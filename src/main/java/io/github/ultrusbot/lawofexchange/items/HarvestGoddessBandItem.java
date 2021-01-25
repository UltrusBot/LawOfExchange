package io.github.ultrusbot.lawofexchange.items;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.emc.EMCStorageItem;
import io.github.ultrusbot.lawofexchange.emc.EMC_Controller;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
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
        if (!(entity instanceof PlayerEntity) || world.isClient()) {
            return;
        }
        int tickCount = getTickCount(stack);
        if (tickCount >= 200) {
            setTickCount(stack, 0);
            tickCount = 0;
            removeEMC(stack, 64);
        }
        growingTick(world, entity);
        if (getEMC(stack) == 0) {
            getAndConsumeEMC(stack,(PlayerEntity)entity, 64);
        }
        if (getMode(stack) == 1 && getEMC(stack) != 0) {
            harvestTick(world, entity);
            setTickCount(stack, tickCount+1);

        } else if (getMode(stack) == 1 && getEMC(stack) <= 64) {
            switchMode(stack);
        }
    }

    public void growingTick(World world, Entity entity) {
        for (BlockPos cur : BlockPos.iterateOutwards(entity.getBlockPos(), 5, 1, 5)) {
            BlockState block = world.getBlockState(cur);
            if (block.getBlock() instanceof CropBlock) {
                if (world.random.nextFloat() < 0.3) {
                    ((CropBlock) block.getBlock()).randomTick(block, world.getServer().getOverworld(), cur, world.getServer().getOverworld().random);
                }
            }
        }
    }
    public void harvestTick(World world, Entity entity) {
        for (BlockPos cur : BlockPos.iterateOutwards(entity.getBlockPos(), 5, 1, 5)) {
            BlockState block = world.getBlockState(cur);

            if (block.getBlock() instanceof CropBlock) {
                if (((CropBlock) block.getBlock()).isMature(block)) {
                    world.breakBlock(cur, true, entity);
                }
            } else {
                if (block.getBlock() instanceof PlantBlock) {
                    world.breakBlock(cur, true, entity);

                }
            }
        }
    }
    public void setTickCount(ItemStack itemStack, int count) {
        itemStack.getOrCreateTag().putInt("tickCount", count);

    }
    public int getTickCount(ItemStack itemStack) {
        return itemStack.getOrCreateTag().getInt("tickCount");

    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            switchMode(user.getStackInHand(hand));
            return TypedActionResult.success(user.getStackInHand(hand));
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.getBlockState(context.getBlockPos()).getBlock() == Blocks.FARMLAND) {
            PlayerEntity entity = context.getPlayer();
            Tag<Item> tag = TagRegistry.item(new Identifier("c:seeds"));
            ItemStack seeds = ((PlayerInventoryAccess)context.getPlayer().inventory).searchForItemFromTag(tag);
            for (BlockPos cur : BlockPos.iterateOutwards(entity.getBlockPos().up(), 5, 0, 5)) {
                BlockState block = world.getBlockState(cur);
                BlockState below = world.getBlockState(cur.down());
                if (below.getBlock() == Blocks.FARMLAND && block.getBlock() instanceof AirBlock && !seeds.isEmpty()) {
                    world.setBlockState(cur, ((BlockItem)seeds.getItem()).getBlock().getDefaultState());
                    seeds.decrement(1);
                }
            }
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }
    public void getAndConsumeEMC(ItemStack stack, PlayerEntity player,  int amount) {
        ItemStack kleinStar = ((PlayerInventoryAccess)player.inventory).getKleinStarItem();
        if (!kleinStar.isEmpty()) {
            KleinStarItem item = (KleinStarItem)kleinStar.getItem();
            int itemTotalEMC = item.getEMC(kleinStar);
            item.removeEMC(kleinStar, amount);
            addEMC(stack, Math.min(itemTotalEMC, amount));
        } else {
            ItemStack fuelItem = ((PlayerInventoryAccess)player.inventory).getFuelItem();
            if (fuelItem.isEmpty()) return;
            int itemTotalEMC = EMC_Controller.getEMC(fuelItem.getItem());
            addEMC(stack, itemTotalEMC);
            fuelItem.decrement(1);
        }
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
        item.getOrCreateTag().putInt("CustomModelData", currentMode);
    }

    @Override
    public int getMode(ItemStack item) {
        CompoundTag tag = item.getTag();
        return tag == null ? 0 : tag.getInt("mode");
    }
    @Override
    public int getMaxEMC() {
        return Integer.MAX_VALUE;
    }
}
