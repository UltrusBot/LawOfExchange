package io.github.ultrusbot.lawofexchange.client;

import io.github.ultrusbot.lawofexchange.network.CustomKeybindPackets;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeybindRegistry {
    public static KeyBinding projectileKey;

    public enum KEY {
        FIRE_PROJECTILE,
        CHARGE_ITEM,
        CHANGE_MODE,
        ARMOR_EFFECT
    }

    public static void register() {
        projectileKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lawofexchange.fire_projectile", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "key.category.law_of_exchange"));

    }
}
