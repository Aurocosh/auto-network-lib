[![Build Status](https://travis-ci.com/Aurocosh/divine-favor.svg?branch=master)](https://travis-ci.com/Aurocosh/divine-favor)

# Auto Network Lib
Network message serialization library

Curse Forge: [Project page](https://minecraft.curseforge.com/projects/auto-network-lib)

# Features:
- Automatic serialization and deserialization of fields in a network packet
- Automatic packet handling in main thread
- Built in serializers of most common data types
- You can add your own serializer for a type that is not supported by default
- Can serialize generic lists
- Can serialize generic sets 
- Can serialize generic maps 
- Can serialize java arrays
- Can serialize generic collection inside generic collection
- Can serialize any Enum
- Optimized for perfomance

## Description
When writing network code modders need to manually serialize and deserialize all fields in a packet. This library can do it automatically so that you don't have to write boiler plate code. You only need to declare fields in a packet.
Networking code in Minecraft is running in a separate thread from main game thread and because of this before doing anything you need to schedule task in main thread and handle everything in there. This library automatically schedules task in main thread.
Out of the box this library supports serialization for following data types: int, long, float, double, boolean, Integer, Long, Float, Double, Boolean, String, NBTTagCompound, ItemStack, char, byte, short, Character, Byte, Short, BlockPos, Vec3d, Color3f
This library can automatically serialize following generic java collections: List&lt;T&gt;, ArrayList&lt;T&gt;, Stack&lt;T&gt;, Vector&lt;T&gt;, LinkedList&lt;T&gt;, Set&lt;T&gt;, HashSet&lt;T&gt;, Map&lt;T,K&gt;, HashMap&lt;T,K&gt;
This library can automatically serialize arrays of any type but currently only one dimensional arrays supported.
This library can automatically serialize supported generic collections inside of other supported generic collections. For example it can serialize following data types: HashMap&lt;Integer, List&lt;Integer&gt;&gt;, List&lt;List&lt;List&lt;String&gt;&gt;&gt;, HashMap&lt;List&lt;String&gt;, List&lt;Integer&gt;&gt;
Optimized for perfomance. Reflection is used only once on first serialization of packet after that no reflection is needed. Also it uses method handles instead of reflection to read and write data which is faster then access via reflection.

## Maven info

```
maven {
    url "https://dl.bintray.com/aurocosh/Maven"
}

dependencies {
    compile 'aurocosh.autonetworklib:autonetworklib-1.12.2:[VERSION]'
}
```

Replace [VERSION] with the version of the mod that you want to use! 

## Serializer registration examples:

Here is an example serializer registration for Color3f data type:

```java
@Mod.EventBusSubscriber
public class Color3fSerializer {
    @SubscribeEvent
    public static void onRegisterSerializers(BufSerializerRegistryEvent event) {
        BufSerializerProvider.registerReader(Color3f.class, Color3fSerializer::readColor3f);
        BufSerializerProvider.registerWriter(Color3f.class, Color3fSerializer::writeColor3f);
    }

    public static void writeColor3f(ByteBuf buf, Color3f value) {
        buf.writeFloat(value.x);
        buf.writeFloat(value.y);
        buf.writeFloat(value.z);
    }

    public static Color3f readColor3f(ByteBuf buf) {
        float x = buf.readFloat();
        float y = buf.readFloat();
        float z = buf.readFloat();
        return new Color3f(x, y, z);
    }
}
```

Here is an example serializer registration
for MutableInt data type:

```java
@Mod.EventBusSubscriber
public class MutableIntSerializer {
    @SubscribeEvent
    public static void onRegisterSerializers(BufSerializerRegistryEvent event) {
        BufSerializerProvider.registerWriter(MutableInt.class, MutableIntSerializer::writeMutableInt);
        BufSerializerProvider.registerReader(MutableInt.class, MutableIntSerializer::readMutableInt);
    }

    public static void writeMutableInt(ByteBuf buf, MutableInt value) {
        buf.writeInt(value.getValue());
    }

    public static MutableInt readMutableInt(ByteBuf buf) {
        return new MutableInt(buf.readInt());
    }
}
```
 
## Code examples
Without this library:
```java
public class PacketAuraChunk implements IMessage {
    private int chunkX;
    private int chunkZ;
    private Map&lt;BlockPos, MutableInt&gt; drainSpots;

    public PacketAuraChunk(int chunkX, int chunkZ, Map&lt;BlockPos, MutableInt&gt; drainSpots) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.drainSpots = drainSpots;
    }

    public PacketAuraChunk() {

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.chunkX = buf.readInt();
        this.chunkZ = buf.readInt();

        this.drainSpots = new HashMap&lt;&gt;();
        int amount = buf.readInt();
        for (int i = 0; i &lt; amount; i++) {
            this.drainSpots.put(
                    BlockPos.fromLong(buf.readLong()),
                    new MutableInt(buf.readInt())
            );
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.chunkX);
        buf.writeInt(this.chunkZ);

        buf.writeInt(this.drainSpots.size());
        for (Map.Entry&lt;BlockPos, MutableInt&gt; entry : this.drainSpots.entrySet()) {
            buf.writeLong(entry.getKey().toLong());
            buf.writeInt(entry.getValue().intValue());
        }
    }

    public static class Handler implements IMessageHandler&lt;PacketAuraChunk, IMessage&gt; {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketAuraChunk message, MessageContext ctx) {
            NaturesAura.proxy.scheduleTask(() -&gt; {
                World world = Minecraft.getMinecraft().world;
                if (world != null) {
                    Chunk chunk = world.getChunk(message.chunkX, message.chunkZ);
                    if (chunk.hasCapability(NaturesAuraAPI.capAuraChunk, null)) {
                        AuraChunk auraChunk = (AuraChunk) chunk.getCapability(NaturesAuraAPI.capAuraChunk, null);
                        auraChunk.setSpots(message.drainSpots);
                    }
                }
            });

            return null;
        }
    }
}
```

With this library:
```java
public class PacketAuraChunk extends NetworkClientMessage {
    private int chunkX;
    private int chunkZ;
    private Map&lt;BlockPos, MutableInt&gt; drainSpots;

    public PacketAuraChunk(int chunkX, int chunkZ, Map&lt;BlockPos, MutableInt&gt; drainSpots) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.drainSpots = drainSpots;
    }

    public PacketAuraChunk() {

    }

    @SideOnly(Side.CLIENT)
    override fun handleSafe() {
        World world = Minecraft.getMinecraft().world;
	if (world != null) {
	    Chunk chunk = world.getChunk(message.chunkX, message.chunkZ);
	    if (chunk.hasCapability(NaturesAuraAPI.capAuraChunk, null)) {
		AuraChunk auraChunk = (AuraChunk) chunk.getCapability(NaturesAuraAPI.capAuraChunk, null);
		auraChunk.setSpots(message.drainSpots);
	    }
	}
    }
}
```

## Credits:
- Vazkii - This mod is inspired by his library AutoRegLib
- Ellpeck - Some of his code used in example
