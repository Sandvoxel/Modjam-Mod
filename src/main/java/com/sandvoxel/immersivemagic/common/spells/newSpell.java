package com.sandvoxel.immersivemagic.common.spells;

import com.sandvoxel.immersivemagic.common.spells.lib.SpellBase;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellLight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class newSpell extends SpellBase {
    public newSpell() {
        super("lightspell", "", SpellTypes.THROWABLE_SPELL, SpellLight.class);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        if(!worldIn.isRemote){
            SpellLight spellLight = new SpellLight(worldIn,playerIn);
            spellLight.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
            worldIn.spawnEntity(spellLight);
        }


        return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
    }
}
