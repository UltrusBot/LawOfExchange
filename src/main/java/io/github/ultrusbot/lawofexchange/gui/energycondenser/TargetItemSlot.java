package io.github.ultrusbot.lawofexchange.gui.energycondenser;

import net.minecraft.inventory.Inventory;

public class TargetItemSlot extends EmcItemSlot {
    public TargetItemSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
