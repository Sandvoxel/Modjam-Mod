package com.sandvoxel.immersivemagic.common.spells;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellLight;
import com.sandvoxel.immersivemagic.common.spells.lib.SpellBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class LightSpell extends SpellBase {
    public LightSpell() {
        super("lightspell", "lightspell", SpellTypes.THROWABLE_SPELL, SpellLight.class);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        IAffinities aff = playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null);

        if(!worldIn.isRemote && aff.canCast(80, AffinityTypes.EARTH)){
            SpellLight spellLight = new SpellLight(worldIn,playerIn);
            spellLight.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float) -(playerIn.motionX+playerIn.motionY+playerIn.motionZ)+1.0F, 1.0F);
            worldIn.spawnEntity(spellLight);
            return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));

        }


        return new ActionResult(EnumActionResult.FAIL,playerIn.getHeldItem(handIn));
    }
}
