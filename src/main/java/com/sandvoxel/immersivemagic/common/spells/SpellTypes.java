package com.sandvoxel.immersivemagic.common.spells;

public enum  SpellTypes {
    THROWABLE_SPELL,
    DIRECT_SPELL,
    RAYCAST_SPELL;

    private static final SpellTypes[] META_LOOKUP = new SpellTypes[values().length];

    static {
        for (SpellTypes spell : values()) {
            META_LOOKUP[spell.getMeta()] = spell;
        }
    }

    public int getMeta() {
        return this.ordinal();
    }

    public static SpellTypes byMeta(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }
}
