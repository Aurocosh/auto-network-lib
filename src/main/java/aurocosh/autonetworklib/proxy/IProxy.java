package aurocosh.autonetworklib.proxy;

import com.google.common.util.concurrent.ListenableFuture;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public interface IProxy {
    void preInit(FMLCommonSetupEvent event);

    void init(FMLCommonSetupEvent event);

    void postInit(FMLCommonSetupEvent event);

//    ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule);
}
