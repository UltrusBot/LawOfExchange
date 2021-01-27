package io.github.ultrusbot.lawofexchange.emc;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.items.ItemRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.HashMap;

final public class EMC_Controller {
    private static HashMap<Item, Integer> EMCValues;

    //Loads EMC Config
    /*
    This holds the base items: Diamonds, Wood, Iron, etc, which is used to generate the EMC from recipes
     */
    static public void loadEMCConfig() {
        addEMCValue(Items.COBBLESTONE, 1);
        addEMCValue(Items.SAND, 1);
        addEMCValue(Items.SNOW_BLOCK, 1);
        addEMCValue(Items.DIRT, 1);
        addEMCValue(Items.GLASS, 1);
        addEMCValue(Items.GRASS_BLOCK, 1);
        addEMCValue(Items.GRASS_PATH, 1);
        addEMCValue(Items.MYCELIUM, 1);
        addEMCValue(Items.NETHERRACK, 1);
        addEMCValue(Items.WARPED_NYLIUM, 1);
        addEMCValue(Items.CRIMSON_NYLIUM, 1);
        addEMCValue(Items.STONE, 1);
        addEMCValue(Items.GRANITE, 1);
        addEMCValue(Items.ANDESITE, 1);
        addEMCValue(Items.DIORITE, 1);
        addEMCValue(Items.BLACKSTONE, 1);
        addEMCValue(Items.END_STONE, 1);
        addEMCValue(Items.WHITE_DYE, 8);
        addEMCValue(Items.ORANGE_DYE, 8);
        addEMCValue(Items.MAGENTA_DYE, 8);
        addEMCValue(Items.LIGHT_BLUE_DYE, 8);
        addEMCValue(Items.YELLOW_DYE, 8);
        addEMCValue(Items.LIME_DYE, 8);
        addEMCValue(Items.PINK_DYE, 8);
        addEMCValue(Items.GRAY_DYE, 8);
        addEMCValue(Items.LIGHT_GRAY_DYE, 8);
        addEMCValue(Items.CYAN_DYE, 8);
        addEMCValue(Items.PURPLE_DYE, 8);
        addEMCValue(Items.BLUE_DYE, 8);
        addEMCValue(Items.BROWN_DYE, 8);
        addEMCValue(Items.GREEN_DYE, 8);
        addEMCValue(Items.RED_DYE, 8);
        addEMCValue(Items.BLACK_DYE, 8);
        addEMCValue(Items.STICK, 4);
        addEMCValue(Items.NETHER_BRICK, 4);
        addEMCValue(Items.SANDSTONE, 4);
        addEMCValue(Items.GRAVEL, 4);
        addEMCValue(Items.FLINT, 4);
        addEMCValue(Items.COCOA_BEANS, 64);
        addEMCValue(Items.SPONGE, 64);
        addEMCValue(Items.WET_SPONGE, 64);
        addEMCValue(Items.PURPUR_BLOCK, 128);
        addEMCValue(Items.STRING, 24);
        addEMCValue(Items.WHEAT, 24);
        addEMCValue(Items.NETHER_WART, 24);
        addEMCValue(Items.ROTTEN_FLESH, 24);
        addEMCValue(Items.SLIME_BALL, 24);
        addEMCValue(Items.SUGAR_CANE, 32);

        addEMCValue(Items.FEATHER, 48);
        addEMCValue(Items.COAL, 128);
        addEMCValue(Items.APPLE, 128);
        addEMCValue(Items.SPIDER_EYE, 128);
        addEMCValue(Items.BONE, 144);
        addEMCValue(Items.ENDER_PEARL, 1024);
        addEMCValue(Items.GHAST_TEAR, 4096);
        addEMCValue(Items.REDSTONE, 64);
        addEMCValue(Items.IRON_INGOT, 256);
        addEMCValue(Items.LAVA_BUCKET, 832);
        addEMCValue(Items.GOLD_INGOT, 2048);
        addEMCValue(Items.DIAMOND, 8192);
        addEMCValue(Items.NETHERITE_SCRAP, 294912);
        addEMCValue(Items.GLOWSTONE_DUST, 384);
        addEMCValue(Items.BLAZE_ROD, 1536);

        addEMCValue(ItemRegistry.ALCHEMICAL_COAL, 512);
        addEMCValue(ItemRegistry.MOBIUS_FUEL, 2048);
        addEMCValue(ItemRegistry.AETERNALIS_FUEL, 8192);

    }

    static public void generateRecipeEMC(MinecraftServer server) {
        for (Recipe<?> recipe: server.getOverworld().getRecipeManager().listAllOfType(RecipeType.CRAFTING)) {
            boolean ingredientsHaveEMC = true;
            int totalEMC = 0;
            Item output = recipe.getOutput().getItem();
            if (EMCValues.get(output) != null) {
                continue;
            }
            for (Ingredient ingredient: recipe.getPreviewInputs()) {
                for (Integer id: ingredient.getIds()) {
                    Item item = RecipeFinder.getStackFromId(id).getItem();
                    if (item.equals(ItemRegistry.PHILOSOPHERS_STONE)) {
                        continue;
                    }
                    Integer EMC = EMCValues.get(item);
                    ingredientsHaveEMC = ingredientsHaveEMC && EMC != null ;
                    if (ingredientsHaveEMC) {
                        totalEMC += EMC;
                    }
                    break;
                }
            }
            if (ingredientsHaveEMC) {
                int count = 1;
                if (recipe instanceof ShapedRecipe) {
                    ShapedRecipe newRecipe = (ShapedRecipe)recipe;
                    count = newRecipe.getOutput().getCount();
                }
                if (recipe instanceof ShapelessRecipe) {
                    ShapelessRecipe newRecipe = (ShapelessRecipe)recipe;
                    count = newRecipe.getOutput().getCount();
                }

                addEMCValue(recipe.getOutput().getItem(), totalEMC/count);
            }
        }
    }

    static public void printEMC() {
        for (Item item : EMCValues.keySet()) {
            System.out.println(item.getTranslationKey() + " : " + EMCValues.get(item));

        }
    }
    static public void tagsToEMC() {
        for (Block block : BlockTags.LOGS.values()) {
            addEMCValue(block.asItem(), 32);
        }
        for (Item item : ItemTags.FISHES.values()) {
            addEMCValue(item, 64);
        }
        for (Block block : BlockTags.WOOL.values()) {
            addEMCValue(block.asItem(), 48);
        }
        for (Block block : BlockTags.PLANKS.values()) {
            addEMCValue(block.asItem(), 8);
        }
        for (Block block : BlockTags.FLOWERS.values()) {
            addEMCValue(block.asItem(), 16);
        }
        Tag<Item> tag = TagRegistry.item(new Identifier("c", "saplings"));
        for (Item item : tag.values()) {
            addEMCValue(item, 32);
        }

    }

    static public int getEMC(ItemStack item) {
        Integer value = EMCValues.get(item.getItem());
        float damageDivider = 1;
        if (item.getDamage() != 0 && item.getMaxDamage() != 0) {
            damageDivider = ((item.getMaxDamage() - item.getDamage())/(float)item.getMaxDamage());
        }
        return (value != null) ? MathHelper.floor((value * item.getCount()) * damageDivider) : 0;
    }
    static public int getEMC(Item item) {
        Integer value = EMCValues.get(item);
        return (value != null) ? value : 0;

    }
    static public void addEMCValue(Item item, Integer integer) {
        EMCValues.put(item, integer);
    }



    static {
        EMCValues = new HashMap<>();
    }
}
