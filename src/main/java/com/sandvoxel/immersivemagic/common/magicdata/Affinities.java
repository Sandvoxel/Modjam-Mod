package com.sandvoxel.immersivemagic.common.magicdata;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class Affinities implements IAffinities {
    //The player that has the affinity
    private EntityPlayer player;
    //The players affinitys
    private List<AffinitieTypes> affinitieTypes = new ArrayList<>();


    @Override
    public List<AffinitieTypes> getPlayerAffinities(EntityPlayer player) {
        return affinitieTypes;
    }


}
