package aurocosh.automessagelib.network.base.serialization.serializer_provider.registration;

import aurocosh.automessagelib.network.base.serialization.interfaces.BufReader;
import aurocosh.automessagelib.network.base.serialization.interfaces.BufWriter;
import aurocosh.automessagelib.network.base.serialization.interfaces.GenericSerializerProvider;
import aurocosh.automessagelib.network.base.serialization.serializer_provider.BufSerializerProvider;
import net.minecraftforge.fml.common.eventhandler.Event;

public class BufSerializerRegistryEvent extends Event {
    public BufSerializerRegistryEvent() {
    }

    public static <T> void registerWriter(Class<T> clazz, BufWriter<T> writer) {
        BufSerializerProvider.registerWriter(clazz, writer);
    }

    public static <T> void registerReader(Class<T> clazz, BufReader<T> reader) {
        BufSerializerProvider.registerReader(clazz, reader);
    }

    public static <T> void registerSerializerProvider(Class<T> clazz, GenericSerializerProvider<T> provider) {
        BufSerializerProvider.registerSerializerProvider(clazz, provider);
    }
}
