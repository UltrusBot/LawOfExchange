package io.github.ultrusbot.lawofexchange.emc;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.MathHelper;

/**
 * Used on items that store EMC in some way.
 * For items that aren't acting as a storage, but do use EMC, getMaxEMC should return MAX_INT
 */
public interface EMCStorageItem {
    default int getEMC(ItemStack itemStack) {
        CompoundTag tag = itemStack.getTag();
        return tag == null ? 0 : tag.getInt("EMC");
    }
    int getMaxEMC();
    default void setEMC(ItemStack itemStack, int emc) {
        itemStack.getOrCreateTag().putInt("EMC", emc);
    }
    default void addEMC(ItemStack itemStack, int emc) {
        int curEMC = getEMC(itemStack);
        curEMC+=emc;
        curEMC = MathHelper.clamp(curEMC, 0, getMaxEMC());
        setEMC(itemStack, curEMC);
    }
    default void removeEMC(ItemStack itemStack, int emc) {
        this.addEMC(itemStack, -emc);
    }
}
