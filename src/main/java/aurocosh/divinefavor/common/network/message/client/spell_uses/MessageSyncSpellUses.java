package aurocosh.divinefavor.common.network.message.client.spell_uses;

import aurocosh.divinefavor.DivineFavor;
import aurocosh.divinefavor.common.custom_data.player.PlayerData;
import aurocosh.divinefavor.common.custom_data.player.data.talisman_uses.FavorData;
import aurocosh.divinefavor.common.network.base.NetworkWrappedClientMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncSpellUses extends NetworkWrappedClientMessage {

    public int talismanId;
    public int spellUses;

    public MessageSyncSpellUses() {
    }

    public MessageSyncSpellUses(int talismanId, FavorData usesData) {
        this.talismanId = talismanId;
        this.spellUses = usesData.getFavor(talismanId);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void handleSafe() {
        EntityPlayer player = DivineFavor.proxy.getClientPlayer();
        FavorData usesData = PlayerData.get(player).getFavorData();
        usesData.setFavor(talismanId, spellUses);
    }
}
