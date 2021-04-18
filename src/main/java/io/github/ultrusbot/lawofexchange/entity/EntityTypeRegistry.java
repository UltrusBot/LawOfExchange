package io.github.ultrusbot.lawofexchange.entity;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.entity.explosives.NovaCataclysmEntity;
import io.github.ultrusbot.lawofexchange.entity.explosives.NovaCatalystEntity;
import io.github.ultrusbot.lawofexchange.entity.projectile.SwiftwolfsRendingGaleProjectileEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityTypeRegistry {
    public static EntityType<SwiftwolfsRendingGaleProjectileEntity> SWRG_ENTITY_TYPE;
    public static EntityType<NovaCatalystEntity> NOVA_CATALYST;
    public static EntityType<NovaCataclysmEntity> NOVA_CATACLYSM;

    public static void register() {
        SWRG_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(LawOfExchangeMod.MOD_ID, "swrg_projectile"),
                FabricEntityTypeBuilder.<SwiftwolfsRendingGaleProjectileEntity>create(SpawnGroup.MISC, SwiftwolfsRendingGaleProjectileEntity::new).build());
        NOVA_CATALYST = Registry.register(Registry.ENTITY_TYPE, new Identifier(LawOfExchangeMod.MOD_ID, "nova_catalyst"),
                FabricEntityTypeBuilder.<NovaCatalystEntity>create(SpawnGroup.MISC, NovaCatalystEntity::new).dimensions(EntityDimensions.changing(0.98F, 0.98F)).trackRangeBlocks(10).trackedUpdateRate(10).build()
                );
        NOVA_CATACLYSM = Registry.register(Registry.ENTITY_TYPE, new Identifier(LawOfExchangeMod.MOD_ID, "nova_cataclysm"),
                FabricEntityTypeBuilder.<NovaCataclysmEntity>create(SpawnGroup.MISC, NovaCataclysmEntity::new).dimensions(EntityDimensions.changing(0.98F, 0.98F)).trackRangeBlocks(10).trackedUpdateRate(10).build()
        );
    }

}
