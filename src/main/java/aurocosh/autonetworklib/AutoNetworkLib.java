package aurocosh.autonetworklib;

import aurocosh.autonetworklib.proxy.ClientProxy;
import aurocosh.autonetworklib.proxy.ServerProxy;
import aurocosh.autonetworklib.proxy.IProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(AutoNetworkLib.MOD_ID)
public class AutoNetworkLib {
    // Mod Constants
    public final static String MOD_ID = "autonetworklib";
    public final static String MOD_NAME = "AutoNetworkLib";

    public static AutoNetworkLib instance;
    public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static Logger logger;

    public AutoNetworkLib() {
        instance = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    public void setup(FMLCommonSetupEvent event) {
        this.preInit(event);
        this.init(event);
        this.postInit(event);
    }

    public void preInit(FMLCommonSetupEvent event) {
        logger = LogManager.getLogger();
        proxy.preInit(event);
    }

    public void init(FMLCommonSetupEvent event) {
        proxy.init(event);
    }

    public void postInit(FMLCommonSetupEvent event) {
        proxy.postInit(event);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            logger.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        logger.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
}
