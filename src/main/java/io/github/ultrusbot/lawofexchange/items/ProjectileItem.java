package io.github.ultrusbot.lawofexchange.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface ProjectileItem {
    /**
     *
     * @param world
     * @param user User shooting the projectile
     * @param hand Hand used
     */
    public void shootProjectile(World world, LivingEntity user, Hand hand);
}
