package com.sandvoxel.immersivemagic.common.magicdata;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AffinitiesProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IAffinities.class)
    public static final Capability<IAffinities> AFFINITIES_CAPABILITY = null;

    private IAffinities instance = AFFINITIES_CAPABILITY.getDefaultInstance();



    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == AFFINITIES_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == AFFINITIES_CAPABILITY ? AFFINITIES_CAPABILITY.<T> cast(this.instance):null;
    }

    @Override
    public NBTBase serializeNBT() {
        return AFFINITIES_CAPABILITY.getStorage().writeNBT(AFFINITIES_CAPABILITY,this.instance,null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        AFFINITIES_CAPABILITY.getStorage().readNBT(AFFINITIES_CAPABILITY,this.instance,null,nbt);
    }
}
