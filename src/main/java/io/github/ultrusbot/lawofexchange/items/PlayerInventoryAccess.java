package io.github.ultrusbot.lawofexchange.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;

public interface PlayerInventoryAccess {
    ItemStack getKleinStarItem();
    ItemStack getFuelItem();
    ItemStack searchForItem(Item item);
    ItemStack searchForItemFromTag(Tag<Item> tag);

    }
