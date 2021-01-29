package io.github.ultrusbot.lawofexchange.mixin;

import io.github.ultrusbot.lawofexchange.items.ItemRegistry;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class PhilosopherStoneItemRemainderMixin {
    @Shadow public abstract Item asItem();
    //Forces Philosopher's Stone to always be returned in a recipe
    @Inject(at = @At("HEAD"), method= "getRecipeRemainder()Lnet/minecraft/item/Item;", cancellable = true)
    public void getRecipeRemainder(CallbackInfoReturnable<@Nullable Item> cir) {
        if (this.asItem().equals(ItemRegistry.PHILOSOPHERS_STONE)) {
            cir.setReturnValue(ItemRegistry.PHILOSOPHERS_STONE);
        }
    }
    @Inject(at = @At("HEAD"), method= "Lnet/minecraft/item/Item;hasRecipeRemainder()Z", cancellable = true)
    public void hasRecipeRemainder(CallbackInfoReturnable<Boolean> cir) {
        if (this.asItem().equals(ItemRegistry.PHILOSOPHERS_STONE)) {
            cir.setReturnValue(true);
        }
    }
}
