package aurocosh.autonetworklib.network.message;
import aurocosh.autonetworklib.network.serialization.class_serializers.ClassBufSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.HashMap;
import java.util.function.Supplier;

public abstract class NetworkAutoMessage {
    public static final HashMap<Class<? extends NetworkAutoMessage>, ClassBufSerializer> serializers = new HashMap<>();

    protected static <T extends NetworkAutoMessage> T loadFromBuffer(T message, PacketBuffer buf) {
        Class<? extends NetworkAutoMessage> clazz = message.getClass();
        ClassBufSerializer serializer = serializers.computeIfAbsent(clazz, ClassBufSerializer::new);
        serializer.fromBytes(message, buf);
        return message;
    }

    protected static void writeToBuffer(NetworkAutoMessage message, PacketBuffer buf) {
        Class<? extends NetworkAutoMessage> clazz = message.getClass();
        ClassBufSerializer serializer = serializers.computeIfAbsent(clazz, ClassBufSerializer::new);
        serializer.toBytes(message, buf);
    }

    protected static void handleRawMessage(NetworkAutoMessage message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> message.handleMessage(context));
        context.setPacketHandled(true);
    }

    public abstract void handleMessage(NetworkEvent.Context context);
}
