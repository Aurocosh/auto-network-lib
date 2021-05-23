package aurocosh.autonetworklib.network.serialization.interfaces;

import net.minecraft.network.PacketBuffer;

@FunctionalInterface
public interface BufReader<T> {
    T read(PacketBuffer buf);
}
