package aurocosh.automessagelib.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class AutoNetworkWrapper {
    private int nextId;
    private final SimpleNetworkWrapper wrapper;

    public AutoNetworkWrapper(String modId) {
        nextId = 0;
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(modId);
    }

    public void register(Class clazz, Side handlerSide) {
        wrapper.registerMessage(clazz, clazz, nextId++, handlerSide);
    }
}
