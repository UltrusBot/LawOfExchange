package io.github.ultrusbot.lawofexchange.entity.projectile;


import io.github.ultrusbot.lawofexchange.entity.EntityTypeRegistry;
import io.github.ultrusbot.lawofexchange.network.CustomEntitySpawnS2CPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class SwiftwolfsRendingGaleProjectileEntity extends ExplosiveProjectileEntity{


    public SwiftwolfsRendingGaleProjectileEntity(EntityType<SwiftwolfsRendingGaleProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Environment(EnvType.CLIENT)
    public SwiftwolfsRendingGaleProjectileEntity(World world, double x, double y, double z, double directionX, double directionY, double directionZ) {
        super(EntityTypeRegistry.SWIFTWOLFS_RENDING_GALE_PROJECTILE_ENTITY_ENTITY_TYPE, x, y, z, directionX, directionY, directionZ, world);
    }

    public SwiftwolfsRendingGaleProjectileEntity(World world, LivingEntity owner, double directionX, double directionY, double directionZ) {
        super(EntityTypeRegistry.SWIFTWOLFS_RENDING_GALE_PROJECTILE_ENTITY_ENTITY_TYPE, owner, directionX, directionY, directionZ, world);
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (hitResult.getType() != HitResult.Type.MISS) {
            if (!this.world.isClient) {
                LightningEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(this.world);
                lightningBolt.refreshPositionAfterTeleport(hitResult.getPos());
                this.world.spawnEntity(lightningBolt);
                this.remove();
            }
        }
    }

    @Override
    public boolean collides() {
        return false;
    }
    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }
    @Override
    protected boolean isBurning() {
        return false;
    }

    @Override
    protected ParticleEffect getParticleType() {
        return ParticleTypes.PORTAL;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return CustomEntitySpawnS2CPacket.create(this);
    }
}
