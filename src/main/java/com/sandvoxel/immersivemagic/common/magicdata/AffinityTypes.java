package com.sandvoxel.immersivemagic.common.magicdata;

import net.minecraft.util.EnumFacing;

public enum AffinityTypes {
    FIRE(0),
    WATER(1),
    ICE(2),
    AIR(3),
    EARTH(4),
    ENDER(5),
    LIGHT(6),
    DARKNESS(7);

    private static final AffinityTypes[] ID_LOOKUP = new AffinityTypes[values().length];
    private int ID;

    static {
        for (AffinityTypes tier : values()) {
            ID_LOOKUP[tier.ID] = tier;
        }
    }

    AffinityTypes(int ID) {
        this.ID = ID;
    }

    public static AffinityTypes getAffinity(int ID) {
        if (ID < 0 || ID >= ID_LOOKUP.length) {
            ID = 0;
        }

        return ID_LOOKUP[ID];
    }


    public static int getAffinityID(AffinityTypes affinityTypes){
        return affinityTypes.ID;
    }
}
