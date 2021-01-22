package io.github.ultrusbot.lawofexchange.emc;

import net.minecraft.item.ItemStack;

/**
 * Used on items that store EMC in some way.
 * For items that aren't acting as a storage, but do use EMC, getMaxEMC should return MAX_INT
 */
public interface EMCStorageItem {
    int getEMC(ItemStack itemStack);
    int getMaxEMC();
    void setEMC(ItemStack itemStack, int emc);
    void addEMC(ItemStack itemStack, int emc);
    void removeEMC(ItemStack itemStack, int emc);

}
