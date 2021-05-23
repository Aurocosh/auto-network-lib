package aurocosh.autonetworklib.network.serialization.buf_serializers;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.vector.Vector3d;

public class Vector3dSerializer {
    public static void writeVec3d(ByteBuf buf, Vector3d value) {
        buf.writeDouble(value.x);
        buf.writeDouble(value.y);
        buf.writeDouble(value.z);
    }

    public static Vector3d readVec3d(ByteBuf buf) {
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        return new Vector3d(x, y, z);
    }
}
