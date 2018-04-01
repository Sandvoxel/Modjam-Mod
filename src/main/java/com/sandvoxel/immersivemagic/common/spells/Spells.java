package com.sandvoxel.immersivemagic.common.spells;


import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.common.items.Items;
import com.sandvoxel.immersivemagic.common.spells.lib.SpellBase;
import com.sandvoxel.immersivemagic.common.util.RegistryHelper;

public enum  Spells {
    LIGHT_SPELL(newSpell.class);

    private Class<? extends SpellBase> spellItemClass;


    Spells(Class<? extends SpellBase> spellItemClass) {
        this.spellItemClass = spellItemClass;
    }
    public static void registerSpells() {
        int id = 0;
        for (Spells spell : Spells.values()) {
            spell.registerSpell(id);
            id++;
        }
    }
    private void registerSpell(int id) {
        RegistryHelper.spellRegstration(spellItemClass,id);
    }
}
