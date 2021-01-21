package io.github.ultrusbot.lawofexchange.gui.alchemical_chest;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.ultrusbot.lawofexchange.gui.GUIRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public class AlchemicalChestGuiDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 104;

    public AlchemicalChestGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(GUIRegistry.ALCHEMICAL_CHEST_SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(200, 200);
        for (int y = 0; y<8;y++) {
            for (int x = 0; x<13;x++) {
                WItemSlot itemSlot = WItemSlot.of(blockInventory, x + y * 13);
                root.add(itemSlot, x, y);

            }
        }

        root.add(this.createPlayerInventoryPanel(), 2, 8);

        root.validate(this);
    }
}
