package io.github.ultrusbot.lawofexchange.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public class AlchemicalContainerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final ItemStack alchemicalBag;
    public AlchemicalContainerScreenHandler(int i, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(i, playerInventory, packetByteBuf.readItemStack());
    }
    public AlchemicalContainerScreenHandler(int syncId, PlayerInventory playerInventory, ItemStack alchemicalBag) {
        this(syncId, playerInventory, new SimpleInventory(13 * 8), alchemicalBag);
    }
    public AlchemicalContainerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, ItemStack alchemicalBag) {
        super(GUIRegistry.ALCHEMICAL_CONTAINER_SCREEN_HANDLER, syncId);
        this.alchemicalBag = alchemicalBag;
        checkSize(inventory, 13 * 8);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        int n;
        int m;
        for (int y = 0; y<8;y++) {
            for (int x = 0; x<13;x++) {
                this.addSlot(new Slot(inventory, x + y * 13, 7 + x * 18, 6 + y * 18));

            }
        }
        for(n = 0; n < 3; ++n) {
            for(m = 0; m < 9; ++m) {
                this.addSlot(new Slot(playerInventory, m + n * 9 + 9, 43 + m * 18, 157 + n * 18));
            }
        }

        for(n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInventory, n, 43 + n * 18, 215 ));
        }

    }



    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index < 13 * 8) {
                if (!this.insertItem(itemStack2, 13 * 8, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 0, 13 * 8, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public void close(PlayerEntity player) {
        super.close(player);
        this.inventory.onClose(player);
    }

}
