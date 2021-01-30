package io.github.ultrusbot.lawofexchange.gui.furnace;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.FurnaceRecipeBookScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DarkMatterFurnaceScreen extends AbstractEMCFurnaceScreen<DarkMatterFurnaceScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(LawOfExchangeMod.MOD_ID, "textures/gui/dark_matter_furnace.png");

    public DarkMatterFurnaceScreen(DarkMatterFurnaceScreenHandler container, PlayerInventory inventory, Text title) {
        super(container, new FurnaceRecipeBookScreen(), inventory, title, TEXTURE);
    }
}
