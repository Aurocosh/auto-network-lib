package aurocosh.autonetworklib.network.serialization.interfaces;

import net.minecraft.network.PacketBuffer;

@FunctionalInterface
public interface BufWriter<T> {
    void write(PacketBuffer buf, T value);
}
