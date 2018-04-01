package com.sandvoxel.immersivemagic.common.magicdata;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.capability.SimpleCapabilityProvider;
import com.sandvoxel.immersivemagic.common.util.CapabilityUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class AffinitiesProvider {

    @CapabilityInject(IAffinities.class)
    public static final Capability<IAffinities> AFFINITIES_CAPABILITY = null;



    @Nullable
    public static IAffinities getMaxHealth(EntityPlayer entity) {
        return CapabilityUtils.getCapability(entity, AFFINITIES_CAPABILITY, null);
    }

    public static ICapabilityProvider createpoider(IAffinities affinities){
        return new SimpleCapabilityProvider<>(AFFINITIES_CAPABILITY,null,affinities);
    }

}
