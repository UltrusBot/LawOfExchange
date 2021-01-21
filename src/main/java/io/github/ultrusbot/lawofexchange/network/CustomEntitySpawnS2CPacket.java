package io.github.ultrusbot.lawofexchange.network;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class CustomEntitySpawnS2CPacket {
    public static final Identifier LOW_ENTITY_SPAWN_PACKET = new Identifier(LawOfExchangeMod.MOD_ID, "lawofexchange_entityspawn");

    public static Packet<?> create(Entity entity) {
        final PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeVarInt(entity.getEntityId());
        buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(entity.getType()));
        buf.writeUuid(entity.getUuid());
        buf.writeDouble(entity.getX());
        buf.writeDouble(entity.getY());
        buf.writeDouble(entity.getZ());
        buf.writeByte(MathHelper.floor(entity.pitch * 256.0F / 360.0F));
        buf.writeByte(MathHelper.floor(entity.yaw * 256.0F / 360.0F));
        buf.writeFloat(entity.pitch);
        buf.writeFloat(entity.yaw);
        return ServerSidePacketRegistry.INSTANCE.toPacket(LOW_ENTITY_SPAWN_PACKET, buf);
    }
    @Environment(EnvType.CLIENT)
    public static void readPacket(PacketContext context, PacketByteBuf buf) {
        int id = buf.readVarInt();
        EntityType<?> type = Registry.ENTITY_TYPE.get(buf.readVarInt());
        UUID uuid = buf.readUuid();
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        float pitch = (buf.readByte() * 360) / 256.0F;
        float yaw = (buf.readByte() * 360) / 256.0F;

        context.getTaskQueue().execute(() ->
        {
            ClientWorld world = MinecraftClient.getInstance().world;
            Entity entity = type.create(world);
            if (world != null && entity != null)
            {
                entity.updatePosition(x, y, z);
                entity.updateTrackedPosition(x, y, z);
                entity.pitch = pitch;
                entity.yaw = yaw;
                entity.setEntityId(id);
                entity.setUuid(uuid);
                world.addEntity(id, entity);
            }
        });

    }

}
