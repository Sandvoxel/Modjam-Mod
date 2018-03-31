package com.sandvoxel.immersivemagic.common.items;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.common.entity.SpellBase;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SpellItem extends ItemBase {
    public SpellItem() {
        super("spell","");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        if(!worldIn.isRemote){
            SpellBase spellBase = new SpellBase(worldIn,playerIn);
            spellBase.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(spellBase);
        }


        return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
    }
}
