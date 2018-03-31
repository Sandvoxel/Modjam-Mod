package com.sandvoxel.immersivemagic.common.magicdata;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AffinitiesStorage implements Capability.IStorage<IAffinities> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IAffinities> capability, IAffinities instance, EnumFacing side) {
        List<Integer> affinityIDs = new ArrayList<>();
        for(AffinityTypes affinityTypes :instance.getPlayerAffinities()){
            affinityIDs.add(AffinityTypes.getAffinityID(affinityTypes));
        }
        return new NBTTagIntArray(affinityIDs);
    }

    @Override
    public void readNBT(Capability<IAffinities> capability, IAffinities instance, EnumFacing side, NBTBase nbt) {
        instance.setPlayerAffinitiesFromID(((NBTTagIntArray)nbt).getIntArray());
    }
}
