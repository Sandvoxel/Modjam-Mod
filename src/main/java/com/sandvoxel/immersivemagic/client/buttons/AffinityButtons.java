package com.sandvoxel.immersivemagic.client.buttons;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;

public class AffinityButtons extends GuiButton {

    private EntityPlayer player;

    public AffinityButtons(int buttonId, int x, int y, EntityPlayer player) {
        super(buttonId, x, y, "");
        width = 16;
        height =16;
        visible = true;
        this.player = player;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        IAffinities affinities = player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);

        if(affinities.hasAffinity(AffinityTypes.getAffinity(id))){
            displayString = "O";
        }else {
            displayString = "X";
        }


        super.drawButton(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        super.mousePressed(mc, mouseX, mouseY);
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

    }
}
