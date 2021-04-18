package io.github.ultrusbot.lawofexchange.entity.explosives;

import io.github.ultrusbot.lawofexchange.entity.EntityTypeRegistry;
import io.github.ultrusbot.lawofexchange.network.CustomEntitySpawnS2CPacket;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class NovaCataclysmEntity extends NovaCatalystEntity {

    public NovaCataclysmEntity(EntityType<? extends NovaCatalystEntity> entityType, World world) {
        super(entityType, world);
        this.fuseTimer = 80;
        this.inanimate = true;
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

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 50.0F, Explosion.DestructionType.BREAK);
    }
}