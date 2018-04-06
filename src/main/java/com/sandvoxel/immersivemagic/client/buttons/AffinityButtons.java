package com.sandvoxel.immersivemagic.client.buttons;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
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

    public AffinityButtons(int buttonId, int x, int y, EntityPlayer player) {
        super(buttonId, x, y, "");
        id = buttonId;
        width = 18;
        height =18;
        visible = false;
        this.player = player;
    }


    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        IAffinities affinities = player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);

        mc.getTextureManager().bindTexture(texture);

        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

        texXStart = (float)id * 18.0f;

        texYStart = 0.0f;
        if(affinities.hasAffinity(AffinityTypes.getAffinity(id))){
            texYStart = 18.0f;
        } else {
            texYStart = 36.0f;
        }if (this.hovered)
        {
            texYStart = 54.0f;
        }

        drawModalRectWithCustomSizedTexture(this.x, this.y, texXStart, texYStart, this.width, this.height, 144.0f, 72.0f);

        super.drawButton(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        super.mousePressed(mc, mouseX, mouseY);
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }
}
