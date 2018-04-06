package com.sandvoxel.immersivemagic.common.spells;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellNova;
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
public class NovaSpell extends SpellBase {


    public NovaSpell() {
        super("spell_nova", "spell_fire", SpellTypes.THROWABLE_SPELL, SpellNova.class, AffinityTypes.FIRE, 100);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        successfulCast = true;
        IAffinities aff = playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null);

        if (!worldIn.isRemote) {
            if (aff.canCast(baseManaCost, spellAffType)){
                SpellNova spellNova = new SpellNova(worldIn, playerIn);
                spellNova.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float) -(playerIn.motionX + playerIn.motionY + playerIn.motionZ) + 1.0F, 1.0F);
                worldIn.spawnEntity(spellNova);
                return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }  else if (aff.hasAffinity(spellAffType)) {
                dispOutOfMana(playerIn, spellAffType.getMeta(), aff.getAffinityMana(spellAffType), baseManaCost);
            } else {
                dispNoAffinity(playerIn, spellAffType.getMeta());
            }
        } else if (successfulCast) {
            return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
