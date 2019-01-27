package aurocosh.divinefavor.common.registry.common;

import aurocosh.divinefavor.common.core.ResourceNamer;
import aurocosh.divinefavor.common.favor.ModFavor;
import aurocosh.divinefavor.common.muliblock.ModMultiBlock;
import aurocosh.divinefavor.common.spirit.base.ModSpirit;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber
public final class RegistryGenerator {
    private final static String REGESTRY_PREFIX = "registry";

    @SubscribeEvent
    public static void onCreateRegistry(RegistryEvent.NewRegistry event) {
        new RegistryBuilder<ModFavor>()
                .setType(ModFavor.class)
                .setName(ResourceNamer.getFullName(REGESTRY_PREFIX,"favors"))
                .create();
        new RegistryBuilder<ModMultiBlock>()
                .setType(ModMultiBlock.class)
                .setName(ResourceNamer.getFullName(REGESTRY_PREFIX,"multi_blocks"))
                .create();
        new RegistryBuilder<ModSpirit>()
                .setType(ModSpirit.class)
                .setName(ResourceNamer.getFullName(REGESTRY_PREFIX,"spirits"))
                .create();
    }
}