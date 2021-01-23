package io.github.ultrusbot.lawofexchange.items;

import net.minecraft.item.ItemStack;

public interface ModeSwitchingItem{
    void switchMode(ItemStack item);
    int getMode(ItemStack item);
}
