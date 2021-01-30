package io.github.ultrusbot.lawofexchange.gui.furnace;

import io.github.ultrusbot.lawofexchange.gui.GUIRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public class DarkMatterFurnaceScreenHandler extends AbstractEMCFurnaceScreenHandler {
    public DarkMatterFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(GUIRegistry.DARK_MATTER_FURNACE_SCREEN_HANDLER, RecipeType.SMELTING, RecipeBookCategory.FURNACE, syncId, playerInventory);
    }

    public DarkMatterFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(GUIRegistry.DARK_MATTER_FURNACE_SCREEN_HANDLER, RecipeType.SMELTING, RecipeBookCategory.FURNACE, syncId, playerInventory, inventory, propertyDelegate);
    }

}
