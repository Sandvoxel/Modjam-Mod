package com.sandvoxel.elementalaffinities.common.spells;

import com.sandvoxel.elementalaffinities.common.magicdata.AffinitiesProvider;
import com.sandvoxel.elementalaffinities.common.magicdata.AffinityTypes;
import com.sandvoxel.elementalaffinities.common.network.AffinityGuiPacket;
import com.sandvoxel.elementalaffinities.common.network.lib.Network;
import com.sandvoxel.elementalaffinities.common.spells.entity.SpellLiquefact;
import com.sandvoxel.elementalaffinities.common.spells.lib.SpellBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

/**
 * Created by CrazyGrape on 4/1/2018.
 */
public class LiquefactSpell extends SpellBase {

    public LiquefactSpell() {
        super("spell_liquefact", "spell_earth", SpellTypes.THROWABLE_SPELL, SpellLiquefact.class, AffinityTypes.EARTH, 80);
    }

    protected void dispMana(EntityPlayer playerIn, int affMana) {
        playerIn.sendStatusMessage(new TextComponentTranslation(affMana + " mana", new Object[0]), true);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        Network.sendToServer(new AffinityGuiPacket(0, 0, true));
        //dispMana(playerIn, aff.getAffinityMana(spellAffType));


        if (playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).canCast(baseManaCost, spellAffType)) {
            if (!worldIn.isRemote) {
                SpellLiquefact liquef = new SpellLiquefact(worldIn, playerIn);
                liquef.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float) -(playerIn.motionX + playerIn.motionY + playerIn.motionZ) + 1F, 1.0F);
                worldIn.spawnEntity(liquef);
            }
            return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        } else if (playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).hasAffinity(spellAffType)) {
            dispOutOfMana(playerIn, spellAffType.getMeta(), playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).getAffinityMana(spellAffType), baseManaCost);
        } else {
            dispNoAffinity(playerIn, spellAffType.getMeta());
        }

        return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
