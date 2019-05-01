package aurocosh.divinefavor.common.network.message.client.spirit_data;

import aurocosh.divinefavor.DivineFavor;
import aurocosh.divinefavor.common.custom_data.player.PlayerData;
import aurocosh.divinefavor.common.custom_data.player.data.favor.SpiritData;
import aurocosh.divinefavor.common.network.base.WrappedClientMessage;
import aurocosh.divinefavor.common.spirit.base.ModSpirit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncFavor extends WrappedClientMessage {
    public int spiritId;
    public int favor;

    public MessageSyncFavor() {
    }

    public MessageSyncFavor(ModSpirit spirit, SpiritData spiritData) {
        this.spiritId = spirit.getId();
        favor = spiritData.getFavor(spiritId);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void handleSafe() {
        EntityPlayer player = DivineFavor.proxy.getClientPlayer();
        SpiritData spiritData = PlayerData.INSTANCE.get(player).getSpiritData();
        spiritData.setFavor(spiritId, favor);
    }
}
