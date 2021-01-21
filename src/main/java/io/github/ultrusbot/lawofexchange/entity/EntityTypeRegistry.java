package io.github.ultrusbot.lawofexchange.entity;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.entity.projectile.SwiftwolfsRendingGaleProjectileEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityTypeRegistry {
    public static EntityType<SwiftwolfsRendingGaleProjectileEntity> SWIFTWOLFS_RENDING_GALE_PROJECTILE_ENTITY_ENTITY_TYPE;



    public static void register() {
        SWIFTWOLFS_RENDING_GALE_PROJECTILE_ENTITY_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(LawOfExchangeMod.MOD_ID, "swrg_projectile"),
                FabricEntityTypeBuilder.<SwiftwolfsRendingGaleProjectileEntity>create(SpawnGroup.MISC, SwiftwolfsRendingGaleProjectileEntity::new).build());

    }

}
