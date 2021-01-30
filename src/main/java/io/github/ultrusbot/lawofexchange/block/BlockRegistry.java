package io.github.ultrusbot.lawofexchange.block;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.block.furnace.DarkMatterFurnaceBlock;
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
    public static Block TRANSMUTATION_TABLE = new TransmutationTableBlock(FabricBlockSettings.of(Material.STONE));
    public static Block DARK_MATTER_FURNACE = new DarkMatterFurnaceBlock(FabricBlockSettings.copyOf(DARK_MATTER_BLOCK));
    public static Block ALCHEMICAL_COAL_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.COAL_BLOCK));
    public static Block MOBIUS_FUEL_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.COAL_BLOCK));
    public static Block AETERNALIS_FUEL_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.COAL_BLOCK));
    public static void register() {
        ALCHEMICAL_CHEST = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_chest"), ALCHEMICAL_CHEST);
        DARK_MATTER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "dark_matter_block"), DARK_MATTER_BLOCK);
        RED_MATTER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "red_matter_block"), RED_MATTER_BLOCK);
        EXOTIC_MATTER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "exotic_matter_block"), EXOTIC_MATTER_BLOCK);
        TRANSMUTATION_TABLE = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "transmutation_table"), TRANSMUTATION_TABLE);
        DARK_MATTER_FURNACE = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "dark_matter_furnace"), DARK_MATTER_FURNACE);
        ALCHEMICAL_COAL_BLOCK = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_coal_block"), ALCHEMICAL_COAL_BLOCK);
        MOBIUS_FUEL_BLOCK = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "mobius_fuel_block"), MOBIUS_FUEL_BLOCK);
        AETERNALIS_FUEL_BLOCK = Registry.register(Registry.BLOCK, new Identifier(LawOfExchangeMod.MOD_ID, "aeternalis_fuel_block"), AETERNALIS_FUEL_BLOCK);

    }
}
