package aurocosh.divinefavor.common.core.creative_tabs;

import aurocosh.divinefavor.common.constants.ConstMisc;
import aurocosh.divinefavor.common.item.common.ModItems;
import aurocosh.divinefavor.common.registry.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DivineFavorCreativeTab extends CreativeTabs {
    public static final DivineFavorCreativeTab INSTANCE = new DivineFavorCreativeTab();
    private NonNullList list;

    public DivineFavorCreativeTab() {
        super(ConstMisc.MOD_ID);
        //ConstMisc.MOD_ID

        //setNoTitle();
        //setBackgroundImageName(ConstResources.GUI_CREATIVE);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.grimoire);
    }

//    @Override
//    public ItemStack getTabIconItem() {
//        return getIconItemStack();
//    }

    @Override
    public boolean hasSearchBar() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> stacks) {
        list = stacks;

        ModRegistries.blocks.getValues().forEach(this::addBlock);
        ModRegistries.items.getValues().forEach(this::addItem);
    }

    private void addItem(Item item) {
        item.getSubItems(this, list);
    }

    private void addBlock(Block block) {
        addItem(Item.getItemFromBlock(block));
    }
}