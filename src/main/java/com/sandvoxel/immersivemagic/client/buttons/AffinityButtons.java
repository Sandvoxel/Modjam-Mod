package com.sandvoxel.immersivemagic.client.buttons;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class AffinityButtons extends GuiButton {

    private int id = 0;
    private EntityPlayer player;
    final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID,"textures/gui/affinity_list.png");
    private float texXStart = 0f;
    private float texYStart = 0f;
    protected IAffinities affinities;

    public AffinityButtons(int buttonId, int x, int y, EntityPlayer player, IAffinities affinities) {
        super(buttonId, x, y, "");
        id = buttonId;
        width = 18;
        height =18;
        visible = true;
        this.affinities = affinities;
        this.player = player;
    }

    public int getButtonID() {
        return id;
    }

    public IAffinities getAffinityInfo() {
        return affinities;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible)
        {

            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(texture);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            texXStart = (float)id * 18.0f;
            //this.drawTexturedModalRect(this.x, this.y, texXStart, texYStart, this.width, this.height);
            //this.drawTexturedModalRect(this.x + this.width, this.y, texXStart, texYStart, this.width, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

        //Logic for how the button should render if the player has the affinity
            texYStart = 0.0f;
            if(affinities.hasAffinity(AffinityTypes.getAffinity(id))){
                texYStart = 18.0f;
            } else {
                texYStart = 36.0f;
            }
        //Other button logic
            if (packedFGColour != 0)
            {
                j = packedFGColour;
                texYStart = 0.0f;
            }
            else
            if (!this.enabled)
            {
                j = 10526880;
                texYStart = 0.0f;
            }
            else if (this.hovered)
            {

                j = 16777120;
                texYStart = 54.0f;
            } else {

            }


            //Actual drawing of the texture
            drawModalRectWithCustomSizedTexture(this.x, this.y, texXStart, texYStart, this.width, this.height, 144.0f, 72.0f);

            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        super.mousePressed(mc, mouseX, mouseY);
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }
}
