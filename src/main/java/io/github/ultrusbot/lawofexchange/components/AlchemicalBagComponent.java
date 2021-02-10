package io.github.ultrusbot.lawofexchange.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import io.github.ultrusbot.lawofexchange.inventory.AlchemicalBagInventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public class AlchemicalBagComponent implements ComponentV3 {
//    private AlchemicalBagInventory[] alchemicalBagInventories = new AlchemicalBagInventory[16];
    private AlchemicalBagInventory alchemicalBag = new AlchemicalBagInventory();
    private int id;

    public AlchemicalBagComponent(int id) {
        super();
        this.id = id;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
//        for (int i = 0; i < alchemicalBagInventories.length; i++) {
//            alchemicalBagInventories[i].readTags(tag.getList("alchemicalBag" + i, 10));
//        }
        alchemicalBag.readTags(tag.getList("alchemicalBag" + id, 10));
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
//        for (int i = 0; i < alchemicalBagInventories.length; i++) {
//            AlchemicalBagInventory alchemicalBagInventory = alchemicalBagInventories[i];
//            ListTag alchemicalTag = alchemicalBagInventory.getTags();
//            System.out.println(alchemicalTag.asString());
//            tag.put("alchemicalBag" + i, alchemicalTag );
//        }
        tag.put("alchemicalBag" + id, alchemicalBag.getTags());
    }
    public AlchemicalBagInventory getInventory() {
        return alchemicalBag;
    }
}
