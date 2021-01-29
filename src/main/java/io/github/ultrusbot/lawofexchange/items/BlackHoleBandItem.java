package io.github.ultrusbot.lawofexchange.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class BlackHoleBandItem extends Item implements ModeSwitchingItem {
    public BlackHoleBandItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (getMode(stack) == 1 && entity instanceof PlayerEntity) {
            pullItems(world, entity);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            switchMode(user.getStackInHand(hand));
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        BlockHitResult blockHitResult = (BlockHitResult)hitResult;
        BlockPos blockPos = blockHitResult.getBlockPos();
        Direction direction = blockHitResult.getSide();
        BlockPos blockPos2 = blockPos.offset(direction);
        if (world.canPlayerModifyAt(user, blockPos) && user.canPlaceOn(blockPos2, direction, user.getStackInHand(hand))) {
            BlockState blockState;
                blockState = world.getBlockState(blockPos);
                if (blockState.getBlock() instanceof FluidDrainable) {
                    Fluid fluid = ((FluidDrainable)blockState.getBlock()).tryDrainFluid(world, blockPos, blockState);
                    if (fluid != Fluids.EMPTY) {
                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
                    }
                }

                return TypedActionResult.fail(user.getStackInHand(hand));
        }
        return TypedActionResult.fail(user.getStackInHand(hand));
    }

    public void pullItems(World world, Entity user) {
        world.getOtherEntities(user, user.getBoundingBox().expand(5), entity -> {
            if (!(entity instanceof ItemEntity)) {return false;}
            Vec3d userPos = user.getPos();
            Vec3d entityPos = entity.getPos();
            Vec3d pushDir = ((entityPos.add(userPos).multiply(0.5D)).subtract(entityPos)).multiply(0.2D);
            entity.addVelocity(pushDir.x, pushDir.y, pushDir.z);
            return false;
        } );
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getMode(stack) == 1;
    }


}
