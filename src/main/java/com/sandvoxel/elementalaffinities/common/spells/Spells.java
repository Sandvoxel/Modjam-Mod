package com.sandvoxel.elementalaffinities.common.spells;


import com.sandvoxel.elementalaffinities.common.spells.lib.SpellBase;
import com.sandvoxel.elementalaffinities.common.util.RegistryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum  Spells {
    LIGHT_SPELL(LightSpell.class),
    MINING_SPELL(MiningSpell.class),
    LIQUEFACT_SPELL(LiquefactSpell.class),
    NOVA_SPELL(NovaSpell.class);

    private Class<? extends SpellBase> spellItemClass;
    private Item item;


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
        item = RegistryHelper.spellRegstration(spellItemClass,id);
    }

    public ItemStack getitemStack(){
        return new ItemStack(item);
    }
}
