package io.github.ultrusbot.lawofexchange.config;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.client.gui.screen.Screen;

public class LawOfExchangeModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> (Screen) AutoConfig.getConfigScreen(PhilosopherStoneConfig.class, parent).get();
    }
}
