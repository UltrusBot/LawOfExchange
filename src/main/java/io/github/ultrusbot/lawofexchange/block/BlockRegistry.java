package io.github.ultrusbot.lawofexchange.block;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    public static Block ALCHEMICAL_CHEST = new AlchemicalChestBlock(FabricBlockSettings.copyOf(Blocks.STONE));
    public static Block DARK_MATTER_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(50, 18000000).breakByTool(FabricToolTags.PICKAXES, 4));
    public static Block RED_MATTER_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(50, 0).breakByTool(FabricToolTags.PICKAXES, 5));
    public static Block EXOTIC_MATTER_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(50, 0).breakByTool(FabricToolTags.PICKAXES, 6));
    public static Block DARK_MATTER_FURNACE;
    public static Block TRANSMUTATION_TABLE = new TransmutationTableBlock(FabricBlockSettings.of(Material.STONE));
    public static void register() {
        ALCHEMICAL_CHEST = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_chest"), ALCHEMICAL_CHEST);
        DARK_MATTER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "dark_matter_block"), DARK_MATTER_BLOCK);
        RED_MATTER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "red_matter_block"), RED_MATTER_BLOCK);
        EXOTIC_MATTER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "exotic_matter_block"), EXOTIC_MATTER_BLOCK);
        TRANSMUTATION_TABLE = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "transmutation_table"), TRANSMUTATION_TABLE);

    }
}
