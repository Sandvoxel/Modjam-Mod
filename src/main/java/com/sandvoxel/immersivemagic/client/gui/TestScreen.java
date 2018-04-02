package com.sandvoxel.immersivemagic.client.gui;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.client.buttons.AffinityButtons;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.network.AffinityGuiPacket;
import com.sandvoxel.immersivemagic.common.network.lib.Network;
import com.sun.deploy.panel.AdvancedNetworkSettingsDialog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class TestScreen extends GuiScreen {

    final ResourceLocation textture = new ResourceLocation(Reference.MOD_ID,"textures/gui/affinity_selection.png");
    int texHeight = 114;
    int texWidth = 114;
    int centerX;
    int centerY;
    EntityPlayer player;

    AffinityButtons button;

    public TestScreen(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(textture);
        centerX = (width - texWidth) / 2;
        centerY = (height - texHeight) / 2;
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        //drawTexturedModalRect(centerX,centerY,0,0,texWidth,texHeight);
        drawModalRectWithCustomSizedTexture(centerX, centerY, 0, 0, 114, 114, texWidth, texHeight);


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(button = new AffinityButtons(0,30,30, player));
        buttonList.add(button = new AffinityButtons(1,60,30, player));
        buttonList.add(button = new AffinityButtons(2,90,30, player));
        buttonList.add(button = new AffinityButtons(3,120,30, player));
        buttonList.add(button = new AffinityButtons(4,30,60, player));
        buttonList.add(button = new AffinityButtons(5,30,90, player));
        buttonList.add(button = new AffinityButtons(6,30,120, player));
        buttonList.add(button = new AffinityButtons(7,120,120, player));

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            default:
                Network.sendToServer(new AffinityGuiPacket(button.id,1));
        }
        super.actionPerformed(button);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
