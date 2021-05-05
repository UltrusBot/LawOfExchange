package io.github.ultrusbot.lawofexchange;

import io.github.ultrusbot.lawofexchange.block.BlockRegistry;
import io.github.ultrusbot.lawofexchange.block.entity.BlockEntityRegistry;
import io.github.ultrusbot.lawofexchange.emc.EmcController;
import io.github.ultrusbot.lawofexchange.entity.EntityTypeRegistry;
import io.github.ultrusbot.lawofexchange.gui.GUIRegistry;
import io.github.ultrusbot.lawofexchange.items.ItemRegistry;
import io.github.ultrusbot.lawofexchange.network.CustomKeybindPackets;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

public class LawOfExchangeMod implements ModInitializer {
    public static final String MOD_ID = "lawofexchange";
    public static final ItemGroup PHILOSOPHERS_STONE_ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("lawofexchange", "items"),
            () -> new ItemStack(ItemRegistry.PHILOSOPHERS_STONE));

    @Override
    public void onInitialize() {
        ItemRegistry.register();
        BlockRegistry.register();
        BlockEntityRegistry.register();
        GUIRegistry.register();
        EntityTypeRegistry.register();
        CustomKeybindPackets.readKeybindPacket();
        ServerLifecycleEvents.SERVER_STARTED.register((MinecraftServer server) -> {
            EmcController.loadEMCConfig();
            EmcController.tagsToEMC();
            EmcController.generateRecipeEmc(server);
        });
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register(((server, serverResourceManager, success) -> {
            EmcController.loadEMCConfig();
            EmcController.tagsToEMC();
            EmcController.generateRecipeEmc(server);

        }));
    }

}
