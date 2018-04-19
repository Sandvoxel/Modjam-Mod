package com.sandvoxel.elementalaffinities.common.magicdata;

import com.sandvoxel.elementalaffinities.ElementalAffinities;
import com.sandvoxel.elementalaffinities.api.magic.IAffinities;
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
            string.append("-");
            string.append(affinityTypes.isActive());
            string.append(" ");
        }

        return new NBTTagString(string.toString());
    }

    @Override
    public void readNBT(Capability<IAffinities> capability, IAffinities instance, EnumFacing side, NBTBase nbt) {
        String[] affinities = ((NBTTagString)nbt).getString().split(" ");
        ElementalAffinities.LOGGER.info(affinities[0]);
        List<AffinityObject> list = new ArrayList<>();
        for(AffinityTypes types : AffinityTypes.values()){
            list.add(new AffinityObject(types));
        }
        if(!affinities[0].isEmpty()) {
            boolean encounteredException = false;
            for (String affinitiy : affinities) {
                try {
                    String[] holder = affinitiy.split("-");
                    list.get(Integer.parseInt(holder[0])).setAffinityLevel(Integer.parseInt(holder[1]));
                    list.get(Integer.parseInt(holder[0])).setAffinityMana(Integer.parseInt(holder[2]));
                    list.get(Integer.parseInt(holder[0])).setCurrentXP(Integer.parseInt(holder[3]));
                    list.get(Integer.parseInt(holder[0])).setActive(Boolean.parseBoolean(holder[4]));
                } catch (Exception exception) {
                    if (!encounteredException) {
                        ElementalAffinities.LOGGER.info("Encountered exception reading NBT data. Resetting all affinities in this loaded instance.");
                        encounteredException = true;
                    }
                    String[] holder = affinitiy.split("-");
                    list.get(Integer.parseInt(holder[0])).setAffinityLevel(1);
                    list.get(Integer.parseInt(holder[0])).setAffinityMana(0);
                    list.get(Integer.parseInt(holder[0])).setCurrentXP(0);
                    list.get(Integer.parseInt(holder[0])).setActive(false);
                }
            }
        }
        instance.setPlayerAffinities(list);
    }
}
