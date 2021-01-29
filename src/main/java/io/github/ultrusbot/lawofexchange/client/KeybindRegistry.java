package io.github.ultrusbot.lawofexchange.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeybindRegistry {
    public static KeyBinding projectileKey;
    public static KeyBinding chargeKey;
    public static KeyBinding itemFunction;
    public enum KEY {
        FIRE_PROJECTILE,
        CHARGE_ITEM,
        ACTIVATE_ITEM,
        ARMOR_EFFECT
    }

    public static void register() {
        projectileKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lawofexchange.fire_projectile", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "key.category.law_of_exchange"));
        chargeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lawofexchange.charge_item", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "key.category.law_of_exchange"));
        itemFunction = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lawofexchange.item_function", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "key.category.law_of_exchange"));

    }
}
