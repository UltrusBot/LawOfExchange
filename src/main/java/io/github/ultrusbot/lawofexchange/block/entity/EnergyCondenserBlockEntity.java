package io.github.ultrusbot.lawofexchange.block.entity;

import io.github.ultrusbot.lawofexchange.inventory.CustomInventory;
import io.github.ultrusbot.lawofexchange.gui.energycondenser.EnergyCondenserScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import io.github.ultrusbot.lawofexchange.emc.EmcController;

public class EnergyCondenserBlockEntity extends LootableContainerBlockEntity implements CustomInventory, Tickable {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(92, ItemStack.EMPTY);;
    private int storedEmc;
    protected final PropertyDelegate propertyDelegate;
    public EnergyCondenserBlockEntity() {
        super(BlockEntityRegistry.ENERGY_CONDENSER_BLOCK_ENTITY);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                if (index == 0) {
                    return EnergyCondenserBlockEntity.this.storedEmc;
                }
                return 0;
            }

            public void set(int index, int value) {
                if (index == 0) {
                    EnergyCondenserBlockEntity.this.storedEmc = value;
                }

            }

            public int size() {
                return 1;
            }
        };
    }

    @Override
    public void tick() {
        boolean markDirty = false;
        if (!this.world.isClient) {
            if (this.getEMC() < 10000000) {
                for (int i = 1; i < 92; i++) {
                    ItemStack currentItemStack = this.inventory.get(i);

                    if (currentItemStack.isEmpty()) continue;
                    if (currentItemStack.isItemEqual(this.inventory.get(0))) continue;
                    int emc = EmcController.getEMC(currentItemStack.getItem());
                    if (this.storedEmc + this.storedEmc > 10000000) continue;
                    if (currentItemStack.getItem().isDamageable() && currentItemStack.getCount() == 1) {
                        emc = EmcController.getEMC(currentItemStack);
                    }
                    this.storedEmc += emc;
                    currentItemStack.decrement(1);
                    markDirty = true;
                    break;
                }
            }
            if (this.getEMC() >= EmcController.getEMC(this.inventory.get(0).getItem().getDefaultStack())) {
                boolean addAttempt = tryToAdd(this.inventory.get(0).getItem().getDefaultStack());
                if (addAttempt) {
                    this.storedEmc -= EmcController.getEMC(this.inventory.get(0));
                    markDirty = true;
                }

            }
        }


        if (markDirty) {
            this.markDirty();
        }
    }
    public boolean tryToAdd(ItemStack item) {
        for (int i = 1; i < 92; i++) {
            ItemStack current = this.inventory.get(i);
            if (current.isItemEqual(item) && current.getCount() < current.getMaxCount()) {
                current.increment(1);
                return true;
            } else if (current.isEmpty()) {
                this.inventory.set(i, item);
                return true;
            }
        }
        return false;
    }
    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("lawofexchange.container.energy_condenser");
    }


    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.storedEmc = tag.getInt("EMC");
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);


    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("EMC", this.storedEmc);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }
    public int getEMC() {
        return this.storedEmc;
    }


    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new EnergyCondenserScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public int size() {
        return 92;
    }
}
