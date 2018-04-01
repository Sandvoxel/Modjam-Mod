package com.sandvoxel.immersivemagic.common.spells.lib;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.api.spell.ISpellRegstier;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import com.sandvoxel.immersivemagic.common.spells.SpellTypes;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellEntityBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;

public class SpellBase extends ItemBase implements ISpellRegstier {
    private SpellTypes spellType;
    @Nullable
    private Class<? extends SpellEntityBase> entityClass;

    public SpellBase(String internalName, String resourcePath, SpellTypes spellType,Class<? extends SpellEntityBase> entityClass) {
        super(internalName, resourcePath);
        this.spellType = spellType;
        this.entityClass = entityClass;
    }


    @Override
    public SpellTypes getSpellType() {
        return spellType;
    }

    @Override
    public void RegstierSpellEntity(int id) {
        ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID+":spell_" + getInteneralName());
        EntityRegistry.registerModEntity(resourceLocation, entityClass,"spell",id, Reference.MOD_ID,64,10,true);
    }
}
