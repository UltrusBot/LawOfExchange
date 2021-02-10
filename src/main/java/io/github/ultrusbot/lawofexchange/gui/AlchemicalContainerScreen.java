package io.github.ultrusbot.lawofexchange.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AlchemicalContainerScreen extends HandledScreen<AlchemicalContainerScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(LawOfExchangeMod.MOD_ID, "textures/gui/alchemical_chest.png");

    public AlchemicalContainerScreen(AlchemicalContainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.of(""));
        this.backgroundWidth = 245;
        this.backgroundHeight = 238;

    }
    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
    }
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(TEXTURE);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }
}
