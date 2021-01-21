package io.github.ultrusbot.lawofexchange.emc;

import net.minecraft.item.ItemStack;

/**
 * Used on items that store EMC in some way.
 */
public interface EMCStorageItem {
    public int getEMC(ItemStack itemStack);
    public int getMaxEMC();
    public void setEMC(ItemStack itemStack, int emc);
    public void addEMC(ItemStack itemStack, int emc);
    public void removeEMC(ItemStack itemStack, int emc);

}
