package io.github.ultrusbot.lawofexchange.block.entity;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.block.BlockRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntityRegistry {

    public static BlockEntityType<AlchemicalChestEntity> ALCHEMICAL_CHEST_ENTITY;
    public static BlockEntityType<DarkMatterFurnaceEntity> DARK_MATTER_FURNACE_ENTITY;
    public static BlockEntityType<EnergyCondenserBlockEntity> ENERGY_CONDENSER_BLOCK_ENTITY;
    public static BlockEntityType<InterdictionBlockEntity> INTERDICTION_BLOCK_ENTITY;

    public static void register() {
        ALCHEMICAL_CHEST_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_chest"), BlockEntityType.Builder.create(AlchemicalChestEntity::new, BlockRegistry.ALCHEMICAL_CHEST).build(null));
        DARK_MATTER_FURNACE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LawOfExchangeMod.MOD_ID, "dark_matter_furnace"), BlockEntityType.Builder.create(DarkMatterFurnaceEntity::new, BlockRegistry.DARK_MATTER_FURNACE).build(null));
        ENERGY_CONDENSER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LawOfExchangeMod.MOD_ID, "energy_condenser"), BlockEntityType.Builder.create(EnergyCondenserBlockEntity::new, BlockRegistry.ENERGY_CONDENSER).build(null));
        INTERDICTION_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LawOfExchangeMod.MOD_ID, "interdiction_block"), BlockEntityType.Builder.create(InterdictionBlockEntity::new, BlockRegistry.INTERDICTION_TORCH, BlockRegistry.INTERDICTION_WALL_TORCH).build(null));
    }
}
