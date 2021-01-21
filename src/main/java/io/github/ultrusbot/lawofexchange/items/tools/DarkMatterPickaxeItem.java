package io.github.ultrusbot.lawofexchange.items.tools;

import me.shedaniel.cloth.api.durability.bar.DurabilityBarItem;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DarkMatterPickaxeItem extends InfiniteDurabilityPickaxeItem implements DurabilityBarItem {
    int mode = 0;
    public DarkMatterPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        switch (mode) {
            case 0:
                tooltip.add(new TranslatableText("text.lawofexchange.pickaxe_mode_0"));
                break;
            case 1:
                tooltip.add(new TranslatableText("text.lawofexchange.pickaxe_mode_1"));
                break;
            case 2:
                tooltip.add(new TranslatableText("text.lawofexchange.pickaxe_mode_2"));
                break;
            case 3:
                tooltip.add(new TranslatableText("text.lawofexchange.pickaxe_mode_3"));
                break;
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

//    public void setModeNBT(ItemStack item, int mode) {
//        CompoundTag compoundTag = item.getOrCreateSubTag("darkMatterMode");
//        compoundTag.putString("Name", Text.Serializer.toJson(Text.of(Integer.toString(mode))));
//
//
//    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            if (!world.isClient) {
                mode+=1;
                mode%=4;

                System.out.println(mode);
                switch(mode) {
                    case 0:
                        user.sendMessage(new TranslatableText("text.lawofexchange.mode").append(" ").append( new TranslatableText("text.lawofexchange.pickaxe_mode_0")), true);
                        user.getItemCooldownManager().set(this, 10);
                        return TypedActionResult.pass(user.getStackInHand(hand));

                    case 1:
                        user.sendMessage(new TranslatableText("text.lawofexchange.mode").append(" ").append( new TranslatableText("text.lawofexchange.pickaxe_mode_1")), true);
                        user.getItemCooldownManager().set(this, 10);
                        return TypedActionResult.pass(user.getStackInHand(hand));
                    case 2:
                        user.sendMessage(new TranslatableText("text.lawofexchange.mode").append(" ").append( new TranslatableText("text.lawofexchange.pickaxe_mode_2")), true);
                        user.getItemCooldownManager().set(this, 10);
                        return TypedActionResult.pass(user.getStackInHand(hand));
                    case 3:
                        user.sendMessage(new TranslatableText("text.lawofexchange.mode").append(" ").append( new TranslatableText("text.lawofexchange.pickaxe_mode_3")), true);
                        user.getItemCooldownManager().set(this, 10);
                        return TypedActionResult.pass(user.getStackInHand(hand));
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        switch(mode) {
            case 1:
                BlockState above = world.getBlockState(pos.up());
                BlockState below = world.getBlockState(pos.up());
                if (above.equals(state)) {
                    world.breakBlock(pos.up(), true, miner);
                }
                if (below.equals(state)) {
                    world.breakBlock(pos.down(), true, miner);
                }
                break;
            case 2:
                Direction dir = Direction.fromRotation(miner.getHeadYaw()).rotateYClockwise();
                System.out.println(dir.name());
                BlockState left = world.getBlockState(pos.offset(dir));
                BlockState right = world.getBlockState(pos.offset(dir.getOpposite()));
                if (left.equals(state)) {
                    world.breakBlock(pos.offset(dir), true, miner);
                }
                if (right.equals(state)) {
                    world.breakBlock(pos.offset(dir.getOpposite()), true, miner);
                }
                break;
            case 3:
                Direction direction = Direction.fromRotation(miner.getHeadYaw());
                System.out.println(direction.name());
                BlockState behind = world.getBlockState(pos.offset(direction, 1));
                BlockState behinder = world.getBlockState(pos.offset(direction, 2));
                if (behind.equals(state)) {
                    world.breakBlock(pos.offset(direction, 1), true, miner);
                }
                if (behinder.equals(state)) {
                    world.breakBlock(pos.offset(direction, 2), true, miner);
                }
                break;
            default:
                return true;
        }

        return true;
    }

    @Override
    public double getDurabilityBarProgress(ItemStack stack) {
        return 0d;
    }

    @Override
    public boolean hasDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public int getDurabilityBarColor(ItemStack stack) {
        return 0x0398fc;
    }

}
