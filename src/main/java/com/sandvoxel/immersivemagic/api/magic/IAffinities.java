package com.sandvoxel.immersivemagic.api.magic;

import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public interface IAffinities {
    public List<AffinityTypes>getPlayerAffinities();

    public void setPlayerAffinitiesFromID(int[] IDs);
}
