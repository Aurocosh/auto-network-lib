package aurocosh.divinefavor.common.core;

import aurocosh.divinefavor.common.block.base.ModBlocks;
import aurocosh.divinefavor.common.item.base.ModItems;
import aurocosh.divinefavor.common.lib.LibMisc;
import aurocosh.divinefavor.common.potion.PotionTypeDivine;
import aurocosh.divinefavor.common.potion.PotionTypeRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;

public class DivineFavorCreativeTab extends CreativeTabs {
    public static DivineFavorCreativeTab INSTANCE = new DivineFavorCreativeTab();
    NonNullList list;

    public DivineFavorCreativeTab() {
        super(LibMisc.MOD_ID);
        //LibMisc.MOD_ID

        //setNoTitle();
        //setBackgroundImageName(LibResources.GUI_CREATIVE);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(ModBlocks.blockFastFurnace);
    }

    @Override
    public ItemStack getTabIconItem() {
        return getIconItemStack();
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_) {
        list = p_78018_1_;

        addBlock(ModBlocks.blockFastFurnace);
        addBlock(ModBlocks.blockIronMedium);

        addItem(ModItems.arrow_throw_talisman);
        addItem(ModItems.bonemeal_talisman);
        addItem(ModItems.fell_tree_talisman);
        addItem(ModItems.ignition_talisman);
        addItem(ModItems.snowball_throw_talisman);
        addItem(ModItems.stoneball_throw_talisman);
        addItem(ModItems.small_fireball_throw_talisman);
        addItem(ModItems.stoneball);

        for (PotionTypeDivine pt : PotionTypeRegistry.potions) {
            list.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), pt));
            list.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), pt));
            list.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), pt));
            list.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), pt));
        }
    }

    private void addItem(Item item) {
        item.getSubItems(this, list);
    }

    private void addBlock(Block block) {
        addItem(Item.getItemFromBlock(block));
    }

}