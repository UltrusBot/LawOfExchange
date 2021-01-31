package io.github.ultrusbot.lawofexchange.mixin;

import io.github.ultrusbot.lawofexchange.emc.EmcController;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ItemEMCTooltipMixin {

    @Inject(at = @At("HEAD"),method= "appendTooltip(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Ljava/util/List;Lnet/minecraft/client/item/TooltipContext;)V")
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        int EMC = EmcController.getEMC(stack);
        if (EMC != 0) {
            tooltip.add(new TranslatableText("text.lawofexchange.emc").append(String.valueOf(EMC)));
        }
    }
}
