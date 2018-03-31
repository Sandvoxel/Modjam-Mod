package com.sandvoxel.immersivemagic.common.magicdata;

import java.util.ArrayList;
import java.util.List;

public class AffinityObject {
    private AffinityTypes affinityType;
    private int affinityPower;

    public AffinityObject(AffinityTypes affinityType, int affinityPower) {
        this.affinityType = affinityType;
        this.affinityPower = affinityPower;
    }

    public AffinityTypes getAffinityType() {
        return affinityType;
    }

    public int getAffinityPower() {
        return affinityPower;
    }

    public void setAffinityPower(int affinityPower) {
        this.affinityPower = affinityPower;
    }
}
