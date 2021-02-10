package io.github.ultrusbot.lawofexchange.gui.energycondenser;

import io.github.ultrusbot.lawofexchange.gui.GUIRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

import java.lang.annotation.Target;

public class EnergyCondenserScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public EnergyCondenserScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(92), new ArrayPropertyDelegate(1));
    }

    public EnergyCondenserScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(GUIRegistry.ENERGY_CONDENSER_SCREEN_HANDLER, syncId);
        this.propertyDelegate = propertyDelegate;
        checkSize(inventory, 92);
        checkDataCount(propertyDelegate, 1);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.addSlot(new TargetItemSlot(inventory, 0, 7, 5));
        for(int y = 0; y < 7; y++) {
            for(int x = 0; x < 13; x++) {
                this.addSlot(new EmcItemSlot(inventory,  1 + x + y * 13, 7 + x * 18, 26 + y * 18));
            }
        }
        int l;
        for(l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 43 + k * 18, 155 + l * 18));
            }
        }

        for(l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 43 + l * 18, 213));
        }
        this.addProperties(propertyDelegate);
    }
    public int getEMC() {
        return propertyDelegate.get(0);
    }
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;

        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index < 7 * 13) {
                if (!this.insertItem(itemStack2, 7 * 13, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 0, 7 * 13, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }
    public void close(PlayerEntity player) {
        super.close(player);
        this.inventory.onClose(player);
    }

    @Override
    public boolean canUse(PlayerEntity player) { return this.inventory.canPlayerUse(player); }
}
