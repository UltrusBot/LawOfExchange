package io.github.ultrusbot.lawofexchange.mixin;

import io.github.ultrusbot.lawofexchange.items.tools.SpecialMatterShears;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(SheepEntity.class)
public abstract class SpecialShearsSheepMixin extends AnimalEntity {

    @Shadow @Final private static Map<DyeColor, ItemConvertible> DROPS;

    @Shadow public abstract DyeColor getColor();

    protected SpecialShearsSheepMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method= "interactMob", cancellable = true)
    public void interactMobDarkMatterShears(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() instanceof SpecialMatterShears) {
            if (!this.world.isClient && ((SheepEntity)(Object)this).isShearable()) {
                this.world.playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (this.random.nextFloat() < 0.5f) {
                    SheepEntity newSheep = EntityType.SHEEP.create(this.world);
                    newSheep.copyPositionAndRotation(((SheepEntity)(Object)this));
                    this.world.spawnEntity(newSheep);
                }
                ((SheepEntity)(Object)this).setSheared(true);
                int i = 5 + this.random.nextInt(4);
                for(int j = 0; j < i; ++j) {
                    ItemEntity itemEntity = this.dropItem((ItemConvertible)DROPS.get(this.getColor()), 1);
                    if (itemEntity != null) {
                        itemEntity.setVelocity(itemEntity.getVelocity().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
                    }
                }
                cir.setReturnValue(ActionResult.SUCCESS);
            } else {
                cir.setReturnValue(ActionResult.CONSUME);
            }
        } else {
            cir.setReturnValue(super.interactMob(player, hand));
        }

    }


}
