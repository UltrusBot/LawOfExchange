package io.github.ultrusbot.lawofexchange.entity.explosives;

import io.github.ultrusbot.lawofexchange.entity.EntityTypeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NovaCataclysmEntity extends NovaCatalystEntity {
    public NovaCataclysmEntity(EntityType<? extends NovaCatalystEntity> entityType, World world) {
        super(entityType, world);
        this.fuseTimer = 80;
        this.inanimate = true;
        this.explosionPower = 50f;
    }

    public NovaCataclysmEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(EntityTypeRegistry.NOVA_CATACLYSM, world);
        this.updatePosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465D;
        this.setVelocity(-Math.sin(d) * 0.02D, 0.20000000298023224D, -Math.cos(d) * 0.02D);
        this.setFuse(80);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }
}