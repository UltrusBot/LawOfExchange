package io.github.ultrusbot.lawofexchange.network;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.client.KeybindRegistry;
import io.github.ultrusbot.lawofexchange.items.ChargeableItem;
import io.github.ultrusbot.lawofexchange.items.ProjectileItem;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.impl.networking.ServerSidePacketRegistryImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class CustomKeybindPackets {
    private static final Identifier LOE_KEYBIND_PACKET = new Identifier(LawOfExchangeMod.MOD_ID, "tool_keybind_press");


    public static void readKeybindPacket() {
        ServerSidePacketRegistryImpl.INSTANCE.register(LOE_KEYBIND_PACKET, (CustomKeybindPackets::readPacket));
    }

    @Environment(EnvType.CLIENT)
    public static void sendKeybindPacket(KeybindRegistry.KEY key) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeEnumConstant(key);

        MinecraftClient.getInstance().getNetworkHandler().getConnection().send(new CustomPayloadC2SPacket(LOE_KEYBIND_PACKET, new PacketByteBuf(buf)));

    }
    private static void readPacket(PacketContext context, PacketByteBuf buffer) {
        KeybindRegistry.KEY key = buffer.readEnumConstant(KeybindRegistry.KEY.class);
        ItemStack stack = context.getPlayer().getStackInHand(Hand.MAIN_HAND);
        context.getTaskQueue().execute(() ->{
            switch(key) {
                case FIRE_PROJECTILE:
                    if (stack.getItem() instanceof ProjectileItem) {
                        ProjectileItem projectileItem = (ProjectileItem)stack.getItem();
                        projectileItem.shootProjectile(context.getPlayer().world, context.getPlayer(), Hand.MAIN_HAND);
                    }
                    break;
                case ACTIVATE_ITEM:
                    break;
                case CHARGE_ITEM:
                    if (stack.getItem() instanceof ChargeableItem) {
                        ChargeableItem item = (ChargeableItem)stack.getItem();
                        item.increaseCharge(stack, context.getPlayer().isSneaking() ? -1 : 1);
                        context.getPlayer().getItemCooldownManager().set(stack.getItem(), 10);
                    }
                    break;
                case ARMOR_EFFECT:
                    break;
            }
        });
    }
}
