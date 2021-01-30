package io.github.ultrusbot.lawofexchange.gui.furnace;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public abstract class AbstractEMCFurnaceScreenHandler extends AbstractRecipeScreenHandler<Inventory> {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected final World world;
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final RecipeBookCategory category;

    protected AbstractEMCFurnaceScreenHandler(ScreenHandlerType<?> type, RecipeType<? extends AbstractCookingRecipe> recipeType, RecipeBookCategory recipeBookCategory, int i, PlayerInventory playerInventory) {
        this(type, recipeType, recipeBookCategory, i, playerInventory, new SimpleInventory(19), new ArrayPropertyDelegate(4));
    }

    protected AbstractEMCFurnaceScreenHandler(ScreenHandlerType<?> type, RecipeType<? extends AbstractCookingRecipe> recipeType, RecipeBookCategory recipeBookCategory, int i, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, i);
        this.recipeType = recipeType;
        this.category = recipeBookCategory;
        checkSize(inventory, 19);
        checkDataCount(propertyDelegate, 4);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.world = playerInventory.player.world;
        this.addSlot(new Slot(inventory, 0, 56, 17));
        this.addSlot(new EMCFurnaceFuelSlot(this, inventory, 1, 56, 53));
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 2, 112, 35));
        this.addSlot(new Slot(inventory, 3, 20, 9));
        this.addSlot(new Slot(inventory, 4, 20, 27));
        this.addSlot(new Slot(inventory, 5, 20, 45));
        this.addSlot(new Slot(inventory, 6, 20, 63));
        this.addSlot(new Slot(inventory, 7, 38, 9));
        this.addSlot(new Slot(inventory, 8, 38, 27));
        this.addSlot(new Slot(inventory, 9, 38, 45));
        this.addSlot(new Slot(inventory, 10, 38, 63));

        this.addSlot(new Slot(inventory, 11, 134, 9));
        this.addSlot(new Slot(inventory, 12, 134, 27));
        this.addSlot(new Slot(inventory, 13, 134, 45));
        this.addSlot(new Slot(inventory, 14, 134, 63));
        this.addSlot(new Slot(inventory, 15, 152, 9));
        this.addSlot(new Slot(inventory, 16, 152, 27));
        this.addSlot(new Slot(inventory, 17, 152, 45));
        this.addSlot(new Slot(inventory, 18, 153, 63));
        int l;
        for(l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, 84 + l * 18));
            }
        }

        for(l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
        }

        this.addProperties(propertyDelegate);
    }

    public void populateRecipeFinder(RecipeFinder finder) {
        if (this.inventory instanceof RecipeInputProvider) {
            ((RecipeInputProvider)this.inventory).provideRecipeInputs(finder);
        }

    }

    public void clearCraftingSlots() {
        this.inventory.clear();
    }

    public void fillInputSlots(boolean craftAll, Recipe<?> recipe, ServerPlayerEntity player) {
        (new FurnaceInputSlotFiller(this)).fillInputSlots(player, recipe, craftAll);
    }

    public boolean matches(Recipe<? super Inventory> recipe) {
        return recipe.matches(this.inventory, this.world);
    }

    public int getCraftingResultSlotIndex() {
        return 2;
    }

    public int getCraftingWidth() {
        return 1;
    }

    public int getCraftingHeight() {
        return 1;
    }

    @Environment(EnvType.CLIENT)
    public int getCraftingSlotCount() {
        return 3;
    }

    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index == 2) {
                if (!this.insertItem(itemStack2, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onStackChanged(itemStack2, itemStack);
            } else if (index != 1 && index != 0) {
                if (this.isSmeltable(itemStack2)) {
                    if (!this.insertItem(itemStack2, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemStack2)) {
                    if (!this.insertItem(itemStack2, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.insertItem(itemStack2, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.insertItem(itemStack2, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    protected boolean isSmeltable(ItemStack itemStack) {
        return this.world.getRecipeManager().getFirstMatch(this.recipeType, new SimpleInventory(new ItemStack[]{itemStack}), this.world).isPresent();
    }

    protected boolean isFuel(ItemStack itemStack) {
        Tag<Item> tag = TagRegistry.item(new Identifier(LawOfExchangeMod.MOD_ID, "emc_fuels"));
        return itemStack.getItem().isIn(tag);
    }

    @Environment(EnvType.CLIENT)
    public int getCookProgress() {
        int i = this.propertyDelegate.get(2);
        int j = this.propertyDelegate.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    @Environment(EnvType.CLIENT)
    public int getFuelProgress() {
        int i = this.propertyDelegate.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.propertyDelegate.get(0) * 13 / i;
    }

    @Environment(EnvType.CLIENT)
    public boolean isBurning() {
        return this.propertyDelegate.get(0) > 0;
    }

    @Environment(EnvType.CLIENT)
    public RecipeBookCategory getCategory() {
        return this.category;
    }
}
