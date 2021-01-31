package io.github.ultrusbot.lawofexchange.items;

import io.github.ultrusbot.lawofexchange.emc.EMCStorageItem;
import io.github.ultrusbot.lawofexchange.emc.EmcController;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IgnitionRingItem extends Item implements ProjectileItem, ModeSwitchingItem, EMCStorageItem {
    public IgnitionRingItem(Settings settings) {
        super(settings);
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
            getAndConsumeEMC(stack, (PlayerEntity)entity, 2);
            if (getEMC(stack) >=2) {
                placeRandomFire(entity.getBlockPos(), world, entity);
                removeEMC(stack, 4);
            } else {
                switchMode(stack);
            }
        }
        ((PlayerEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 10, 1, true, true));

    }
    private void placeRandomFire(BlockPos pos, World world, Entity entity) {
        if (world.getRandom().nextFloat() < 0.2) {
            for (BlockPos blockPos : BlockPos.iterateRandomly(world.getRandom(), 3, pos.getX() - 5, pos.getY(), pos.getZ() - 5, pos.getX() + 5, pos.getY(), pos.getZ() + 5)) {
                BlockState fire = AbstractFireBlock.getState(world, blockPos);
                if (world.getBlockState(blockPos).isAir() && fire.canPlaceAt(world, blockPos)) {
                    world.setBlockState(blockPos, fire);
                }
            }
        }

        world.getOtherEntities(entity, entity.getBoundingBox().expand(5), otherEntity -> {
            if (otherEntity instanceof HostileEntity && !otherEntity.isOnFire()) {
                otherEntity.setOnFireFor(20);
            }
            return false;
        } );

    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.SUCCESS;
    }
    @Override

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("text.lawofexchange.stored_emc").append(" ").append(Integer.toString(getEMC(stack))));
        super.appendTooltip(stack, world, tooltip, context);

    }


    @Override
    public void shootProjectile(World world, LivingEntity user, Hand hand) {
        getAndConsumeEMC(user.getStackInHand(hand), (PlayerEntity)user, 64);
        if (getEMC(user.getStackInHand(hand)) < 64) return;
        removeEMC(user.getStackInHand(hand), 64);
        FireballEntity fireballEntity = new FireballEntity(world, user, 0, 0, 0);
        fireballEntity.explosionPower = 2;
        fireballEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 3.0F, 1.0F);
        Vec3d vec = user.getRotationVec(1f);
        fireballEntity.updatePosition(user.getX() + vec.x, user.getY() + vec.y, user.getZ() + vec.z);
        world.spawnEntity(fireballEntity);
        ((PlayerEntity)user).getItemCooldownManager().set(this, 10);

    }
    public void getAndConsumeEMC(ItemStack stack, PlayerEntity player,  int amount) {
        ItemStack kleinStar = ((PlayerInventoryAccess)player.inventory).getKleinStarItem();
        if (getEMC(stack) >= amount) return;
        if (!kleinStar.isEmpty()) {
            KleinStarItem item = (KleinStarItem)kleinStar.getItem();
            int itemTotalEMC = item.getEMC(kleinStar);
            item.removeEMC(kleinStar, amount);
            addEMC(stack, Math.min(itemTotalEMC, amount));
        } else {
            ItemStack fuelItem = ((PlayerInventoryAccess)player.inventory).getFuelItem();
            if (fuelItem.isEmpty()) return;
            int itemTotalEMC = EmcController.getEMC(fuelItem.getItem());
            addEMC(stack, itemTotalEMC);
            fuelItem.decrement(1);
        }
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getMode(stack) == 1;
    }





    @Override
    public int getMaxEMC() {
        return Integer.MAX_VALUE;
    }
}
