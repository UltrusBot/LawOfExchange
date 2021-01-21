package io.github.ultrusbot.lawofexchange.items;

import io.github.ultrusbot.lawofexchange.emc.EMCStorageItem;
import me.shedaniel.cloth.api.durability.bar.DurabilityBarItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KleinStarItem extends Item implements DurabilityBarItem, EMCStorageItem {

    int maxEMC;
    public KleinStarItem(int maxEMC, Settings settings) {
        super(settings);
        this.maxEMC = maxEMC;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        this.setEMC(stack, 0);
        super.onCraft(stack, world, player);
    }

    @Override
    public double getDurabilityBarProgress(ItemStack stack) {
        return (1d - (double)getEMC(stack)/(double)getMaxEMC());
    }

    @Override
    public boolean hasDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public int getDurabilityBarColor(ItemStack stack) {
        return 0x0398fc;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("text.lawofexchange.stored_emc").append(" ").append(Integer.toString(getEMC(stack))));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getEMC(ItemStack itemStack) {
        CompoundTag tag = itemStack.getTag();
        return tag == null ? 0 : tag.getInt("EMC");
    }

    @Override
    public int getMaxEMC() {
        return maxEMC;
    }

    @Override
    public void setEMC(ItemStack itemStack, int emc) {
        itemStack.getOrCreateTag().putInt("EMC", emc);
    }

    @Override
    public void addEMC(ItemStack itemStack, int emc) {
        int curEMC = getEMC(itemStack);
        curEMC+=emc;
        curEMC = MathHelper.clamp(curEMC, 0, maxEMC);
        setEMC(itemStack, curEMC);
    }

    @Override
    public void removeEMC(ItemStack itemStack, int emc) {
        addEMC(itemStack, -emc);
    }

}
