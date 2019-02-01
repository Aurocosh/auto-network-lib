package aurocosh.divinefavor.common.custom_data.player.data.pearl_crumbs;

import aurocosh.divinefavor.common.lib.GlobalBlockPos;
import aurocosh.divinefavor.common.lib.interfaces.INbtSerializer;
import aurocosh.divinefavor.common.util.UtilSerialize;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

// Handles the actual read/write of the nbt.
public class PearlCrumbsDataSerializer implements INbtSerializer<PearlCrumbsData> {
    private static String TAG_CRUMBS_POSITIONS = "CrumbsPositions";
    private static String TAG_CRUMBS_DIMENSIONS = "CrumbsDimensions";

    @Override
    public void serialize(NBTTagCompound nbt, PearlCrumbsData instance) {
        List<GlobalBlockPos> globalBlockPos = instance.getAllPositions();
        List<BlockPos> posList = new ArrayList<>(globalBlockPos.size());
        int[] dimensions = new int[globalBlockPos.size()];

        for (int i = 0; i < globalBlockPos.size(); i++) {
            GlobalBlockPos pos = globalBlockPos.get(i);
            posList.add(pos.pos);
            dimensions[i] = pos.dimensionId;
        }

        nbt.setIntArray(TAG_CRUMBS_POSITIONS, UtilSerialize.serializeBlockPosArray(posList));
        nbt.setIntArray(TAG_CRUMBS_DIMENSIONS, dimensions);
    }

    @Override
    public void deserialize(NBTTagCompound nbt, PearlCrumbsData instance) {

        List<BlockPos> posList = UtilSerialize.deserializeBlockPosArray(nbt.getIntArray(TAG_CRUMBS_POSITIONS));
        int[] dimensions = nbt.getIntArray(TAG_CRUMBS_DIMENSIONS);

        if (posList.size() != dimensions.length)
            return;

        List<GlobalBlockPos> globalBlockPos = new ArrayList<>();
        for (int i = 0; i < posList.size(); i++) {
            BlockPos pos = posList.get(i);
            int dimension = dimensions[i];
            globalBlockPos.add(new GlobalBlockPos(pos, dimension));
        }
        instance.setAllPositions(globalBlockPos);
    }
}