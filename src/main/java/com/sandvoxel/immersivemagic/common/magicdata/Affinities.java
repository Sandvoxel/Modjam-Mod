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
        for(AffinityTypes types : AffinityTypes.values()){
            affinityObjects.add(new AffinityObject(types));
        }
    }

    @Override
    public List<AffinityObject> getPlayerAffinities() {
        return affinityObjects;
    }

    @Override
    public void addAffinities(AffinityTypes affinityType) {
        affinityObjects.get(affinityType.getMeta()).setActive(true);
    }

    @Override
    public void removeAffinity(AffinityTypes affinityType) {
        affinityObjects.get(affinityType.getMeta()).setActive(false);
    }

    @Override
    public int getAffinityLevel(AffinityTypes affinityType) {
        return affinityObjects.get(affinityType.getMeta()).getAffinityPower();
    }

    @Override
    public int getAffinityMana(AffinityTypes affinityType) {
        return affinityObjects.get(affinityType.getMeta()).getAffinityMana();
    }

    @Override
    public boolean canCast(int manaCost, AffinityTypes affinityType) {
        AffinityObject affinity = affinityObjects.get(affinityType.getMeta());
        if (affinity.getAffinityMana() >= manaCost) {
            affinityObjects.get(affinityType.getMeta()).setAffinityMana(affinity.getAffinityMana() - manaCost);
            return true;
        }
        return false;
    }

    @Override
    public int getManaCap(AffinityTypes affinityType) {
        return affinityObjects.get(affinityType.getMeta()).getManaCap();

    }

    @Override
    public void addXp(int XP,AffinityTypes affinityType) {
        affinityObjects.get(affinityType.getMeta()).setCurrentXP(affinityObjects.get(affinityType.getMeta()).getCurrentXP()+XP);
        affinityObjects.get(affinityType.getMeta()).canLevelUp();
        ImmersiveMagic.LOGGER.info(affinityObjects.get(affinityType.getMeta()).getAffinityPower());
    }

    @Override
    public void setPlayerAffinities(List<AffinityObject> nbt) {
        affinityObjects.clear();
        affinityObjects = nbt;
    }

    @Override
    public boolean hasAffinity(AffinityTypes affinityTypes) {
        return affinityObjects.get(affinityTypes.getMeta()).isActive();
    }

}
