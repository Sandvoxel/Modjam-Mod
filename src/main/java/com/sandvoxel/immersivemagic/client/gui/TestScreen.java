package com.sandvoxel.immersivemagic.client.gui;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.client.buttons.AffinityButtons;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.network.AffinityGuiPacket;
import com.sandvoxel.immersivemagic.common.network.lib.Network;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;

import java.io.IOException;


public class TestScreen extends GuiBase {

    final ResourceLocation textture = new ResourceLocation(Reference.MOD_ID,"textures/gui/affinity_selection.png");
    int texWidth = 114;
    int texHeight = 114;
    int centerX = (width - texWidth) / 2;
    int centerY = (height - texHeight) / 2;
    int buttonCenterX;
    int buttonCenterY;
    EntityPlayer player;

    AffinityButtons button;
    Rectangle buttonExtents;

    public TestScreen(EntityPlayer player) {
        super(Reference.MOD_ID, new Container() {
            @Override
            public boolean canInteractWith(EntityPlayer playerIn) {
                return true;
            }
        });
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

        Point currentMouse = new Point(mouseX - guiLeft, mouseY - guiTop);
        
    }

    @Override
    public void initGui() {

        buttonCenterX = this.width / 2;
        buttonCenterY = this.height / 2;
        buttonList.clear();
        IAffinities playerAffinities = player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);

        buttonList.add(button = new AffinityButtons(AffinityTypes.LIGHT.getMeta(),  buttonCenterX - 30,  buttonCenterY - 58, player, playerAffinities));
        buttonList.add(button = new AffinityButtons(AffinityTypes.FIRE.getMeta(),   buttonCenterX + 11,  buttonCenterY - 58, player, playerAffinities));
        buttonList.add(button = new AffinityButtons(AffinityTypes.EARTH.getMeta(),  buttonCenterX - 58,  buttonCenterY - 29, player, playerAffinities));
        buttonList.add(button = new AffinityButtons(AffinityTypes.ENDER.getMeta(),  buttonCenterX + 40,  buttonCenterY - 29, player, playerAffinities));
        buttonList.add(button = new AffinityButtons(AffinityTypes.WATER.getMeta(),  buttonCenterX - 58,  buttonCenterY + 11, player, playerAffinities));
        buttonList.add(button = new AffinityButtons(AffinityTypes.AIR.getMeta(),    buttonCenterX + 40,  buttonCenterY + 11, player, playerAffinities));
        buttonList.add(button = new AffinityButtons(AffinityTypes.ICE.getMeta(),    buttonCenterX - 29,  buttonCenterY + 40, player, playerAffinities));
        buttonList.add(button = new AffinityButtons(AffinityTypes.DARKNESS.getMeta(),buttonCenterX + 11, buttonCenterY + 40, player, playerAffinities));

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            default:
                    Network.sendToServer(new AffinityGuiPacket(button.id, 1));
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

    @Override
    public void drawBG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {

    }

    @Override
    public void drawFG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {

    }
}
