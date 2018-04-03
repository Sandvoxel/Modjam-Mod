package com.sandvoxel.immersivemagic.common.items;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;

/**
 * Created by CrazyGrape on 4/3/2018.
 */
public class crystalBall extends ItemBase {

    public crystalBall() {
        super("crystal_ball", "spell");
        setCreativeTab(ImmersiveMagic.tabimmmag);
    }
}