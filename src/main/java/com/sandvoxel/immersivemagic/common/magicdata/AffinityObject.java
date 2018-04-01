package com.sandvoxel.immersivemagic.common.magicdata;

public class AffinityObject {
    private AffinityTypes affinityType;
    private int affinityPower;
    private int affinityMana;

    public AffinityObject(AffinityTypes affinityType, int affinityPower,int affinityMana) {
        this.affinityType = affinityType;
        this.affinityPower = affinityPower;
        this.affinityMana = affinityMana;
    }

    public AffinityTypes getAffinityType() {
        return affinityType;
    }

    public int getAffinityMana() {
        return affinityMana;
    }

    public void setAffinityMana(int affinityMana) {
        this.affinityMana = affinityMana;
    }

    private void subtractAffinityMana(int affinityMana){
        this.affinityMana -= affinityMana;
    }

    public int getAffinityPower() {
        return affinityPower;
    }

    public void setAffinityPower(int affinityPower) {
        this.affinityPower = affinityPower;
    }
}
