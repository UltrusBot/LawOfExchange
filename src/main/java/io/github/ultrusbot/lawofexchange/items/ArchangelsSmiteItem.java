package io.github.ultrusbot.lawofexchange.items;

import io.github.ultrusbot.lawofexchange.emc.EMCStorageItem;
import io.github.ultrusbot.lawofexchange.emc.EmcController;
import io.github.ultrusbot.lawofexchange.inventory.PlayerInventoryAccess;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArchangelsSmiteItem extends Item implements EMCStorageItem,ModeSwitchingItem,ProjectileItem {
    public ArchangelsSmiteItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            switchMode(user.getStackInHand(hand));
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        ItemStack arrow = ((PlayerInventoryAccess)user.inventory).searchForItem(Items.ARROW);
        ItemStack item = user.getStackInHand(hand);
        int shots = getMode(item) == 1 ? 7 : 1;
        for (int i = 0; i < shots; i++) {
            if (!arrow.isEmpty()) {
                ArrowItem arrowItem = (ArrowItem)arrow.getItem();
                shootArrow(user, world, arrowItem, arrow);
                arrow.decrement(1);

            } else {
                getAndConsumeEMC(item, user, 14);
                if (getEMC(item) >= 14) {
                    shootArrow(user, world, (ArrowItem)Items.ARROW, arrow);
                    removeEMC(item, 14);
                }
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("text.lawofexchange.stored_emc").append(" ").append(Integer.toString(getEMC(stack))));
        super.appendTooltip(stack, world, tooltip, context);

    }
    public void shootArrow(PlayerEntity user, World world, ArrowItem arrowItem, ItemStack arrow) {
        PersistentProjectileEntity arrowEntity = arrowItem.createArrow(world, arrow, user);
        arrowEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 3.0F, 1.0F);
        world.spawnEntity(arrowEntity);

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
    public int getMaxEMC() {
        return Integer.MAX_VALUE;
    }
    

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getMode(stack) == 1;
    }

    @Override
    public void shootProjectile(World world, LivingEntity user, Hand hand) {
        PlayerEntity player = (PlayerEntity)user;
        ItemStack arrow = ((PlayerInventoryAccess) player.inventory).searchForItem(Items.ARROW);
        ItemStack item = user.getStackInHand(hand);
        if (!arrow.isEmpty()) {
            ArrowItem arrowItem = (ArrowItem) arrow.getItem();
            shootArrow(player, world, arrowItem, arrow);
            arrow.decrement(1);

        } else {
            getAndConsumeEMC(item, player, 14);
            if (getEMC(item) >= 14) {
                shootArrow(player, world, (ArrowItem) Items.ARROW, arrow);
                removeEMC(item, 14);
            }
        }
    }
}
