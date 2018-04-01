package com.sandvoxel.immersivemagic.common.spells;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.spells.lib.SpellBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class MiningSpell extends SpellBase {
    public MiningSpell() {
        super("miningspell", "", SpellTypes.DIRECT_SPELL, null);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        IAffinities affinities = playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);
        if(!affinities.getPlayerAffinities().isEmpty()){
            if(affinities.hasAffinity(AffinityTypes.LIGHT))
            ImmersiveMagic.LOGGER.info(affinities.getPlayerAffinities());
        }
            ImmersiveMagic.LOGGER.info(affinities.hasAffinity(AffinityTypes.LIGHT));

        return new ActionResult(EnumActionResult.FAIL,playerIn.getHeldItem(handIn));
    }
}
