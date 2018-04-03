package com.sandvoxel.immersivemagic.common.blocks;

import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import com.sandvoxel.immersivemagic.common.spells.Spells;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class SpellLightBlock extends BlockBase {

    protected static final AxisAlignedBB SPELL_LIGHT_AABB = new AxisAlignedBB(0.35D, 0.35D, 0.35D, 0.65D, 0.65D, 0.65D);

    public SpellLightBlock() {
        super(Material.GLASS, "block_spell_light");
        setInternalName("block_spell_light");
        lightValue = 15;
        setLightOpacity(0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        if(worldIn.isRemote && rand.nextInt(8) > 5){
            for (int i = 0; i < rand.nextInt(2); ++i)
            {
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D, 0,0,0);
            }
        }
    }


    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return Spells.LIGHT_SPELL.getitemStack();
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SPELL_LIGHT_AABB;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return 15;
    }
}
