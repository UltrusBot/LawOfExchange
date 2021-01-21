package io.github.ultrusbot.lawofexchange.network;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.client.KeybindRegistry;
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
import net.minecraft.util.Identifier;

public class CustomKeybindPackets {
    private static final Identifier LOE_KEYBIND_PACKET = new Identifier(LawOfExchangeMod.MOD_ID, "tool_keybind_press");


    public static void readKeybindPacket() {
        ServerSidePacketRegistryImpl.INSTANCE.register(LOE_KEYBIND_PACKET, (CustomKeybindPackets::readPacket));
    }

    @Environment(EnvType.CLIENT)
    public static void sendKeybindPacket(ItemStack item, KeybindRegistry.KEY key) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeItemStack(item);
        buf.writeEnumConstant(key);
        MinecraftClient.getInstance().getNetworkHandler().getConnection().send(new CustomPayloadC2SPacket(LOE_KEYBIND_PACKET, new PacketByteBuf(buf)));

    }
    private static void readPacket(PacketContext context, PacketByteBuf buffer) {
        ItemStack item = buffer.readItemStack();
        KeybindRegistry.KEY key = buffer.readEnumConstant(KeybindRegistry.KEY.class);
        context.getTaskQueue().execute(() ->{
            switch(key) {
                case FIRE_PROJECTILE:
                    if (item.getItem() instanceof ProjectileItem) {
                        ProjectileItem projectileItem = (ProjectileItem)item.getItem();
                        projectileItem.shootProjectile(item, context.getPlayer().world, context.getPlayer());
                    }
                    break;
                case CHANGE_MODE:
                    break;
                case CHARGE_ITEM:
                    break;
                case ARMOR_EFFECT:
                    break;
            }
        });
    }
}
