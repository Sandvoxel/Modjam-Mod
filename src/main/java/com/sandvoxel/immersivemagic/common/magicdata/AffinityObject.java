package com.sandvoxel.immersivemagic.common.magicdata;

public class AffinityObject {
    private AffinityTypes affinityType;
    private int affinityPower;
    private int affinityMana=0;
    private int currentXP=0;


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

    public int getCurrentXP() {
        return currentXP;
    }

    public void setCurrentXP(int currentXP) {
        this.currentXP = currentXP;
    }

    public boolean canLevelUp(){
        if(!(affinityPower >= getPowerCap())){
            return false;
        }
        if(currentXP > Math.sqrt(affinityPower)+200){
            affinityPower++;
            currentXP = 0;
            return true;
        }
        return false;
    }

    public int getAffinityPower() {
        return affinityPower;
    }

    public void setAffinityPower(int affinityPower) {
        this.affinityPower = affinityPower;
    }
}
