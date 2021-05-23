package aurocosh.autonetworklib.network.message.test;
import aurocosh.autonetworklib.network.message.NetworkAutoMessage;
import aurocosh.autonetworklib.network.message.NetworkClientMessage;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public class TestMessage extends NetworkClientMessage {
    public static TestMessage fromBytes(PacketBuffer buf) {
        return NetworkAutoMessage.loadFromBuffer(new TestMessage(), buf);
    }

    public static void toBytes(TestMessage message, PacketBuffer buf) {
        NetworkAutoMessage.writeToBuffer(message, buf);
    }

    public static void onMessage(TestMessage message, Supplier<NetworkEvent.Context> ctx) {
        NetworkAutoMessage.handleRawMessage(message, ctx);
    }

    @Override
    public void handleMessage(NetworkEvent.Context context) {

    }
}
