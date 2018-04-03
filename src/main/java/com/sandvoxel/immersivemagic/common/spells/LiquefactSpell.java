package com.sandvoxel.immersivemagic.common.spells;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellLiquefact;
import com.sandvoxel.immersivemagic.common.spells.lib.SpellBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by CrazyGrape on 4/1/2018.
 */
public class LiquefactSpell extends SpellBase {
    public LiquefactSpell() {
        super("spell_liquefact", "spell_earth", SpellTypes.THROWABLE_SPELL, SpellLiquefact.class);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        IAffinities aff = playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null);

        if(!worldIn.isRemote && aff.canCast(80, AffinityTypes.EARTH)){
            SpellLiquefact liquef = new SpellLiquefact(worldIn,playerIn);
            liquef.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float) -(playerIn.motionX+playerIn.motionY+playerIn.motionZ)+1F, 1.0F);
            worldIn.spawnEntity(liquef);
        } else {
            if (aff.hasAffinity(AffinityTypes.EARTH)) {
                dispOutOfMana(playerIn);
            } else {
                dispNoAffinity(playerIn);
            }
        }

        return new ActionResult(EnumActionResult.FAIL,playerIn.getHeldItem(handIn));
    }
}