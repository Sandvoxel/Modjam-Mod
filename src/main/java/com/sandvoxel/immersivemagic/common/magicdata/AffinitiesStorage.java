package com.sandvoxel.immersivemagic.common.magicdata;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AffinitiesStorage implements Capability.IStorage<IAffinities> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IAffinities> capability, IAffinities instance, EnumFacing side) {
        StringBuilder string = new StringBuilder();


        for(AffinityObject affinityTypes :instance.getPlayerAffinities()){
            string.append(AffinityTypes.getAffinityID(affinityTypes.getAffinityType()));
            string.append("-");
            string.append(affinityTypes.getAffinityLevel());
            string.append("-");
            string.append(affinityTypes.getAffinityMana());
            string.append("-");
            string.append(affinityTypes.getCurrentXP());
            string.append(" ");
        }

        return new NBTTagString(string.toString());
    }

    @Override
    public void readNBT(Capability<IAffinities> capability, IAffinities instance, EnumFacing side, NBTBase nbt) {
        String[] affinities = ((NBTTagString)nbt).getString().split(" ");
        ImmersiveMagic.LOGGER.info(affinities[0]);
        List<AffinityObject> list = new ArrayList<>();
        if(!affinities[0].isEmpty()) {
            for (String affinitiy : affinities) {
                String[] holder = affinitiy.split("-");
                AffinityObject affinityObject = new AffinityObject(AffinityTypes.getAffinity(Integer.parseInt(holder[0])));
                affinityObject.setAffinityLevel(Integer.parseInt(holder[1]));
                affinityObject.setAffinityMana(Integer.parseInt(holder[2]));
                affinityObject.setCurrentXP(Integer.parseInt(holder[3]));
                list.add(affinityObject);
            }
        }
        instance.setPlayerAffinities(list);
    }
}
