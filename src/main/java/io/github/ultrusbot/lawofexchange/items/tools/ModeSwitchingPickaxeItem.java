package io.github.ultrusbot.lawofexchange.items.tools;

import io.github.ultrusbot.lawofexchange.items.ModeSwitchingItem;
import me.shedaniel.cloth.api.durability.bar.DurabilityBarItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class ModeSwitchingPickaxeItem extends InfiniteDurabilityPickaxeItem implements DurabilityBarItem, ModeSwitchingItem {
    private final int oreBreakSize;
    public ModeSwitchingPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, int oreBreakSize) {
        super(material, attackDamage, attackSpeed, settings);
        this.oreBreakSize = oreBreakSize;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        switch (getMode(stack)) {
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
                ItemStack stack = user.getStackInHand(hand);
                switchMode(stack);
                switch(getMode(stack)) {
                    case 0:
                        user.sendMessage(new TranslatableText("text.lawofexchange.mode").append(" ").append( new TranslatableText("text.lawofexchange.pickaxe_mode_0")), true);
                        user.getItemCooldownManager().set(this, 10);
                        return TypedActionResult.pass(stack);

                    case 1:
                        user.sendMessage(new TranslatableText("text.lawofexchange.mode").append(" ").append( new TranslatableText("text.lawofexchange.pickaxe_mode_1")), true);
                        user.getItemCooldownManager().set(this, 10);
                        return TypedActionResult.pass(stack);
                    case 2:
                        user.sendMessage(new TranslatableText("text.lawofexchange.mode").append(" ").append( new TranslatableText("text.lawofexchange.pickaxe_mode_2")), true);
                        user.getItemCooldownManager().set(this, 10);
                        return TypedActionResult.pass(stack);
                    case 3:
                        user.sendMessage(new TranslatableText("text.lawofexchange.mode").append(" ").append( new TranslatableText("text.lawofexchange.pickaxe_mode_3")), true);
                        user.getItemCooldownManager().set(this, 10);
                        return TypedActionResult.pass(stack);
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
//        if (!context.getPlayer().isSneaking()) {
//            Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
//            if (block instanceof OreBlock) {
//                int current = 0;
//                breakBlocks(context.getWorld(), context.getBlockPos(), block, oreBreakSize, context.getPlayer(), current);
//                return ActionResult.SUCCESS;
//            }
//        } else {
//            return ActionResult.PASS;
//        }
        return ActionResult.PASS;
    }
//    private void breakBlocks(World world, BlockPos blockPos, Block block, int limit, Entity player, int current) {
//        Iterator<BlockPos> surroundingBlocks = getSurroundingBlocks(world, blockPos, block);
//        world.breakBlock(blockPos, true, player);
//        System.out.println(current);
//        current++;
//        while (surroundingBlocks.hasNext() && current < limit) {
//            BlockPos blockPos1 = surroundingBlocks.next();
//            if (world.getBlockState(blockPos1).getBlock().equals(block)) {
//                world.breakBlock(blockPos1, true, player);
//                current++;
//            }
//        }
//
//
//    }
//    private Iterator<BlockPos> getSurroundingBlocks(World world, BlockPos blockPos, Block block) {
//        return BlockPos.iterateOutwards(blockPos,1, 1,1 ).iterator();
//
//    }
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!isEffectiveOn(state)) return false;
        switch(getMode(stack)) {
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

    @Override
    public void switchMode(ItemStack item) {
        CompoundTag tag = item.getOrCreateTag();
        int currentMode = tag.getInt("mode");
        currentMode+=1;
        currentMode%=4;

        tag.putInt("mode", currentMode);

    }

    @Override
    public int getMode(ItemStack item) {
        CompoundTag tag = item.getTag();
        return tag == null ? 0 : tag.getInt("mode");

    }
}
