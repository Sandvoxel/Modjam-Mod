package com.sandvoxel.immersivemagic.common.spells.lib;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.api.spell.ISpellRegstier;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.spells.SpellTypes;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;

public class SpellBase extends ItemBase implements ISpellRegstier {

    private SpellTypes spellType;

    protected AffinityTypes spellAffType = AffinityTypes.EARTH;
    protected int baseManaCost = 80;

    @Nullable
    private Class<? extends SpellEntityBase> entityClass;

    public SpellBase(String internalName, String resourcePath, SpellTypes spellType,Class<? extends SpellEntityBase> entityClass, AffinityTypes spellAffinityType, int baseManaCost) {
        super(internalName, resourcePath);
        this.spellAffType = spellAffinityType;
        this.baseManaCost = baseManaCost;
        this.spellType = spellType;
        this.entityClass = entityClass;
        this.setCreativeTab(ImmersiveMagic.tabimmmag);
        this.setMaxStackSize(1);
    }

    @Override
    public SpellTypes getSpellType() {
        return spellType;
    }

    protected void dispNoAffinity(EntityPlayer playerIn, int affTypeID) {
        playerIn.sendStatusMessage(new TextComponentTranslation("You need the " + AffinityTypes.getAffinity(affTypeID).getName() + " affinity to cast this spell!", new Object[0]), true);
    }

    protected void dispOutOfMana(EntityPlayer playerIn, int affTypeID, int mana, int manaCost) {
        playerIn.sendStatusMessage(new TextComponentTranslation("You only have " + mana + " of the required " + manaCost + " " + AffinityTypes.getAffinity(affTypeID).getName() + " mana to cast this spell!", new Object[0]), true);
    }
    

    @Override
    public void RegisterSpellEntity(int id) {
        ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID + ":spell_" + getInteneralName());
        EntityRegistry.registerModEntity(resourceLocation, entityClass,"spell",id, Reference.MOD_ID,64,10,true);
    }
}
