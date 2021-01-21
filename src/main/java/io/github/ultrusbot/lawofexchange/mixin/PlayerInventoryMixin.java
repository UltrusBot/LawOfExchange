package io.github.ultrusbot.lawofexchange.mixin;


import io.github.ultrusbot.lawofexchange.items.KleinStarItem;
import io.github.ultrusbot.lawofexchange.items.PlayerInventoryAccess;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
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



}
