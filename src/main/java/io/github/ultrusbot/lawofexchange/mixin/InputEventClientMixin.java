package io.github.ultrusbot.lawofexchange.mixin;

import io.github.ultrusbot.lawofexchange.client.KeybindRegistry;
import io.github.ultrusbot.lawofexchange.network.CustomKeybindPackets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//handleInputEvents
@Mixin(MinecraftClient.class)
public class InputEventClientMixin {


    @Shadow @Nullable public ClientPlayerEntity player;

    @Inject(at = @At("TAIL"),method= "handleInputEvents")
    public void LOEInputEvent(CallbackInfo ci) {
        while (KeybindRegistry.projectileKey.wasPressed()) {
            if (!this.player.getMainHandStack().isEmpty()) {
                CustomKeybindPackets.sendKeybindPacket(this.player.getMainHandStack(), KeybindRegistry.KEY.FIRE_PROJECTILE);
                System.out.println("Sent out a packet!");
            }
        }

    }

}
