package io.github.ultrusbot.lawofexchange.items.tools;

import draylar.magna.api.MagnaTool;
import io.github.ultrusbot.lawofexchange.items.ModeSwitchingItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DarkMatterHammerItem extends InfiniteDurabilityPickaxeItem implements MagnaTool, ModeSwitchingItem {
    public DarkMatterHammerItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        int mode = 3 + 2 * getMode(stack);
        tooltip.add(Text.of("§7§o" + mode + " x " + mode));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getRadius(ItemStack stack) {
        return 1 + getMode(stack);
    }

    @Override
    public boolean playBreakEffects() {
        return false;
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            switchMode(user.getStackInHand(hand));
            return TypedActionResult.success(user.getStackInHand(hand));
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }
}
