package io.github.ultrusbot.lawofexchange.items;

import io.github.ultrusbot.lawofexchange.emc.EMCStorageItem;
import io.github.ultrusbot.lawofexchange.emc.EmcController;
import io.github.ultrusbot.lawofexchange.inventory.PlayerInventoryAccess;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulStoneItem extends Item implements EMCStorageItem {
    public SoulStoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!(entity instanceof PlayerEntity) || slot > 8) { return; }
        PlayerEntity player = (PlayerEntity)entity;
        if (player.getItemCooldownManager().isCoolingDown(stack.getItem())) { return; }
        if (getEMC(stack) < 64) { getAndConsumeEMC(stack, player, 64); }
        if (player.getHealth() < player.getMaxHealth() && getEMC(stack) >= 64) {
            for (int i = 0; i < 4; i++) {
                getAndConsumeEMC(stack, player, 64);
                if (getEMC(stack) < 64 || player.getHealth() == player.getMaxHealth()) {
                    break;
                } else {
                    player.heal(1f);
                    removeEMC(stack, 64);
                }

            }
            player.getItemCooldownManager().set(this.asItem(), 20);
        }

    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("text.lawofexchange.stored_emc").append(" ").append(Integer.toString(getEMC(stack))));
        super.appendTooltip(stack, world, tooltip, context);

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
}
