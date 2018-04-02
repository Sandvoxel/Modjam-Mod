package com.sandvoxel.immersivemagic.common.magicdata;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.Iterator;
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
        Iterator<AffinityObject> iter = affinityObjects.iterator();

        while (iter.hasNext()){
            AffinityObject affinityObject = iter.next();

            if(affinityObject.getAffinityType()==affinityTypes.getAffinityType())
                iter.remove();
        }
        ImmersiveMagic.LOGGER.info(affinityTypes);
        this.affinityObjects.add(affinityTypes);
    }

    @Override
    public boolean removeAffinity(AffinityTypes affinityType) {
        Iterator<AffinityObject> iter = affinityObjects.iterator();

        while (iter.hasNext()){
            AffinityObject affinityObject = iter.next();

            if(affinityObject.getAffinityType()==affinityType)
                iter.remove();
        }
        return false;
    }

    @Override
    public int getAffinityLevel(AffinityTypes affinityType) {
        for (AffinityObject affinityObject : affinityObjects) {
            if (affinityObject.getAffinityType() == affinityType) {
                return affinityObject.getAffinityPower();
            }
        }
        return 0;
    }

    @Override
    public int getAffinityMana(AffinityTypes affinityType) {
        for (AffinityObject affinityObject : affinityObjects) {
            if (affinityObject.getAffinityType() == affinityType) {
                return affinityObject.getAffinityMana();
            }
        }
        return 0;
    }

    @Override
    public int getManaCap(AffinityTypes affinityType) {
        for (AffinityObject affinityObject : affinityObjects){
            if(affinityObject.getAffinityType() == affinityType){
                return affinityObject.getManaCap();
            }
        }
        return 0;
    }

    @Override
    public void addXp(int XP,AffinityTypes affinityTypes) {
        for(AffinityObject affinityObject: affinityObjects){
            if(affinityObject.getAffinityType() == affinityTypes){
                affinityObject.setCurrentXP((affinityObject.getCurrentXP()+XP));
                affinityObject.canLevelUp();
            }
        }
    }

    @Override
    public void setPlayerAffinities(List<AffinityObject> nbt) {
        affinityObjects.clear();
        affinityObjects = nbt;
    }

    @Override
    public boolean hasAffinity(AffinityTypes affinityTypes) {
        for (AffinityObject affinityObject : affinityObjects){
            if(affinityObject.getAffinityType() == affinityTypes){
                return true;
            }
        }
        return false;
    }

}
