package io.github.ultrusbot.lawofexchange.items;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.block.BlockRegistry;
import io.github.ultrusbot.lawofexchange.components.AlchemicalBagComponent;
import io.github.ultrusbot.lawofexchange.components.LawOfExchangeComponents;
import io.github.ultrusbot.lawofexchange.items.armor.LoEArmorMaterials;
import io.github.ultrusbot.lawofexchange.items.tools.InfiniteDurabilityAxeItem;
import io.github.ultrusbot.lawofexchange.items.tools.InfiniteDurabilitySwordItem;
import io.github.ultrusbot.lawofexchange.items.tools.ModeSwitchingPickaxeItem;
import io.github.ultrusbot.lawofexchange.items.tools.SpecialMatterShears;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    private static final String MOD_ID = LawOfExchangeMod.MOD_ID;
    public static Item PHILOSOPHERS_STONE = new PhilosopherStoneItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    //Fuels
    public static Item ALCHEMICAL_COAL = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item MOBIUS_FUEL = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).rarity(Rarity.UNCOMMON));
    public static Item AETERNALIS_FUEL = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).rarity(Rarity.RARE));
    public static Item ALCHEMICAL_COAL_BLOCK = new BlockItem(BlockRegistry.ALCHEMICAL_COAL_BLOCK, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item MOBIUS_FUEL_BLOCK = new BlockItem(BlockRegistry.MOBIUS_FUEL_BLOCK, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).rarity(Rarity.UNCOMMON));
    public static Item AETERNALIS_FUEL_BLOCK = new BlockItem(BlockRegistry.AETERNALIS_FUEL_BLOCK, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).rarity(Rarity.RARE));

    //Dark Matter
    public static Item DARK_MATTER = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_BLOCK = new BlockItem(BlockRegistry.DARK_MATTER_BLOCK, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_PICKAXE = new ModeSwitchingPickaxeItem(CustomToolMaterials.DARK_MATTER, 1, -2.8F, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP), 16);
    public static Item DARK_MATTER_AXE = new InfiniteDurabilityAxeItem(CustomToolMaterials.DARK_MATTER, 8, -3.0F, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_SWORD = new InfiniteDurabilitySwordItem(CustomToolMaterials.DARK_MATTER, 6, -2.4F, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_HELMET = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.HEAD, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_CHESTPLATE = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.CHEST, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_LEGGINGS = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.LEGS, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_BOOTS = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.FEET, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item DARK_MATTER_SHEARS = new SpecialMatterShears(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item DARK_MATTER_FURNACE = new BlockItem(BlockRegistry.DARK_MATTER_FURNACE, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));

    //Red Matter
    public static Item RED_MATTER = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item RED_MATTER_BLOCK = new BlockItem(BlockRegistry.RED_MATTER_BLOCK, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item RED_MATTER_SWORD = new InfiniteDurabilitySwordItem(CustomToolMaterials.RED_MATTER, 6, -2.4F, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item RED_MATTER_PICKAXE = new ModeSwitchingPickaxeItem(CustomToolMaterials.RED_MATTER, 1, -2.8F, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP), 32);
    public static Item RED_MATTER_SHEARS = new SpecialMatterShears(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));

    //Exotic Matter
    public static Item EXOTIC_MATTER = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item EXOTIC_MATTER_HELMET = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.HEAD, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item EXOTIC_MATTER_CHESTPLATE = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.CHEST, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item EXOTIC_MATTER_LEGGINGS = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.LEGS, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item EXOTIC_MATTER_BOOTS = new ArmorItem(LoEArmorMaterials.DARK_MATTER, EquipmentSlot.FEET, new Item.Settings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item EXOTIC_MATTER_BLOCK = new BlockItem(BlockRegistry.EXOTIC_MATTER_BLOCK, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));

    //Other
    public static Item ALCHEMICAL_CHEST = new BlockItem(BlockRegistry.ALCHEMICAL_CHEST, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item TRANSMUTATION_TABLE = new BlockItem(BlockRegistry.TRANSMUTATION_TABLE, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item SWIFTWOLFS_RENDING_GALE = new SwiftwolfsRendingGaleItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item HARVEST_GODDESS_BAND = new HarvestGoddessBandItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item BLACK_HOLE_BAND = new BlackHoleBandItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item IRON_BAND = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item ZERO_RING = new ZeroRingItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item ARCHANGELS_SMITE = new ArchangelsSmiteItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item IGNITION_RING = new IgnitionRingItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item GEM_OF_ETERNAL_DENSITY = new GemofEternalDensityItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item ENERGY_CONDENSER = new BlockItem(BlockRegistry.ENERGY_CONDENSER, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item INTERDICTION_TORCH = new WallStandingBlockItem(BlockRegistry.INTERDICTION_TORCH, BlockRegistry.INTERDICTION_WALL_TORCH, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));

    //Alchemical Bags
    public static Item ALCHEMICAL_BAG_WHITE = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_WHITE);
    public static Item ALCHEMICAL_BAG_ORANGE = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_ORANGE);
    public static Item ALCHEMICAL_BAG_MAGENTA = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_MAGENTA);
    public static Item ALCHEMICAL_BAG_LIGHT_BLUE = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_LIGHT_BLUE);
    public static Item ALCHEMICAL_BAG_YELLOW = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_YELLOW);
    public static Item ALCHEMICAL_BAG_LIME = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_LIME);
    public static Item ALCHEMICAL_BAG_PINK = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_PINK);
    public static Item ALCHEMICAL_BAG_GRAY = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_GRAY);
    public static Item ALCHEMICAL_BAG_LIGHT_GRAY = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_LIGHT_GRAY);
    public static Item ALCHEMICAL_BAG_CYAN = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_CYAN);
    public static Item ALCHEMICAL_BAG_PURPLE = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_PURPLE);
    public static Item ALCHEMICAL_BAG_BLUE = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_BLUE);
    public static Item ALCHEMICAL_BAG_BROWN = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_BROWN);
    public static Item ALCHEMICAL_BAG_GREEN = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_GREEN);
    public static Item ALCHEMICAL_BAG_RED = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_RED);
    public static Item ALCHEMICAL_BAG_BLACK = new AlchemicalBagItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1), LawOfExchangeComponents.ALCHEMICAL_BAG_BLACK);

    //KLEIN STARS
    public static Item KLEIN_STAR_EIN = new KleinStarItem(50000, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item KLEIN_STAR_ZWEI = new KleinStarItem(200000, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item KLEIN_STAR_DREI = new KleinStarItem(800000, new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));

    //Stones
    public static Item BODY_STONE = new BodyStoneItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item SOUL_STONE = new SoulStoneItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item LIFE_STONE = new LifeStoneItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));
    public static Item MIND_STONE = new MindStoneItem(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP).maxCount(1));

    //Covalence Dust
    public static Item GREEN_COVALENCE_DUST = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item CYAN_COVALENCE_DUST = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));
    public static Item BLUE_COVALENCE_DUST = new Item(new FabricItemSettings().group(LawOfExchangeMod.PHILOSOPHERS_STONE_ITEM_GROUP));

    public static void register() {
        //Fuels
        ALCHEMICAL_COAL = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "alchemical_coal"), ALCHEMICAL_COAL);
        MOBIUS_FUEL = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mobius_fuel"), MOBIUS_FUEL);
        AETERNALIS_FUEL = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aeternalis_fuel"), AETERNALIS_FUEL);
        ALCHEMICAL_COAL_BLOCK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "alchemical_coal_block"), ALCHEMICAL_COAL_BLOCK);
        MOBIUS_FUEL_BLOCK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mobius_fuel_block"), MOBIUS_FUEL_BLOCK);
        AETERNALIS_FUEL_BLOCK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aeternalis_fuel_block"), AETERNALIS_FUEL_BLOCK);
        //
        PHILOSOPHERS_STONE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "philosophers_stone"), PHILOSOPHERS_STONE);
        ALCHEMICAL_CHEST = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "alchemical_chest"), ALCHEMICAL_CHEST);
        TRANSMUTATION_TABLE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "transmutation_table"), TRANSMUTATION_TABLE);
        SWIFTWOLFS_RENDING_GALE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "swiftwolfs_rending_gale"), SWIFTWOLFS_RENDING_GALE);
        HARVEST_GODDESS_BAND = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "harvest_goddess_band"), HARVEST_GODDESS_BAND);
        BLACK_HOLE_BAND = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "black_hole_band"), BLACK_HOLE_BAND);
        IRON_BAND = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "iron_band"), IRON_BAND);
        ZERO_RING = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "zero_ring"), ZERO_RING);
        ARCHANGELS_SMITE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "archangels_smite"), ARCHANGELS_SMITE);
        IGNITION_RING = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ignition_ring"), IGNITION_RING);
        GEM_OF_ETERNAL_DENSITY = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "gem_of_eternal_density"), GEM_OF_ETERNAL_DENSITY);
        ENERGY_CONDENSER = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "energy_condenser"), ENERGY_CONDENSER);
        INTERDICTION_TORCH = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "interdiction_torch"), INTERDICTION_TORCH);

        //Alchemical Bags
        ALCHEMICAL_BAG_WHITE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "white_alchemical_bag"), ALCHEMICAL_BAG_WHITE);
        ALCHEMICAL_BAG_ORANGE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "orange_alchemical_bag"), ALCHEMICAL_BAG_ORANGE);
        ALCHEMICAL_BAG_MAGENTA = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "magenta_alchemical_bag"), ALCHEMICAL_BAG_MAGENTA);
        ALCHEMICAL_BAG_LIGHT_BLUE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "light_blue_alchemical_bag"), ALCHEMICAL_BAG_LIGHT_BLUE);
        ALCHEMICAL_BAG_YELLOW = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yellow_alchemical_bag"), ALCHEMICAL_BAG_YELLOW);
        ALCHEMICAL_BAG_LIME = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lime_alchemical_bag"), ALCHEMICAL_BAG_LIME);
        ALCHEMICAL_BAG_PINK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "pink_alchemical_bag"), ALCHEMICAL_BAG_PINK);
        ALCHEMICAL_BAG_GRAY = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "gray_alchemical_bag"), ALCHEMICAL_BAG_GRAY);
        ALCHEMICAL_BAG_LIGHT_GRAY = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "light_gray_alchemical_bag"), ALCHEMICAL_BAG_LIGHT_GRAY);
        ALCHEMICAL_BAG_CYAN = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cyan_alchemical_bag"), ALCHEMICAL_BAG_CYAN);
        ALCHEMICAL_BAG_PURPLE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "purple_alchemical_bag"), ALCHEMICAL_BAG_PURPLE);
        ALCHEMICAL_BAG_BLUE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blue_alchemical_bag"), ALCHEMICAL_BAG_BLUE);
        ALCHEMICAL_BAG_BROWN = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "brown_alchemical_bag"), ALCHEMICAL_BAG_BROWN);
        ALCHEMICAL_BAG_GREEN = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "green_alchemical_bag"), ALCHEMICAL_BAG_GREEN);
        ALCHEMICAL_BAG_RED = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_alchemical_bag"), ALCHEMICAL_BAG_RED);
        ALCHEMICAL_BAG_BLACK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "black_alchemical_bag"), ALCHEMICAL_BAG_BLACK);

        //Dark Matter
        DARK_MATTER = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter"), DARK_MATTER);
        DARK_MATTER_BLOCK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_block"), DARK_MATTER_BLOCK);
        DARK_MATTER_PICKAXE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_pickaxe"), DARK_MATTER_PICKAXE);
        DARK_MATTER_AXE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_axe"), DARK_MATTER_AXE);
        DARK_MATTER_SWORD = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_sword"), DARK_MATTER_SWORD);
        DARK_MATTER_HELMET = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_helmet"), DARK_MATTER_HELMET);
        DARK_MATTER_CHESTPLATE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_chestplate"), DARK_MATTER_CHESTPLATE);
        DARK_MATTER_LEGGINGS = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_leggings"), DARK_MATTER_LEGGINGS);
        DARK_MATTER_BOOTS = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_boots"), DARK_MATTER_BOOTS);
        DARK_MATTER_SHEARS = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_shears"), DARK_MATTER_SHEARS);
        DARK_MATTER_FURNACE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_matter_furnace"), DARK_MATTER_FURNACE);

        //Red Matter
        RED_MATTER = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_matter"), RED_MATTER);
        RED_MATTER_SWORD = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_matter_sword"), RED_MATTER_SWORD);
        RED_MATTER_PICKAXE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_matter_pickaxe"), RED_MATTER_PICKAXE);
        RED_MATTER_BLOCK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_matter_block"), RED_MATTER_BLOCK);
        RED_MATTER_SHEARS = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_matter_shears"), RED_MATTER_SHEARS);

        //Exotic Matter
        EXOTIC_MATTER = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "exotic_matter"), EXOTIC_MATTER);
        EXOTIC_MATTER_HELMET = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "exotic_matter_helmet"), EXOTIC_MATTER_HELMET);
        EXOTIC_MATTER_CHESTPLATE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "exotic_matter_chestplate"), EXOTIC_MATTER_CHESTPLATE);
        EXOTIC_MATTER_LEGGINGS = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "exotic_matter_leggings"), EXOTIC_MATTER_LEGGINGS);
        EXOTIC_MATTER_BOOTS = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "exotic_matter_boots"), EXOTIC_MATTER_BOOTS);
        EXOTIC_MATTER_BLOCK = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "exotic_matter_block"), EXOTIC_MATTER_BLOCK);

        //Klein Stars
        KLEIN_STAR_EIN = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "klein_star_ein"), KLEIN_STAR_EIN);
        KLEIN_STAR_ZWEI = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "klein_star_zwei"), KLEIN_STAR_ZWEI);
        KLEIN_STAR_DREI = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "klein_star_drei"), KLEIN_STAR_DREI);

        //Stones
        BODY_STONE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "body_stone"), BODY_STONE);
        SOUL_STONE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "soul_stone"), SOUL_STONE);
        LIFE_STONE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "life_stone"), LIFE_STONE);
        MIND_STONE = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mind_stone"), MIND_STONE);

        //Covalence Dust
        GREEN_COVALENCE_DUST = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "green_covalence_dust"), GREEN_COVALENCE_DUST);
        CYAN_COVALENCE_DUST = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cyan_covalence_dust"), CYAN_COVALENCE_DUST);
        BLUE_COVALENCE_DUST = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blue_covalence_dust"), BLUE_COVALENCE_DUST);

        FuelRegistry.INSTANCE.add(ALCHEMICAL_COAL, 6400);
        FuelRegistry.INSTANCE.add(MOBIUS_FUEL, 25600);
        FuelRegistry.INSTANCE.add(AETERNALIS_FUEL, 102400);
    }

}
