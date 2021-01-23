package io.github.ultrusbot.lawofexchange.items;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.block.BlockRegistry;
import io.github.ultrusbot.lawofexchange.items.armor.LoEArmorMaterials;
import io.github.ultrusbot.lawofexchange.items.tools.DarkMatterPickaxeItem;
import io.github.ultrusbot.lawofexchange.items.tools.InfiniteDurabilitySwordItem;
import io.github.ultrusbot.lawofexchange.items.tools.SpecialMatterShears;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    private static final String MOD_ID = LawOfExchangeMod.MOD_ID;
    public static Item PHILOSOPHERS_STONE = new PhilosopherStoneItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item ALCHEMICAL_COAL = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item MOBIUS_FUEL = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item AETERNALIS_FUEL = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item RED_MATTER = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_PICKAXE = new DarkMatterPickaxeItem(CustomToolMaterials.DARK_MATTER, 1, -2.8F, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_SWORD = new InfiniteDurabilitySwordItem(CustomToolMaterials.DARK_MATTER, 6, -2.4F, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item ALCHEMICAL_CHEST = new BlockItem(BlockRegistry.ALCHEMICAL_CHEST, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item KLEIN_STAR_EIN = new KleinStarItem(50000, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item DARK_MATTER_BLOCK = new BlockItem(BlockRegistry.DARK_MATTER_BLOCK, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item RED_MATTER_BLOCK = new BlockItem(BlockRegistry.RED_MATTER_BLOCK, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item SWIFTWOLFS_RENDING_GALE = new SwiftwolfsRendingGaleItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item DARK_MATTER_HELMET = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.HEAD, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_CHESTPLATE = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.CHEST, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_LEGGINGS = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.LEGS, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_BOOTS = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.FEET, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_SHEARS = new SpecialMatterShears(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item TRANSMUTATION_TABLE = new BlockItem(BlockRegistry.TRANSMUTATION_TABLE, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item EXOTIC_MATTER = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static void register() {
        PHILOSOPHERS_STONE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "philosophers_stone"), PHILOSOPHERS_STONE);
        ALCHEMICAL_COAL = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "alchemical_coal"), ALCHEMICAL_COAL);
        MOBIUS_FUEL = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mobius_fuel"), MOBIUS_FUEL);
        AETERNALIS_FUEL = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aeternalis_fuel"), AETERNALIS_FUEL);
        DARK_MATTER = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter"), DARK_MATTER);
        RED_MATTER = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_matter"), RED_MATTER);
        DARK_MATTER_PICKAXE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_pickaxe"), DARK_MATTER_PICKAXE);
        DARK_MATTER_SWORD = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_sword"), DARK_MATTER_SWORD);
        ALCHEMICAL_CHEST = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "alchemical_chest"), ALCHEMICAL_CHEST);
        KLEIN_STAR_EIN = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "klein_star_ein"), KLEIN_STAR_EIN);
        DARK_MATTER_BLOCK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_block"), DARK_MATTER_BLOCK);
        RED_MATTER_BLOCK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_matter_block"), RED_MATTER_BLOCK);
        SWIFTWOLFS_RENDING_GALE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "swiftwolfs_rending_gale"), SWIFTWOLFS_RENDING_GALE);
        DARK_MATTER_HELMET = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_helmet"), DARK_MATTER_HELMET);
        DARK_MATTER_CHESTPLATE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_chestplate"), DARK_MATTER_CHESTPLATE);
        DARK_MATTER_LEGGINGS = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_leggings"), DARK_MATTER_LEGGINGS);
        DARK_MATTER_BOOTS = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_boots"), DARK_MATTER_BOOTS);
        DARK_MATTER_SHEARS = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_shears"), DARK_MATTER_SHEARS);
        TRANSMUTATION_TABLE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "transmutation_table"), TRANSMUTATION_TABLE);
        EXOTIC_MATTER = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "exotic_matter"), EXOTIC_MATTER);
        FuelRegistry.INSTANCE.add(ALCHEMICAL_COAL, 6400);
        FuelRegistry.INSTANCE.add(MOBIUS_FUEL, 25600);
        FuelRegistry.INSTANCE.add(AETERNALIS_FUEL, 102400);

    }

}
