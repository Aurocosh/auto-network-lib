package aurocosh.autonetworklib.network.base.serialization.interfaces;

import io.netty.buffer.ByteBuf;

@FunctionalInterface
public interface BufReader<T> {
    T read(ByteBuf buf);
}
