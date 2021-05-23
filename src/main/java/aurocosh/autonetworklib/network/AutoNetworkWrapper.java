package aurocosh.autonetworklib.network;

import aurocosh.autonetworklib.network.message.NetworkAutoMessage;
import aurocosh.autonetworklib.network.message.test.TestMessage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class AutoNetworkWrapper {
    private static final String PROTOCOL_VERSION = "1";

    private int nextId;
    private final SimpleChannel wrapper;

    public AutoNetworkWrapper(String modId) {
        nextId = 0;
        wrapper = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(modId, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );
    }

    public SimpleChannel getWrapper() {
        return wrapper;
    }

    public <MSG extends NetworkAutoMessage> void register(Class<MSG> messageType) {
//        wrapper.registerMessage(clazz, clazz, nextId++, handlerSide);


        wrapper.registerMessage(0,TestMessage.class, TestMessage::toBytes, TestMessage::fromBytes, TestMessage::onMessage);
    }

}
