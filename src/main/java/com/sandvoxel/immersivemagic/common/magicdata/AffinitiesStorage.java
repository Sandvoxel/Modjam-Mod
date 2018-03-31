package com.sandvoxel.immersivemagic.common.magicdata;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class AffinitiesStorage implements Capability.IStorage<IAffinities> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IAffinities> capability, IAffinities instance, EnumFacing side) {
        StringBuilder string = new StringBuilder();


        for(AffinityObject affinityTypes :instance.getPlayerAffinities()){
            string.append(AffinityTypes.getAffinityID(affinityTypes.getAffinityType()));
            string.append("-");
            string.append(affinityTypes.getAffinityPower());
            string.append(" ");
        }

        return new NBTTagString(string.toString());
    }

    @Override
    public void readNBT(Capability<IAffinities> capability, IAffinities instance, EnumFacing side, NBTBase nbt) {
        instance.setPlayerAffinitiesFromNBT(((NBTTagString)nbt));
    }
}
