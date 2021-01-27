package io.github.ultrusbot.lawofexchange.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public interface ChargeableItem {
    default int getCharge(ItemStack item) {
        return item.getOrCreateTag().getInt("charge");
    }
    default void increaseCharge(ItemStack item, int amount) {
        int charge = MathHelper.clamp(getCharge(item) + amount, 0, getMaxCharge());
        item.getOrCreateTag().putInt("charge", charge);
    }
    int getMaxCharge();
}
