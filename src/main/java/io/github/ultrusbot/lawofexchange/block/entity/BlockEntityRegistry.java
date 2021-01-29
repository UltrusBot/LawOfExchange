package io.github.ultrusbot.lawofexchange.block.entity;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.block.BlockRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntityRegistry {

    public static BlockEntityType<AlchemicalChestEntity> ALCHEMICAL_CHEST_ENTITY;
    public static void register() {
        ALCHEMICAL_CHEST_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_chest"), BlockEntityType.Builder.create(AlchemicalChestEntity::new, BlockRegistry.ALCHEMICAL_CHEST).build(null));
    }
}
