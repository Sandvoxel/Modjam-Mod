package com.sandvoxel.immersivemagic.common.entity.render;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.common.entity.spells.SpellBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.Map;

public class SpellDefault extends Render {
    protected SpellDefault(RenderManager renderManager) {
        super(renderManager);
    }

    public static void registerRender(){
        RenderingRegistry.registerEntityRenderingHandler(SpellBase.class, new SpellDefault(Minecraft.getMinecraft().getRenderManager()));
    }


    private static final ResourceLocation texture = new ResourceLocation(Refrence.MOD_ID,"textures/misc/fire.png");

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(entity);
        GlStateManager.color(1,1,1,1);
        GlStateManager.pushMatrix();

        GlStateManager.translate(x,y,z);


        BufferBuilder vertexBuffer = Tessellator.getInstance().getBuffer();

        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(0.5,0.5,0.5);
        GL11.glNormal3f(0f,0f,0.5f);


        vertexBuffer.begin(7,DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
        vertexBuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
        vertexBuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
        vertexBuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
        Tessellator.getInstance().draw();

        GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);

        vertexBuffer.begin(7,DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
        vertexBuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
        vertexBuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
        vertexBuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
        Tessellator.getInstance().draw();

        GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);


        vertexBuffer.begin(7,DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
        vertexBuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
        vertexBuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
        vertexBuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
        Tessellator.getInstance().draw();

        GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);

        vertexBuffer.begin(7,DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
        vertexBuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
        vertexBuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
        vertexBuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
        Tessellator.getInstance().draw();

        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);

        vertexBuffer.begin(7,DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
        vertexBuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
        vertexBuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
        vertexBuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
        Tessellator.getInstance().draw();


        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);


        vertexBuffer.begin(7,DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(-2.0D, -2.0D, 0.0D).tex(0, 0).endVertex();
        vertexBuffer.pos(2.0D, -2.0D, 0.0D).tex(1, 0).endVertex();
        vertexBuffer.pos(2.0D, 2.0D, 0.0D).tex(1, 1).endVertex();
        vertexBuffer.pos(-2.0D, 2.0D, 0.0D).tex(0, 1).endVertex();
        Tessellator.getInstance().draw();

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }
}
