package com.sandvoxel.elementalaffinities.api.spell;

import com.sandvoxel.elementalaffinities.common.spells.SpellTypes;

public interface ISpellRegstier {
    SpellTypes getSpellType();

    void RegisterSpellEntity(int id);
}
