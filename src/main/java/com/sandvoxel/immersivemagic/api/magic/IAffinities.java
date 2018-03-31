package com.sandvoxel.immersivemagic.api.magic;

import com.sandvoxel.immersivemagic.common.magicdata.AffinitieTypes;
import com.sandvoxel.immersivemagic.common.magicdata.Affinities;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public interface IAffinities {
    public List<AffinitieTypes>getPlayerAffinities(EntityPlayer player);
}
