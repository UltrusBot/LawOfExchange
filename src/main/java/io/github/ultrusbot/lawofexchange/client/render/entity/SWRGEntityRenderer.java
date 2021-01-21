package io.github.ultrusbot.lawofexchange.client.render.entity;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.entity.projectile.SwiftwolfsRendingGaleProjectileEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;

@Environment(EnvType.CLIENT)
public class SWRGEntityRenderer extends EntityRenderer<SwiftwolfsRendingGaleProjectileEntity> {
    private static final Identifier TEXTURE = new Identifier(LawOfExchangeMod.MOD_ID,"textures/entity/swrg_projectile.png");
    private static final RenderLayer LAYER;

    public SWRGEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }


    @Override
    protected int getBlockLight(SwiftwolfsRendingGaleProjectileEntity entity, BlockPos blockPos) {
        return 15;
    }

    @Override
    public void render(SwiftwolfsRendingGaleProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(this.dispatcher.getRotation());
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix4f = entry.getModel();
        Matrix3f matrix3f = entry.getNormal();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        produceVertex(vertexConsumer, matrix4f, matrix3f, light, 0.0F, 0, 0, 1);
        produceVertex(vertexConsumer, matrix4f, matrix3f, light, 1.0F, 0, 1, 1);
        produceVertex(vertexConsumer, matrix4f, matrix3f, light, 1.0F, 1, 1, 0);
        produceVertex(vertexConsumer, matrix4f, matrix3f, light, 0.0F, 1, 0, 0);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }


    private static void produceVertex(VertexConsumer vertexConsumer, Matrix4f modelMatrix, Matrix3f normalMatrix, int light, float x, int y, int textureU, int textureV) {
        vertexConsumer.vertex(modelMatrix, x - 0.5F, (float)y - 0.25F, 0.0F).color(255, 255, 255, 255).texture((float)textureU, (float)textureV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0.0F, 1.0F, 0.0F).next();
    }

    @Override
    public Identifier getTexture(SwiftwolfsRendingGaleProjectileEntity entity) {
        return TEXTURE;
    }


    static {
        LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);
    }
}

