package io.github.ultrusbot.lawofexchange.gui.energycondenser;

import io.github.ultrusbot.lawofexchange.emc.EmcController;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class EmcItemSlot extends Slot {
    public EmcItemSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return (EmcController.getEMC(stack.getItem()) != 0);
    }
}
