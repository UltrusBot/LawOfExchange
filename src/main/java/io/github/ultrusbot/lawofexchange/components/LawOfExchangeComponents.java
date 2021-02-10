package io.github.ultrusbot.lawofexchange.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import io.github.ultrusbot.lawofexchange.LawOfExchangeMod;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.block.MaterialColor;
import net.minecraft.util.Identifier;

public class LawOfExchangeComponents implements EntityComponentInitializer {
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_WHITE = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_white"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_ORANGE = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_orange"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_MAGENTA = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_magenta"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_LIGHT_BLUE = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_light_blue"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_YELLOW = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_yellow"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_LIME = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_lime"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_PINK = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_pink"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_GRAY = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_gray"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_LIGHT_GRAY = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_light_gray"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_CYAN = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_cyan"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_PURPLE = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_purple"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_BLUE = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_blue"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_BROWN = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_brown"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_GREEN = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_green"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_RED = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_red"), AlchemicalBagComponent.class);
    public static final ComponentKey<AlchemicalBagComponent> ALCHEMICAL_BAG_BLACK = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(LawOfExchangeMod.MOD_ID, "alchemical_bag_black"), AlchemicalBagComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(ALCHEMICAL_BAG_WHITE, player -> new AlchemicalBagComponent(0), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_ORANGE, player -> new AlchemicalBagComponent(1), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_MAGENTA, player -> new AlchemicalBagComponent(2), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_LIGHT_BLUE, player -> new AlchemicalBagComponent(3), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_YELLOW, player -> new AlchemicalBagComponent(4), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_LIME, player -> new AlchemicalBagComponent(5), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_PINK, player -> new AlchemicalBagComponent(6), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_GRAY, player -> new AlchemicalBagComponent(7), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_LIGHT_GRAY, player -> new AlchemicalBagComponent(8), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_CYAN, player -> new AlchemicalBagComponent(9), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_PURPLE, player -> new AlchemicalBagComponent(10), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_BLUE, player -> new AlchemicalBagComponent(11), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_BROWN, player -> new AlchemicalBagComponent(12), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_GREEN, player -> new AlchemicalBagComponent(13), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_RED, player -> new AlchemicalBagComponent(14), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ALCHEMICAL_BAG_BLACK, player -> new AlchemicalBagComponent(15), RespawnCopyStrategy.ALWAYS_COPY);


    }
}
