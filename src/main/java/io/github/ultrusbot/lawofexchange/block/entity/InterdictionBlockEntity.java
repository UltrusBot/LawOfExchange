package io.github.ultrusbot.lawofexchange.block.entity;

import io.github.ultrusbot.lawofexchange.entity.projectile.SwiftwolfsRendingGaleProjectileEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class InterdictionBlockEntity extends BlockEntity implements Tickable {
    public InterdictionBlockEntity() {
        super(BlockEntityRegistry.INTERDICTION_BLOCK_ENTITY);
    }

    @Override
    public void tick() {
        BlockPos pos = this.pos;
        world.getOtherEntities(null, new Box(pos).expand(5), entity -> {
            if (!(entity instanceof HostileEntity)) {return false;}
            Vec3d userPos = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
            Vec3d entityPos = entity.getPos();
            Vec3d pushDir = ((userPos.add(entityPos).multiply(0.5D)).subtract(userPos)).multiply(0.2D);
            entity.addVelocity(pushDir.x, pushDir.y, pushDir.z);
            return false;
        } );
    }
}
