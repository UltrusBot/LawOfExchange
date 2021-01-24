package io.github.ultrusbot.lawofexchange.items;

import io.github.ultrusbot.lawofexchange.emc.EMCStorageItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class HarvestGoddessBandItem extends Item implements ModeSwitchingItem, EMCStorageItem {
    public HarvestGoddessBandItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (world.isClient()) return;
        for (BlockPos cur : BlockPos.iterateOutwards(entity.getBlockPos(), 3, 3, 3)) {
            BlockState block = world.getBlockState(cur);
            if (block.getBlock() instanceof CropBlock) {
                if (world.random.nextFloat() < 0.5) {
                    ((CropBlock) block.getBlock()).randomTick(block, world.getServer().getOverworld(), cur, world.getServer().getOverworld().random);
                }
            }
        }
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        switchMode(user.getStackInHand(hand));
        return TypedActionResult.success(user.getStackInHand(hand));
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("text.lawofexchange.stored_emc").append(" ").append(Integer.toString(getEMC(stack))));
        super.appendTooltip(stack, world, tooltip, context);

    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return getMode(stack) == 1;
    }

    @Override
    public void switchMode(ItemStack item) {
        CompoundTag tag = item.getOrCreateTag();
        int currentMode = tag.getInt("mode");
        currentMode += 1;
        currentMode %= 2;
        tag.putInt("mode", currentMode);
    }

    @Override
    public int getMode(ItemStack item) {
        CompoundTag tag = item.getTag();
        return tag == null ? 0 : tag.getInt("mode");
    }


    @Override
    public int getEMC(ItemStack itemStack) {
        CompoundTag tag = itemStack.getTag();
        return tag == null ? 0 : tag.getInt("EMC");
    }

    @Override
    public int getMaxEMC() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void setEMC(ItemStack itemStack, int emc) {
        itemStack.getOrCreateTag().putInt("EMC", emc);
    }

    @Override
    public void addEMC(ItemStack itemStack, int emc) {
        int curEMC = getEMC(itemStack);
        curEMC+=emc;
        curEMC = MathHelper.clamp(curEMC, 0, getMaxEMC());
        setEMC(itemStack, curEMC);
    }
    @Override
    public void removeEMC(ItemStack itemStack, int emc) {
        this.addEMC(itemStack, -emc);
    }
}
