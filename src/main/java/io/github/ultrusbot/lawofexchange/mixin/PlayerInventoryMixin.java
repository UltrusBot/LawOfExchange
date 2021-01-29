package io.github.ultrusbot.lawofexchange.mixin;


import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.emc.EMC_Controller;
import io.github.ultrusbot.lawofexchange.items.KleinStarItem;
import io.github.ultrusbot.lawofexchange.items.PlayerInventoryAccess;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Iterator;
import java.util.List;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin implements PlayerInventoryAccess {

    @Shadow @Final private List<DefaultedList<ItemStack>> combinedInventory;


    @Unique
    public ItemStack getKleinStarItem() {
        Iterator var2 = this.combinedInventory.iterator();

        while(var2.hasNext()) {
            List<ItemStack> list = (List)var2.next();
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                ItemStack itemStack = (ItemStack)var4.next();
                if (!itemStack.isEmpty() && itemStack.getItem() instanceof KleinStarItem) {
                    return itemStack;
                }
            }
        }

        return ItemStack.EMPTY;

    }

    @Override
    public ItemStack getFuelItem() {
        Iterator var2 = this.combinedInventory.iterator();
        Tag<Item> tag = TagRegistry.item(new Identifier(LawOfExchangeMod.MOD_ID, "emc_fuels"));

        while(var2.hasNext()) {
            List<ItemStack> list = (List)var2.next();
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                ItemStack itemStack = (ItemStack)var4.next();
                if (!itemStack.isEmpty() && itemStack.getItem().isIn(tag)) {
                    return itemStack;
                }
            }
        }

        return ItemStack.EMPTY;
    }
    public ItemStack searchForItem(Item item) {
        Iterator var2 = this.combinedInventory.iterator();
        Tag<Item> tag = TagRegistry.item(new Identifier(LawOfExchangeMod.MOD_ID, "emc_fuels"));

        while(var2.hasNext()) {
            List<ItemStack> list = (List)var2.next();
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                ItemStack itemStack = (ItemStack)var4.next();
                if (!itemStack.isEmpty() && itemStack.getItem() == item) {
                    return itemStack;
                }
            }
        }

        return ItemStack.EMPTY;
    }
    public ItemStack searchForItemFromTag(Tag<Item> tag) {
        Iterator var2 = this.combinedInventory.iterator();
        while(var2.hasNext()) {
            List<ItemStack> list = (List)var2.next();
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                ItemStack itemStack = (ItemStack)var4.next();
                if (!itemStack.isEmpty() && itemStack.getItem().isIn(tag)) {
                    return itemStack;
                }
            }
        }

        return ItemStack.EMPTY;

    }

    @Override
    public ItemStack getItemWithLessEMC(int emc) {
        Iterator var2 = this.combinedInventory.iterator();
        while(var2.hasNext()) {
            List<ItemStack> list = (List)var2.next();
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                ItemStack itemStack = (ItemStack)var4.next();
                if (!itemStack.isEmpty() && EMC_Controller.getEMC(itemStack.getItem()) != 0&&EMC_Controller.getEMC(itemStack.getItem()) < emc) {
                    return itemStack;
                }
            }
        }

        return ItemStack.EMPTY;
    }
}
