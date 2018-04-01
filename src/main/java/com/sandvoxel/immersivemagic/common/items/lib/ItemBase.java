package com.sandvoxel.immersivemagic.common.items.lib;

import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.api.util.IItemRender;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBase extends Item implements IItemRender {
    private String inteneralName;
    private String resourcePath;


    public ItemBase(String internalName, String resourcePath){
        this.maxStackSize = 64;
        this.inteneralName = internalName;
        this.resourcePath = resourcePath;
    }

    public ItemBase(String internalName){
        this.maxStackSize = 64;
        this.inteneralName = internalName;
        this.resourcePath = internalName;
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
