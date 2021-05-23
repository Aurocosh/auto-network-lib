package aurocosh.autonetworklib.network.serialization.buf_serializers;

import aurocosh.autonetworklib.network.serialization.interfaces.BufReader;
import aurocosh.autonetworklib.network.serialization.interfaces.BufWriter;
import net.minecraft.network.PacketBuffer;

public class EnumSerializer implements BufWriter<Enum>, BufReader<Enum> {
    private final Enum[] values;

    public EnumSerializer(Class clazz) {
        values = (Enum[]) clazz.getEnumConstants();
    }

    @Override
    public void write(PacketBuffer buf, Enum value) {
        buf.writeInt(value.ordinal());
    }

    @Override
    public Enum read(PacketBuffer buf) {
        return values[buf.readInt()];
    }
}
