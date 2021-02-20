package io.github.ultrusbot.lawofexchange.items.tools;

import io.github.ultrusbot.lawofexchange.items.ItemRegistry;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum CustomToolMaterials implements ToolMaterial {
    DARK_MATTER(4, 9999, 15.0F, 5.0F, 0, () -> {
        return Ingredient.ofItems(ItemRegistry.DARK_MATTER);
    }),
    RED_MATTER(5, 9999, 20.0F, 7.0F, 0, () -> {
        return Ingredient.ofItems(ItemRegistry.DARK_MATTER);
    });;

    private final int durability;
    private final int miningLevel;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;


    CustomToolMaterials(int miningLevel, int durability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy(repairIngredient);
    }
    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
