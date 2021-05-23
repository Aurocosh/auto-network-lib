package aurocosh.autonetworklib.network.wrapped;
import aurocosh.autonetworklib.network.ISimpleChannelProvider;
import aurocosh.autonetworklib.network.message.NetworkServerMessage;

public abstract class WrappedServerMessage extends NetworkServerMessage implements ISimpleChannelProvider {
    public void send() {
        getChannel().sendToServer(this);
    }
}
