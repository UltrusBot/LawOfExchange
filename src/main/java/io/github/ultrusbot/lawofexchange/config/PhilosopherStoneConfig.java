package io.github.ultrusbot.lawofexchange.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "lawofexchange")
public class PhilosopherStoneConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min=0, max=100)
    int darkMatterFurnaceDoubleChance = 50;
    @ConfigEntry.BoundedDiscrete(min=1, max=20  )
    int darkMatterFurnaceSpeedMultiplier = 20;
    @ConfigEntry.BoundedDiscrete(min=0, max=100)
    int redMatterFurnaceDoubleChance = 100;



}