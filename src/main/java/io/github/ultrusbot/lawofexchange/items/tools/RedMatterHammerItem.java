package io.github.ultrusbot.lawofexchange.items.tools;

import net.minecraft.item.ToolMaterial;

public class RedMatterHammerItem extends DarkMatterHammerItem {
    public RedMatterHammerItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public int getModeCount() {
        return 3;
    }
}
