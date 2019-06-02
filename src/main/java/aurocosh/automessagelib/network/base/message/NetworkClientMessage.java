package aurocosh.automessagelib.network.base.message;

import aurocosh.automessagelib.AutoMessageLib;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class NetworkClientMessage extends NetworkAutoMessage {
    public NetworkClientMessage() {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public final IMessage handleMessage(MessageContext context) {
        AutoMessageLib.proxy.addScheduledTaskClient(this::handleSafe);
        return null;
    }

    @SideOnly(Side.CLIENT)
    protected abstract void handleSafe();
}
