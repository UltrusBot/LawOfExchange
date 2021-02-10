package io.github.ultrusbot.lawofexchange.gui.alchemicalchest;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class AlchemicalChestScreen extends CottonInventoryScreen<AlchemicalChestGuiDescription> {
    public AlchemicalChestScreen(AlchemicalChestGuiDescription gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }
}
