package aurocosh.divinefavor.common.network.message;

import aurocosh.divinefavor.DivineFavor;
import aurocosh.divinefavor.common.item.talisman.capability.ITalismanCostHandler;
import aurocosh.divinefavor.common.item.talisman.capability.TalismanDataHandler;
import aurocosh.divinefavor.common.network.base.NetworkAutoMessage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncTalismanIndexes extends NetworkAutoMessage {
    public int unitIndex;
    public int costIndex;

	public MessageSyncTalismanIndexes() { }

    public MessageSyncTalismanIndexes(int unitIndex, int costIndex) {
        this.unitIndex = unitIndex;
        this.costIndex = costIndex;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage handleMessage(MessageContext context) {
        DivineFavor.proxy.addScheduledTaskClient(this::handle);
        return null;
    }

    @SideOnly(Side.CLIENT)
    private void handle() {
        ItemStack heldItem = DivineFavor.proxy.getClientPlayer().getHeldItem(EnumHand.MAIN_HAND);
        ITalismanCostHandler handler = TalismanDataHandler.getHandler(heldItem);
        if(handler == null)
            return;
        handler.setUnitIndex(unitIndex);
        handler.setCostIndex(costIndex);
    }
}
