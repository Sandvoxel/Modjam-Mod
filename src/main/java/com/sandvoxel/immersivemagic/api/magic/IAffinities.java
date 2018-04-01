package com.sandvoxel.immersivemagic.api.magic;

import com.sandvoxel.immersivemagic.common.magicdata.AffinityObject;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;

import java.util.List;

public interface IAffinities {
    List<AffinityObject>getPlayerAffinities();

    void addAffinities(AffinityObject affinityTypes);

    boolean removeAffinity(AffinityTypes affinityObject);

    int getAffinityLevel(AffinityTypes affinityType);

    int getAffinityMana(AffinityTypes affinityType);

    int getManaCap(AffinityTypes affinityType);

    void setPlayerAffinities(List<AffinityObject> affinities);

    boolean hasAffinity(AffinityTypes affinityTypes);
}
