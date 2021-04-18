package io.github.ultrusbot.lawofexchange.client.render.entity;

import io.github.ultrusbot.lawofexchange.block.BlockRegistry;
import io.github.ultrusbot.lawofexchange.entity.explosives.NovaCataclysmEntity;
import io.github.ultrusbot.lawofexchange.entity.explosives.NovaCatalystEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class NovaCataclysmRenderer extends EntityRenderer<NovaCataclysmEntity> {
    public NovaCataclysmRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
        this.shadowRadius = 0.5F;
    }

    public void render(NovaCataclysmEntity novaCataclysmEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.5D, 0.0D);
        if ((float)novaCataclysmEntity.getFuseTimer() - g + 1.0F < 10.0F) {
            float h = 1.0F - ((float)novaCataclysmEntity.getFuseTimer() - g + 1.0F) / 10.0F;
            h = MathHelper.clamp(h, 0.0F, 1.0F);
            h *= h;
            h *= h;
            float j = 1.0F + h * 0.3F;
            matrixStack.scale(j, j, j);
        }

        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
        matrixStack.translate(-0.5D, -0.5D, 0.5D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        TntMinecartEntityRenderer.renderFlashingBlock(BlockRegistry.NOVA_CATALYST.getDefaultState(), matrixStack, vertexConsumerProvider, i, novaCataclysmEntity.getFuseTimer() / 5 % 2 == 0);
        matrixStack.pop();
        super.render(novaCataclysmEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(NovaCataclysmEntity novaCataclysmEntity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}

