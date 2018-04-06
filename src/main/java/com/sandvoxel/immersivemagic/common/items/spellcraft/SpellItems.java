package com.sandvoxel.immersivemagic.common.items.spellcraft;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpellItems extends ItemBase {

    public SpellItems() {
        super("spellcraft", "");
        setHasSubtypes(true);
        setCreativeTab(ImmersiveMagic.tabimmmag);
        setMaxStackSize(8);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        IAffinities affinities = playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);
        ItemStack helditem = playerIn.getHeldItem(handIn);

        affinities.addAffinities(AffinityTypes.getAffinity(helditem.getItemDamage()));




        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab != ImmersiveMagic.tabimmmag)
            return;
        for (int i = 0; i < AffinityTypes.values().length; i++) {
                items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName();
        String element = AffinityTypes.getAffinity(stack.getItemDamage()).name();
        return name + "." + element;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerItemRenderer() {
        for (int i = 0; i < AffinityTypes.values().length; i++) {
            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Reference.MOD_ID + ":" + this.resourcePath + "spellcraft_" + AffinityTypes.getAffinity(i).name().toLowerCase(), "inventory"));
        }
    }
}

