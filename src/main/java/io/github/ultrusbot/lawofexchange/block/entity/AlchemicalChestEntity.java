package io.github.ultrusbot.lawofexchange.block.entity;

import io.github.ultrusbot.lawofexchange.gui.CustomInventory;
import io.github.ultrusbot.lawofexchange.gui.alchemical_chest.AlchemicalChestGuiDescription;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;

public class AlchemicalChestEntity extends BlockEntity implements CustomInventory, NamedScreenHandlerFactory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(104, ItemStack.EMPTY);

    public AlchemicalChestEntity() {
        super(BlockEntityRegistry.ALCHEMICAL_CHEST_ENTITY);
    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag,items);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag,items);
        return super.toTag(tag);
    }

    @Override
    public Text getDisplayName() {
        // Using the block name as the screen title
        return Text.of("");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
        return new AlchemicalChestGuiDescription(syncId, inventory, ScreenHandlerContext.create(world, pos));
    }
}
