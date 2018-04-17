package com.sandvoxel.immersivemagic.api.magic;

import com.sandvoxel.immersivemagic.common.magicdata.AffinityObject;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;

import java.util.List;

public interface IAffinities {
    List<AffinityObject>getPlayerAffinities();

    void addAffinities(AffinityTypes affinityTypes);

    void removeAffinity(AffinityTypes affinityObject);

    int getAffinityLevel(AffinityTypes affinityType);

    int getAffinityMana(AffinityTypes affinityType);

    void setAffinityMana(AffinityTypes affinityType, int manaLevel);

    boolean canCast(int manaCost, AffinityTypes affinityType);

    int getManaCap(AffinityTypes affinityType);

    void addXp(int XP,AffinityTypes affinityTypes);

    void setPlayerAffinities(List<AffinityObject> affinities);

    boolean hasAffinity(AffinityTypes affinityTypes);
}
