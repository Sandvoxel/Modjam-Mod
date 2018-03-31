package com.sandvoxel.immersivemagic.common.blocks;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TempBlock extends BlockBase {
    public TempBlock() {
        super(Material.WOOD, "");
        setInternalName("tempblock");
        setCreativeTab(CreativeTabs.TRANSPORTATION);

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IAffinities affinities = playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);
        ImmersiveMagic.LOGGER.info(affinities.getPlayerAffinities().toString());
        return true;
    }
}
