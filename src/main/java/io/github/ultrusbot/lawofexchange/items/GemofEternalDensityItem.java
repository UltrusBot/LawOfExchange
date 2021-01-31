package io.github.ultrusbot.lawofexchange.items;

import io.github.ultrusbot.lawofexchange.emc.EMCStorageItem;
import io.github.ultrusbot.lawofexchange.emc.EmcController;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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

public class GemofEternalDensityItem extends Item implements EMCStorageItem,ModeSwitchingItem {
    public GemofEternalDensityItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity)entity;
        if (getMode(stack) != 0) {
            ItemStack toConsume = ((PlayerInventoryAccess)player.inventory).getItemWithLessEMC(EmcController.getEMC(getModeItem(stack)));
            if (toConsume.isEmpty()) return;
            if (toConsume.isDamageable()) return;
            addEMC(stack, EmcController.getEMC(toConsume.getItem()));
            toConsume.decrement(1);
            if (getEMC(stack) >= EmcController.getEMC(getModeItem(stack))) {
                removeEMC(stack, EmcController.getEMC(getModeItem(stack)));
                player.giveItemStack(getModeItem(stack).getDefaultStack());
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("text.lawofexchange.stored_emc").append(" ").append(Integer.toString(getEMC(stack))));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            switchMode(user.getStackInHand(hand));
            if (getMode(user.getStackInHand(hand)) != 0) {
                Item modeItem = getModeItem(user.getStackInHand(hand));
                user.sendMessage(new TranslatableText("text.lawofexchange.mode").append(" ").append(new TranslatableText(modeItem.getTranslationKey())), true);
            } else {
                user.sendMessage(new TranslatableText("text.lawofexchange.deactivated"), true);
            }
            return TypedActionResult.success(user.getStackInHand(hand));
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }

    @Override
    public int getMaxEMC() {
        return Integer.MAX_VALUE;
    }


    /*
    Mode 0: Inactive
    Mode 1: Iron
    Mode 2: Gold
    Mode 3: Diamond
    Mode 4: Dark Matter
    Mode 5: Red Matter
     */
    public Item getModeItem(ItemStack stack) {
        switch(getMode(stack)) {
            case 1:
                return Items.IRON_INGOT;
            case 2:
                return Items.GOLD_INGOT;
            case 3:
                return Items.DIAMOND;
            case 4:
                return ItemRegistry.DARK_MATTER;
            case 5:
                return ItemRegistry.RED_MATTER;
            default:
                throw new IllegalStateException("Unexpected value: " + getMode(stack));
        }
    }
    @Override
    public int getModeCount() {
        return 6;
    }
}
