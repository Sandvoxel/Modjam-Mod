package com.sandvoxel.immersivemagic.client.gui;

import com.sandvoxel.immersivemagic.Refrence;
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
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class TestScreen extends GuiScreen {

    final ResourceLocation textture = new ResourceLocation(Refrence.MOD_ID,"textures/gui/affinity_selection.png");
    int texHeight = 114;
    int texWidth = 114;
    int centerX;
    int centerY;

    AffinityButtons button;


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
        buttonList.add(button = new AffinityButtons(0,30,30));
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 0:
                Network.sendToServer(new AffinityGuiPacket(AffinityTypes.LIGHT.getMeta(),1,100));
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
