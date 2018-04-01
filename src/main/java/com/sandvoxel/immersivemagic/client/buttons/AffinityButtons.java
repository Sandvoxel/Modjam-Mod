package com.sandvoxel.immersivemagic.client.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;

public class AffinityButtons extends GuiButton {


    public AffinityButtons(int buttonId, int x, int y) {
        super(buttonId, x, y, "");
        width = 16;
        height =16;
        visible = false;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        super.mousePressed(mc, mouseX, mouseY);
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

    }
}
