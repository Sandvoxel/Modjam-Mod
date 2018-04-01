package com.sandvoxel.immersivemagic.common.items.spellcraft;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityObject;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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
        setCreativeTab(CreativeTabs.TRANSPORTATION);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        IAffinities affinities = playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);
        ItemStack helditem = playerIn.getHeldItem(handIn);

        if(affinities.getPlayerAffinities().isEmpty()){
            affinities.addAffinities(new AffinityObject(AffinityTypes.getAffinity(helditem.getItemDamage()),1,200));
        }else {

        }



        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < AffinityTypes.values().length; i++) {
                items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName();
        String oreName = AffinityTypes.getAffinity(stack.getItemDamage()).name();
        return name + "." + oreName;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerItemRenderer() {
        for (int i = 0; i < AffinityTypes.values().length; i++) {
            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Refrence.MOD_ID + ":" + resourcePath + "/spellcraft-" + AffinityTypes.getAffinity(i).name(), "inventory"));
        }
    }
}

