package io.github.ultrusbot.lawofexchange.items;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public interface ModeSwitchingItem{
    default void switchMode(ItemStack item) {
        CompoundTag tag = item.getOrCreateTag();
        int currentMode = tag.getInt("mode");
        currentMode += 1;
        currentMode %= getModeCount();
        tag.putInt("mode", currentMode);
        item.getOrCreateTag().putInt("CustomModelData", currentMode);
    }
    default int getMode(ItemStack item) {
        CompoundTag tag = item.getTag();
        return tag == null ? 0 : tag.getInt("mode");
    }
    default int getModeCount() {
        return 2;
    }
}
