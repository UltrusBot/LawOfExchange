package io.github.ultrusbot.lawofexchange.mixin;


import io.github.ladysnake.pal.VanillaAbilities;
import io.github.ultrusbot.lawofexchange.items.ItemRegistry;
import io.github.ultrusbot.lawofexchange.items.SwiftwolfsRendingGaleItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinLOE extends LivingEntity {


    @Shadow @Final public PlayerInventory inventory;

    protected PlayerEntityMixinLOE(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method= "tick", cancellable = true)
    public void updateItems(CallbackInfo ci) {
        if (!this.world.isClient) {
            if (SwiftwolfsRendingGaleItem.SWIFTWOLF_ABILITY.grants((PlayerEntity) (Object) this, VanillaAbilities.ALLOW_FLYING) && !this.inventory.contains(new ItemStack(ItemRegistry.SWIFTWOLFS_RENDING_GALE))) {
                SwiftwolfsRendingGaleItem.SWIFTWOLF_ABILITY.revokeFrom((PlayerEntity) (Object) this, VanillaAbilities.ALLOW_FLYING);
            }
        }
    }

}
