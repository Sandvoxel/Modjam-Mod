package com.sandvoxel.immersivemagic.client.render;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellLight;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellLiquefact;
import com.sandvoxel.immersivemagic.common.spells.entity.SpellNova;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class SpellDefault extends Render {

    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID,"textures/misc/orb_cosmic_ring.png");
    private static String texFire= "textures/entities/orb_fire.png";
    private static String texWater = "textures/entities/orb_water.png";
    private static String texAir = "textures/entities/orb_air.png";
    private static String texEarth = "textures/entities/orb_earth.png";
    private static String texEnder = "textures/entities/orb_ender.png";
    private static String texLight = "textures/entities/orb_light.png";
    private static String texDarkness = "textures/entities/orb_darkness.png";
    private static String texFrost = "textures/entities/orb_frost.png";
    protected static boolean hasShading = true;

    protected SpellDefault(RenderManager renderManager) {
        super(renderManager);
        hasShading = false;
    }

    protected SpellDefault(RenderManager renderManager, String textureLocation, boolean hasShading) {
        super(renderManager);
        texture = new ResourceLocation(Reference.MOD_ID,textureLocation);
        SpellDefault.hasShading = hasShading;
    }

    public static void registerRender(){
        RenderingRegistry.registerEntityRenderingHandler(SpellLight.class, new SpellDefault(Minecraft.getMinecraft().getRenderManager(), texLight, false));
        RenderingRegistry.registerEntityRenderingHandler(SpellLiquefact.class, new SpellDefault(Minecraft.getMinecraft().getRenderManager(), texEarth, true));
        RenderingRegistry.registerEntityRenderingHandler(SpellNova.class, new SpellDefault(Minecraft.getMinecraft().getRenderManager(), texFire, false));
    }



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
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        GlStateManager.translate(x,y,z);
        if (!hasShading) {
            GL11.glDisable(GL11.GL_LIGHTING);

        }


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

        GL11.glEnable(GL11.GL_LIGHTING);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }
}
