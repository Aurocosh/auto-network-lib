package aurocosh.autonetworklib.network.message;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

public abstract class NetworkServerMessage extends NetworkAutoMessage {
    public final void handleMessage(NetworkEvent.Context context) {
        ServerPlayerEntity serverPlayer = context.getSender(); // the client that sent this packet
        handleMessage(serverPlayer);
    }

    protected abstract void handleMessage(ServerPlayerEntity serverPlayer);
}
