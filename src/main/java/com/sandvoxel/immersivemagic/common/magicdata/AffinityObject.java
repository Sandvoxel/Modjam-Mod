package com.sandvoxel.immersivemagic.common.magicdata;

public class AffinityObject {
    private AffinityTypes affinityType;
    private int affinityLevel = 1;
    private int affinityMana=0;
    private int currentXP=0;
    private boolean isActive =false;


    public AffinityObject(AffinityTypes affinityType) {
        this.affinityType = affinityType;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public AffinityTypes getAffinityType() {
        return affinityType;
    }

    public int getAffinityMana() {
        if (affinityMana >= getManaCap())
            affinityMana = getManaCap();

        return affinityMana;
    }

    public int getManaCap() {
        return affinityLevel *200;
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

    public void setAffinityActive(boolean active) {
        isActive = active;
    }

    public void setCurrentXP(int currentXP) {
        this.currentXP = currentXP;
    }

    public boolean canLevelUp(){
        if(affinityLevel >= getPowerCap()){
            return false;
        }
        if(currentXP > (affinityLevel * affinityLevel)*5+200){
            affinityLevel++;
            currentXP = 0;
            return true;
        }
        return false;
    }

    public int getAffinityLevel() {
        return affinityLevel;
    }

    public void setAffinityLevel(int affinityLevel) {
        this.affinityLevel = affinityLevel;
    }
}
