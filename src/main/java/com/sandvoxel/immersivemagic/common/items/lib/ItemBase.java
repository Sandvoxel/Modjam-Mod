package com.sandvoxel.immersivemagic.common.items.lib;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.api.util.IItemRender;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item implements IItemRender {
    protected String inteneralName;
    protected String resourcePath;


    public ItemBase(String internalName, String resourcePath){
        this.maxStackSize = 64;
        this.setUnlocalizedName(internalName);
        this.inteneralName = internalName;
        this.resourcePath = resourcePath;


    }



    public String getInteneralName() {
        return this.inteneralName;
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
    @SideOnly(Side.CLIENT)
    public void registerItemRenderer() {
        String resourcePath = String.format("%s:%s", Reference.MOD_ID, this.resourcePath);
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(resourcePath, "inventory"));
    }
}
