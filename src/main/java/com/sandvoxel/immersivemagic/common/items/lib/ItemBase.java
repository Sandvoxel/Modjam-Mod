package com.sandvoxel.immersivemagic.common.items.lib;

import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.api.util.IItemRender;
import com.sandvoxel.immersivemagic.common.entity.SpellBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item implements IItemRender {
    private String inteneralName;
    private String resourcePath;


    public ItemBase(String inteneralName, String resourcePath){
        this.maxStackSize = 64;
        this.inteneralName = inteneralName;
        this.resourcePath = resourcePath;
    }

    public String getInteneralName() {
        return inteneralName;
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return false;
    }

    @Override
    public void registerItemRenderer() {
        final String resourcePath = String.format("%s:%s", Refrence.MOD_ID, this.resourcePath);

        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(resourcePath, "inventory"));
    }
}
