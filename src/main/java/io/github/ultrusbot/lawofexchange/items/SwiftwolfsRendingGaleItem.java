package io.github.ultrusbot.lawofexchange.items;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.emc.EMCStorageItem;
import io.github.ultrusbot.lawofexchange.entity.projectile.SwiftwolfsRendingGaleProjectileEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class SwiftwolfsRendingGaleItem extends Item implements EMCStorageItem,ProjectileItem,ModeSwitchingItem {
    public static final AbilitySource SWIFTWOLF_ABILITY = Pal.getAbilitySource(LawOfExchangeMod.MOD_ID, "swiftwolf_rending_gale");
    public SwiftwolfsRendingGaleItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (getMode(stack) == 1) {
            forcefieldTick(world, entity);
        }
        if (!(entity instanceof PlayerEntity) || slot > 8) { return; }
        if (!world.isClient) {
            PlayerEntity player = (PlayerEntity) entity;
            if (getEMC(stack) != 0) {
                SWIFTWOLF_ABILITY.grantTo(player, VanillaAbilities.ALLOW_FLYING);
            } else {
                ItemStack kleinStar = ((PlayerInventoryAccess)player.inventory).getKleinStarItem();
                if (!kleinStar.isEmpty()) {
                    KleinStarItem item = (KleinStarItem)kleinStar.getItem();
                    int itemTotalEMC = item.getEMC(kleinStar);
                    item.removeEMC(kleinStar, 64);
                    addEMC(stack, Math.min(itemTotalEMC, 64));
                    if (getEMC(stack) > 0) {
                        SWIFTWOLF_ABILITY.grantTo(player, VanillaAbilities.ALLOW_FLYING);
                    }
                }
            }

            if (player.abilities.flying) {
                int tickCount = getTickCount(stack) + 1;
                setFlyingState(stack, 1);
                this.setTickCount(stack, tickCount);
                if (this.getEMC(stack) == 0) {
                    SWIFTWOLF_ABILITY.revokeFrom(player, VanillaAbilities.ALLOW_FLYING);
                    setFlyingState(stack, 0);
                }
                if (tickCount == 120) {
                    this.removeEMC(stack, 64);
                    tickCount = 0;
                    this.setTickCount(stack, tickCount);
                }

            } else {
                setFlyingState(stack, 0);
            }
        }

    }
    public void forcefieldTick(World world, Entity user) {
        world.getOtherEntities(user, user.getBoundingBox().expand(11), entity -> {
            boolean userProjectile = false;
            if (entity instanceof ProjectileEntity) {
                Entity owner = ((ProjectileEntity)entity).getOwner();
                if (owner!= null) {
                    userProjectile = owner.getUuid() == user.getUuid();
                }
            }
            if (entity instanceof ThrownItemEntity) {
                userProjectile = true;
            }
            if (!(entity instanceof LivingEntity) && userProjectile) {return false;}
            Vec3d userPos = user.getPos();
            Vec3d entityPos = entity.getPos();
            Vec3d pushDir = ((userPos.add(entityPos).multiply(0.5D)).subtract(userPos)).multiply(0.2D);
            entity.addVelocity(pushDir.x, pushDir.y, pushDir.z);
            return false;
        } );
    }
    public void setTickCount(ItemStack itemStack, int count) {
        itemStack.getOrCreateTag().putInt("tickCount", count);

    }
    public int getTickCount(ItemStack itemStack) {
        CompoundTag tag = itemStack.getTag();
        return tag == null ? 0 : tag.getInt("tickCount");

    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("text.lawofexchange.stored_emc").append(" ").append(Integer.toString(getEMC(stack))));
        super.appendTooltip(stack, world, tooltip, context);

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
    public void setFlyingState(ItemStack item, int flying) {
        if (getFlyingState(item) != flying) {
            item.getOrCreateTag().putInt("CustomModelData", flying);
        }
    }
    /*
    0: Not Flying
    1: Flying
     */
    public int getFlyingState(ItemStack item) {
        CompoundTag tag = item.getOrCreateTag();

        if (tag.isEmpty()) {
            tag.putInt("CustomModelData", 0);
        }
        return tag.getInt("CustomModelData");

    }
    @Override
    public void removeEMC(ItemStack itemStack, int emc) {
        this.addEMC(itemStack, -emc);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        switchMode(user.getStackInHand(hand));
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public void shootProjectile(ItemStack stack, World world, LivingEntity user) {
        if (((PlayerEntity)user).getItemCooldownManager().isCoolingDown(this.asItem())) {
            return;
        }
        SwiftwolfsRendingGaleProjectileEntity projectile = new SwiftwolfsRendingGaleProjectileEntity(world, user, 0, 0, 0);
        projectile.setProperties(user, user.pitch, user.yaw, 0.0F, 2.0F, 1F);
        Vec3d vec3d = user.getRotationVec(1.0F);
        projectile.updatePosition(user.getX() + vec3d.x * 4.0D, user.getBodyY(.25F), projectile.getZ() + vec3d.z * 4.0D);
        projectile.
        world.spawnEntity(projectile);
        ((PlayerEntity)user).getItemCooldownManager().set(this.asItem(), 25);

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
    public boolean hasGlint(ItemStack stack) {
        return getMode(stack) == 1;
    }

    @Override
    public int getMode(ItemStack item) {
        CompoundTag tag = item.getTag();
        return tag == null ? 0 : tag.getInt("mode");

    }
}
