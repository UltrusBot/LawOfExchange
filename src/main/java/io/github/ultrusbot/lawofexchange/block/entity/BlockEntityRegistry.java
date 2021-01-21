package io.github.ultrusbot.lawofexchange.block.entity;

import io.github.ultrusbot.lawofexchange.block.BlockRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BlockEntityRegistry {

    public static BlockEntityType<AlchemicalChestEntity> ALCHEMICAL_CHEST_ENTITY;
    public static void register() {
        ALCHEMICAL_CHEST_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "modid:demo", BlockEntityType.Builder.create(AlchemicalChestEntity::new, BlockRegistry.ALCHEMICAL_CHEST).build(null));
    }
}
