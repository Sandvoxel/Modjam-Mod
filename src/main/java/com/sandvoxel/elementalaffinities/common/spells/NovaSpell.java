package com.sandvoxel.elementalaffinities.common.spells;

import com.sandvoxel.elementalaffinities.common.magicdata.AffinitiesProvider;
import com.sandvoxel.elementalaffinities.common.magicdata.AffinityTypes;
import com.sandvoxel.elementalaffinities.common.network.AffinityGuiPacket;
import com.sandvoxel.elementalaffinities.common.network.lib.Network;
import com.sandvoxel.elementalaffinities.common.spells.entity.SpellNova;
import com.sandvoxel.elementalaffinities.common.spells.lib.SpellBase;
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
        Network.sendToServer(new AffinityGuiPacket(0, 0, true));


        if (playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).canCast(baseManaCost, spellAffType)){
            if (!worldIn.isRemote) {
                SpellNova spellNova = new SpellNova(worldIn, playerIn);
                spellNova.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float) -(playerIn.motionX + playerIn.motionY + playerIn.motionZ) + 1.0F, 1.0F);
                worldIn.spawnEntity(spellNova);
            }
            return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }  else if (playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).hasAffinity(spellAffType)) {
            dispOutOfMana(playerIn, spellAffType.getMeta(), playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).getAffinityMana(spellAffType), baseManaCost);
        } else {
            dispNoAffinity(playerIn, spellAffType.getMeta());
        }

        return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
