package aurocosh.divinefavor.common.item.talismans.spell;

import aurocosh.divinefavor.common.config.common.ConfigGeneral;
import aurocosh.divinefavor.common.config.common.ConfigSpells;
import aurocosh.divinefavor.common.item.talismans.spell.base.ItemSpellTalisman;
import aurocosh.divinefavor.common.item.talismans.spell.base.SpellOptions;
import aurocosh.divinefavor.common.item.talismans.spell.base.TalismanContext;
import aurocosh.divinefavor.common.network.message.client.particles.MessageParticlesHeatWave;
import aurocosh.divinefavor.common.spirit.base.ModSpirit;
import aurocosh.divinefavor.common.util.UtilBlock;
import aurocosh.divinefavor.common.util.UtilRandom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;

public class SpellTalismanHeatWave extends ItemSpellTalisman {
    private static final double RADIUS_SQ = ConfigSpells.heatWave.radius * ConfigSpells.heatWave.radius;

    public SpellTalismanHeatWave(String name, ModSpirit spirit, int favorCost, EnumSet<SpellOptions> options) {
        super(name, spirit, favorCost, options);
    }

    @Override
    protected void performActionServer(TalismanContext context) {
        double radius = ConfigSpells.heatWave.radius;
        World world = context.world;
        EntityPlayer player = context.player;
        BlockPos origin = player.getPosition();
        AxisAlignedBB axis = new AxisAlignedBB(origin.getX() - radius, origin.getY() - radius, origin.getZ() - radius, origin.getX() + radius, origin.getY() + radius, origin.getZ() + radius);
        List<Entity> list = world.getEntitiesWithinAABB(EntityLivingBase.class, axis, e -> e != player && e != null && e.getDistanceSq(origin) <= RADIUS_SQ);

        for (Entity entity : list) {
            entity.attackEntityFrom(DamageSource.ON_FIRE, ConfigSpells.heatWave.damage);

            if (UtilRandom.rollDice(ConfigSpells.heatWave.chanceToSetEnemyOnFire))
                entity.setFire(ConfigSpells.heatWave.enemyBurnTime);
            if (UtilRandom.rollDice(ConfigSpells.heatWave.chanceToSetGroundOnFire))
                UtilBlock.ignite(player, world, entity.getPosition());
        }

        Vec3d positionEyes = player.getPositionEyes(0);
        new MessageParticlesHeatWave(positionEyes).sendToAllAround(world, player.getPosition(), ConfigGeneral.particleRadius);
    }
}
