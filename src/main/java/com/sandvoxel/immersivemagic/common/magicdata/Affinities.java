package com.sandvoxel.immersivemagic.common.magicdata;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagString;

import java.util.ArrayList;
import java.util.List;

public class Affinities implements IAffinities {
    //The player that has the affinity
    private EntityPlayer player;
    //The players affinitys
    private List<AffinityObject> affinityObjects = new ArrayList<>();

    public Affinities(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public List<AffinityObject> getPlayerAffinities() {
        return affinityObjects;
    }

    @Override
    public void addAffinities(AffinityObject affinityTypes) {
        for (AffinityObject affinityObject : affinityObjects){
            if(affinityObject.getAffinityType()==affinityTypes.getAffinityType())
                return;
        }
            this.affinityObjects.add(affinityTypes);
    }

    @Override
    public void setPlayerAffinitiesFromNBT(NBTTagString nbt) {
        String[] affinities = nbt.getString().split(" ");
        ImmersiveMagic.LOGGER.info(affinities[0]);
        affinityObjects.clear();
        if(!affinities[0].isEmpty()) {
            for (String affinitiy : affinities) {
                String[] holder = affinitiy.split("-");
                affinityObjects.add(new AffinityObject(AffinityTypes.getAffinity(Integer.parseInt(holder[0])), Integer.parseInt(holder[1])));
            }
        }
    }

}
