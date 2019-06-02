package aurocosh.automessagelib;

import aurocosh.automessagelib.constants.ConstMisc;
import aurocosh.automessagelib.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ConstMisc.MOD_ID, name = ConstMisc.MOD_NAME, version = ConstMisc.VERSION, dependencies = "required-after:forge@[14.23.5.2768,)")
public class AutoMessageLib {
	@Instance(ConstMisc.MOD_ID)
	public static AutoMessageLib instance;

	@SidedProxy(serverSide = ConstMisc.PROXY_COMMON, clientSide = ConstMisc.PROXY_CLIENT)
	public static CommonProxy proxy;
    public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        logger = LogManager.getLogger();
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
