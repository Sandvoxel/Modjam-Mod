package com.sandvoxel.immersivemagic.common.items.lib;

import com.sandvoxel.immersivemagic.common.entity.SpellBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.Sound;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ItemBase extends Item {
    private String inteneralName;

    public ItemBase(String inteneralName){
        this.maxStackSize = 1;
        this.inteneralName = inteneralName;
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

}
