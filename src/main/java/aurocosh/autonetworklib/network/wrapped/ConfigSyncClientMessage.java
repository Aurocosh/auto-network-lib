package aurocosh.autonetworklib.network.wrapped;

import net.minecraft.entity.player.PlayerEntity;

public abstract class ConfigSyncClientMessage extends WrappedClientMessage {
    protected abstract void getServerConfigs();

    @Override
    public void sendTo(PlayerEntity player) {
        getServerConfigs();
        super.sendTo(player);
    }
}
