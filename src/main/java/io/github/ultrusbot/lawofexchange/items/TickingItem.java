package io.github.ultrusbot.lawofexchange.items;

import net.minecraft.item.ItemStack;

public interface TickingItem {
    default void setTickCount(ItemStack itemStack, int count) {
        itemStack.getOrCreateTag().putInt("tickCount", count);

    }
    default int getTickCount(ItemStack itemStack) {
        return itemStack.getOrCreateTag().getInt("tickCount");
    }
}
