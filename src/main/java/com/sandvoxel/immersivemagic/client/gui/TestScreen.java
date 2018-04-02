package com.sandvoxel.immersivemagic.client.gui;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
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
    int texWidth = 114;
    int texHeight = 114;
    int centerX = (width - texWidth) / 2;
    int centerY = (height - texHeight) / 2;
    int buttonCenterX;
    int buttonCenterY;
    EntityPlayer player;

    AffinityButtons button;

    public TestScreen(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(textture);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        centerX = (this.width - texWidth) / 2;
        centerY = (this.height - texHeight) / 2;

        //drawTexturedModalRect(centerX,centerY,0,0,texWidth,texHeight);
        drawModalRectWithCustomSizedTexture(centerX, centerY, 0, 0, 114, 114, texWidth, texHeight);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {

        buttonCenterX = this.width / 2;
        buttonCenterY = this.height / 2;
        buttonList.clear();
        buttonList.add(button = new AffinityButtons(0,buttonCenterX - 28,buttonCenterY - 56, player));
        buttonList.add(button = new AffinityButtons(1,buttonCenterX + 12,buttonCenterY - 56, player));
        buttonList.add(button = new AffinityButtons(2,buttonCenterX - 56,buttonCenterY - 28, player));
        buttonList.add(button = new AffinityButtons(3,buttonCenterX + 40,buttonCenterY - 28, player));
        buttonList.add(button = new AffinityButtons(4,buttonCenterX - 56,buttonCenterY + 11, player));
        buttonList.add(button = new AffinityButtons(5,buttonCenterX + 40,buttonCenterY + 11, player));
        buttonList.add(button = new AffinityButtons(6,buttonCenterX - 28,buttonCenterY + 40, player));
        buttonList.add(button = new AffinityButtons(7,buttonCenterX + 12,buttonCenterY + 40, player));

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            default:
                Network.sendToServer(new AffinityGuiPacket(button.id,1));
                initGui();
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
