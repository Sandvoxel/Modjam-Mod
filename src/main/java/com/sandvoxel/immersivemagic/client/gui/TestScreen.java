package com.sandvoxel.immersivemagic.client.gui;

import com.sandvoxel.immersivemagic.Refrence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GLContext;

import java.io.IOException;

public class TestScreen extends GuiScreen {

    final ResourceLocation textture = new ResourceLocation(Refrence.MOD_ID,"textures/gui/affinity_selection.png");
    int texHeight = 114;
    int texWidth = 114;
    int centerX;
    int centerY;



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
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
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
