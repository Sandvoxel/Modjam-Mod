package com.sandvoxel.immersivemagic.common.magicdata;

public class AffinityObject {
    private AffinityTypes affinityType;
    private int affinityPower;
    private int affinityMana=0;


    public AffinityObject(AffinityTypes affinityType, int affinityPower) {
        this.affinityType = affinityType;
        this.affinityPower = affinityPower;
    }

    public AffinityTypes getAffinityType() {
        return affinityType;
    }

    public int getAffinityMana() {
        return affinityMana;
    }

    public int getManaCap() {
        return affinityPower*200;
    }

    public int getPowerCap(){
        return 32;
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
