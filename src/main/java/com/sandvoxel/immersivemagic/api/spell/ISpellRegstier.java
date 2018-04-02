package com.sandvoxel.immersivemagic.api.spell;

import com.sandvoxel.immersivemagic.common.spells.SpellTypes;

public interface ISpellRegstier {
    SpellTypes getSpellType();

    void RegisterSpellEntity(int id);
}
