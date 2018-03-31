package com.sandvoxel.immersivemagic.common.magicdata;

public enum AffinityTypes {
    FIRE(0,"fire"),
    WATER(1,"water"),
    ICE(2,"ice"),
    AIR(3,"air"),
    EARTH(4,"earth"),
    ENDER(5,"ender"),
    LIGHT(6,"light"),
    DARKNESS(7,"darkness");

    private static final AffinityTypes[] ID_LOOKUP = new AffinityTypes[values().length];
    private int ID;
    private String Name;

    static {
        for (AffinityTypes tier : values()) {
            ID_LOOKUP[tier.ID] = tier;
        }
    }

    AffinityTypes(int ID, String name) {
        this.ID = ID;
        this.Name = name;
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
