package aurocosh.divinefavor.common.network.message;

import aurocosh.divinefavor.DivineFavor;
import aurocosh.divinefavor.common.network.base.NetworkAutoMessage;
import aurocosh.divinefavor.common.tool.IEnergyContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncPower extends NetworkAutoMessage {
    public int energy;

    public MessageSyncPower() {
    }

    public MessageSyncPower(int energy) {
        this.energy = energy;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage handleMessage(MessageContext context) {
        DivineFavor.proxy.addScheduledTaskClient(this::handle);
        return null;
    }

    @SideOnly(Side.CLIENT)
    private void handle() {
        EntityPlayer player = DivineFavor.proxy.getClientPlayer();
        if (player.openContainer instanceof IEnergyContainer)
            ((IEnergyContainer) player.openContainer).syncPower(energy);
    }
}
