package io.github.ultrusbot.lawofexchange.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MindStoneItem extends Item {
    public MindStoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            if (user.totalExperience > 0) {
                int toRemove = Math.min(user.totalExperience, 10 + user.getNextLevelExperience()/20);
                user.addExperience(-toRemove);
                addExp(user.getStackInHand(hand), toRemove);
                return TypedActionResult.success(user.getStackInHand(hand));
            }
        } else {
            int toAdd = Math.min(getExp(user.getStackInHand(hand)), 10 + user.getNextLevelExperience()/20);
            user.addExperience(toAdd);
            removeXP(user.getStackInHand(hand), toAdd);
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("XP" + getExp(stack)));
        super.appendTooltip(stack, world, tooltip, context);

    }
    private int getExp(ItemStack item) {
        return item.getOrCreateTag().getInt("stored_xp");
    }
    private void setExp(ItemStack item, int xp) {
        item.getOrCreateTag().putInt("stored_xp", xp);
    }
    private void addExp(ItemStack item, int xp) {
        int currentXP = getExp(item);
        setExp(item, MathHelper.clamp(currentXP + xp, 0, Integer.MAX_VALUE));
    }
    private void removeXP(ItemStack item, int xp) {
        addExp(item, -xp);
    }
}
