package com.sandvoxel.immersivemagic.common.magicdata;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Affinities implements IAffinities {
    //The player that has the affinity
    private EntityPlayer player;
    //The players affinitys
    private List<AffinityTypes> affinitieTypes = new ArrayList<>();

    public Affinities(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public List<AffinityTypes> getPlayerAffinities() {
        return affinitieTypes;
    }

    @Override
    public void addAffinities(AffinityTypes affinityTypes) {
        this.affinitieTypes.add(affinityTypes);
    }

    @Override
    public void setPlayerAffinitiesFromID(int[] IDs) {
        affinitieTypes.clear();
        for(int id : IDs){
            affinitieTypes.add(AffinityTypes.getAffinity(id));
        }
    }

}
