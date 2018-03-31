package com.sandvoxel.immersivemagic.api.magic;

import com.sandvoxel.immersivemagic.common.magicdata.AffinityObject;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagString;

import java.util.List;

public interface IAffinities {
    public List<AffinityObject>getPlayerAffinities();

    public void addAffinities(AffinityObject affinityTypes);

    public void setPlayerAffinitiesFromNBT(NBTTagString nbtTagString);
}
