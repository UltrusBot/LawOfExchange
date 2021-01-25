package io.github.ultrusbot.lawofexchange.client;

import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import io.github.ultrusbot.lawofexchange.client.render.entity.SWRGEntityRenderer;
import io.github.ultrusbot.lawofexchange.entity.EntityTypeRegistry;
import io.github.ultrusbot.lawofexchange.gui.GUIRegistry;
import io.github.ultrusbot.lawofexchange.gui.alchemical_chest.AlchemicalChestGuiDescription;
import io.github.ultrusbot.lawofexchange.gui.alchemical_chest.AlchemicalChestScreen;
import io.github.ultrusbot.lawofexchange.items.ItemRegistry;
import io.github.ultrusbot.lawofexchange.network.CustomEntitySpawnS2CPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderingRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class LawOfExchangeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.<AlchemicalChestGuiDescription, AlchemicalChestScreen>register(GUIRegistry.ALCHEMICAL_CHEST_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new AlchemicalChestScreen(gui, inventory.player, title));
        ArmorRenderingRegistry.registerSimpleTexture(new Identifier(LawOfExchangeMod.MOD_ID, "dark_matter"), ItemRegistry.DARK_MATTER_HELMET, ItemRegistry.DARK_MATTER_CHESTPLATE, ItemRegistry.DARK_MATTER_LEGGINGS, ItemRegistry.DARK_MATTER_BOOTS);
        ArmorRenderingRegistry.registerSimpleTexture(new Identifier(LawOfExchangeMod.MOD_ID, "exotic_matter"), ItemRegistry.EXOTIC_MATTER_HELMET, ItemRegistry.EXOTIC_MATTER_CHESTPLATE, ItemRegistry.EXOTIC_MATTER_LEGGINGS, ItemRegistry.EXOTIC_MATTER_BOOTS);

        KeybindRegistry.register();
        EntityRendererRegistry.INSTANCE.register(EntityTypeRegistry.SWIFTWOLFS_RENDING_GALE_PROJECTILE_ENTITY_ENTITY_TYPE, (dispatcher, context) -> new SWRGEntityRenderer(dispatcher));
        ClientSidePacketRegistry.INSTANCE.register(CustomEntitySpawnS2CPacket.LOW_ENTITY_SPAWN_PACKET, ((context, buffer) -> {
            CustomEntitySpawnS2CPacket.readPacket(context, buffer);
        }));
    }
}
