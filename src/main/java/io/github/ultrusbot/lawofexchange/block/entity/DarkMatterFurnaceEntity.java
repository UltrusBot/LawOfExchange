package io.github.ultrusbot.lawofexchange.block.entity;

import io.github.ultrusbot.lawofexchange.gui.furnace.DarkMatterFurnaceScreenHandler;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class DarkMatterFurnaceEntity extends AbstractEMCFurnaceBlockEntity {
    public DarkMatterFurnaceEntity() {
        super(BlockEntityRegistry.DARK_MATTER_FURNACE_ENTITY, RecipeType.SMELTING);
    }

    protected Text getContainerName() {
        return new TranslatableText("lawofexchange.container.dark_matter_furnace");
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new DarkMatterFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}