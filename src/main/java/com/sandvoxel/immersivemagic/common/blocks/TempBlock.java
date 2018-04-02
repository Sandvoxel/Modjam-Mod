package com.sandvoxel.immersivemagic.common.blocks;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.client.gui.TestScreen;
import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.util.Platform;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TempBlock extends BlockBase {
    public TempBlock() {
        super(Material.WOOD, "");
        setInternalName("tempblock");
        setCreativeTab(ImmersiveMagic.tabimmmag);

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if(!worldIn.isRemote){
            openGui(playerIn);
        }

        return true;
    }
    @SideOnly(Side.CLIENT)
    private void openGui(EntityPlayer playerIn){
        Minecraft.getMinecraft().displayGuiScreen(new TestScreen(playerIn));

    }
}
