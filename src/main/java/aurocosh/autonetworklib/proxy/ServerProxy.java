package aurocosh.autonetworklib.proxy;

import aurocosh.autonetworklib.network.serialization.serializer_provider.BufSerializerProvider;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ServerProxy implements IProxy {
    @Override
    public void preInit(FMLCommonSetupEvent event) {
        BufSerializerProvider.preInit();
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
    }

    @Override
    public void postInit(FMLCommonSetupEvent event) {
    }
//
//    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule) {
//        throw new IllegalStateException("This should only be called from client side");
//    }
}
