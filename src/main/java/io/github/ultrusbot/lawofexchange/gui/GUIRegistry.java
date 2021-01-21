package io.github.ultrusbot.lawofexchange.gui;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.gui.alchemical_chest.AlchemicalChestGuiDescription;
import io.github.ultrusbot.lawofexchange.gui.alchemical_chest.AlchemicalChestScreen;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class GUIRegistry {
    public static ScreenHandlerType<AlchemicalChestGuiDescription> ALCHEMICAL_CHEST_SCREEN_HANDLER_TYPE;

    public static void register() {
        ALCHEMICAL_CHEST_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_chest"), (syncId, inventory) -> new AlchemicalChestGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY));
    }
}
