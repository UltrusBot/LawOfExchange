package io.github.ultrusbot.lawofexchange.items;

import me.shedaniel.cloth.api.durability.bar.DurabilityBarItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class ZeroRingItem extends Item implements ChargeableItem, DurabilityBarItem, ProjectileItem, ModeSwitchingItem {
    public ZeroRingItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxCharge() {
        return 4;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            switchMode(user.getStackInHand(hand));
            return TypedActionResult.success(user.getStackInHand(hand));
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!(entity instanceof PlayerEntity)) return;
        if (getMode(stack) == 1) {
            snowOver(world, entity, 1 + getCharge(stack));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        snowOver(context.getWorld(), context.getBlockPos(), 1);
        return ActionResult.SUCCESS;
    }
    private void snowOver(World world, BlockPos blockPos, int size) {
        for (BlockPos cur : BlockPos.iterateOutwards(blockPos, size, 1, size)) {
            BlockPos pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, cur);
            FluidState fluidState = world.getFluidState(pos.down());
            if (fluidState.getFluid() == Fluids.WATER && world.getBlockState(pos.down()).getBlock() instanceof FluidBlock) {
                world.setBlockState(pos.down(), Blocks.ICE.getDefaultState());
            }
            if (fluidState.getFluid() == Fluids.LAVA && world.getBlockState(pos.down()).getBlock() instanceof FluidBlock) {
                world.setBlockState(pos.down(), Blocks.OBSIDIAN .getDefaultState());
            }
            if (world.getBlockState(pos).isAir() && Blocks.SNOW.getDefaultState().canPlaceAt(world, pos)) {
                world.setBlockState(pos, Blocks.SNOW.getDefaultState());;
            }
        }
    }
    private void snowOver(World world, Entity entity, int size) {
        for (BlockPos cur : BlockPos.iterateOutwards(entity.getBlockPos(), size, 1, size)) {
            BlockPos pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, cur);
            FluidState fluidState = world.getFluidState(pos.down());
            if (fluidState.getFluid() == Fluids.WATER && world.getBlockState(pos.down()).getBlock() instanceof FluidBlock) {
                world.setBlockState(pos.down(), Blocks.ICE.getDefaultState());
            }
            if (fluidState.getFluid() == Fluids.LAVA && world.getBlockState(pos.down()).getBlock() instanceof FluidBlock) {
                world.setBlockState(pos.down(), Blocks.OBSIDIAN .getDefaultState());
            }
            if (world.getBlockState(pos).isAir() && Blocks.SNOW.getDefaultState().canPlaceAt(world, pos)) {
                world.setBlockState(pos, Blocks.SNOW.getDefaultState());;
            }
        }
    }
    @Override
    public double getDurabilityBarProgress(ItemStack stack) {
        return (1d - (double)getCharge(stack)/(double)getMaxCharge());
    }

    @Override
    public boolean hasDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public int getDurabilityBarColor(ItemStack stack) {
        return 0x42d4f5;
    }

    @Override
    public void shootProjectile(World world, LivingEntity user, Hand hand) {
        SnowballEntity snowballEntity = new SnowballEntity(world, user);
        snowballEntity.setItem(Items.SNOWBALL.getDefaultStack());
        snowballEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
        world.spawnEntity(snowballEntity);
        ((PlayerEntity)user).getItemCooldownManager().set(this, 10);
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
}
