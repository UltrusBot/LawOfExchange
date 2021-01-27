package io.github.ultrusbot.lawofexchange.items;

import com.google.common.collect.ImmutableMap.Builder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class PhilosopherStoneItem extends Item {
    public static Map<Block, Block> blocks;
    public static Map<Block, Block> shiftBlocks;
    public PhilosopherStoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block convertedBlock = (context.getPlayer().isSneaking()) ? shiftBlocks.get(blockState.getBlock()): blocks.get(blockState.getBlock());
        if (convertedBlock != null) {
            if (!world.isClient) {
                world.setBlockState(blockPos, convertedBlock.getDefaultState());
            }
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }


    public static void addBlockTransform(Block originalBlock, Block replaceBlock) {
        blocks.put(originalBlock, replaceBlock);
    }
    static {
        blocks = (new Builder())
                .put(Blocks.DIRT, Blocks.SAND)
                .put(Blocks.GRASS_BLOCK, Blocks.SAND)
                .put(Blocks.COBBLESTONE, Blocks.STONE)
                .put(Blocks.STONE, Blocks.COBBLESTONE)
                .put(Blocks.SAND, Blocks.DIRT)
                .put(Blocks.WATER, Blocks.LAVA)
                .put(Blocks.OAK_LOG, Blocks.BIRCH_LOG)
                .put(Blocks.BIRCH_LOG, Blocks.SPRUCE_LOG)
                .put(Blocks.SPRUCE_LOG, Blocks.JUNGLE_LOG)
                .put(Blocks.JUNGLE_LOG, Blocks.DARK_OAK_LOG)
                .put(Blocks.DARK_OAK_LOG, Blocks.ACACIA_LOG)
                .put(Blocks.ACACIA_LOG, Blocks.OAK_LOG)
                .put(Blocks.MELON, Blocks.PUMPKIN)
                .put(Blocks.PUMPKIN, Blocks.MELON)
                .build();
        shiftBlocks = (new Builder())
                .put(Blocks.GLASS, Blocks.SAND)
                .put(Blocks.STONE, Blocks.GRASS_BLOCK)
                .put(Blocks.COBBLESTONE, Blocks.GRASS_BLOCK)
                .put(Blocks.SAND, Blocks.COBBLESTONE)
                .put(Blocks.SANDSTONE, Blocks.GRAVEL)
                .build();
    }
}
