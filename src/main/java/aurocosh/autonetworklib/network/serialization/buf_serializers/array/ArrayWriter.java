package aurocosh.autonetworklib.network.serialization.buf_serializers.array;

import aurocosh.autonetworklib.network.serialization.interfaces.BufWriter;
import net.minecraft.network.PacketBuffer;

import java.lang.reflect.Array;

public class ArrayWriter implements BufWriter<Object> {
    private final BufWriter writer;

    public ArrayWriter(BufWriter writer) {
        this.writer = writer;
    }

    public void write(PacketBuffer buf, Object array) {
        int length = Array.getLength(array);
        buf.writeInt(length);
        for (int i = 0; i < length; i ++)
            writer.write(buf, Array.get(array, i));
    }
}
