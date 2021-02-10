package io.github.ultrusbot.lawofexchange.items;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import io.github.ultrusbot.lawofexchange.components.AlchemicalBagComponent;
import io.github.ultrusbot.lawofexchange.gui.AlchemicalContainerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AlchemicalBagItem extends Item {
    private final ComponentKey<AlchemicalBagComponent> alchemicalBagComponent;
    public AlchemicalBagItem(Settings settings, ComponentKey<AlchemicalBagComponent> key) {
        super(settings);
        alchemicalBagComponent = key;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        AlchemicalBagItem item = this;
        ItemStack alchemicalBag = user.getStackInHand(hand);
        user.openHandledScreen(new ExtendedScreenHandlerFactory() {
            @Override
            public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                buf.writeItemStack(user.getStackInHand(hand));
            }

            @Override
            public Text getDisplayName() {
                return Text.of("");
            }

            @Override
            public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                return new AlchemicalContainerScreenHandler(syncId, inv, alchemicalBagComponent.get(player).getInventory(), alchemicalBag);
            }
        });
        return TypedActionResult.success(user.getStackInHand(hand));
    }

}
