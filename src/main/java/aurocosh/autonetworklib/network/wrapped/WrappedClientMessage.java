package aurocosh.autonetworklib.network.wrapped;

import aurocosh.autonetworklib.network.ISimpleChannelProvider;
import aurocosh.autonetworklib.network.message.NetworkClientMessage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public abstract class WrappedClientMessage extends NetworkClientMessage implements ISimpleChannelProvider {
    public void sendTo(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            getChannel().send(PacketDistributor.PLAYER.with(() -> serverPlayer), this);
        }
    }

    public void sendTo(LivingEntity player) {
        if (player instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            getChannel().send(PacketDistributor.PLAYER.with(() -> serverPlayer), this);
        }
    }

    public void sendToAllAround(World world, BlockPos pos, int range) {
        getChannel().send(PacketDistributor.ALL.noArg(), this);
    }

    public void sendToAll() {
        getChannel().send(PacketDistributor.ALL.noArg(), this);
    }
}
