package io.github.ultrusbot.lawofexchange.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ProjectileItem {
    /**
     *
     * @param stack ItemStack that is shooting the projectile
     * @param world
     * @param user User shooting the projectile
     */
    public void shootProjectile(ItemStack stack, World world, LivingEntity user);
}
