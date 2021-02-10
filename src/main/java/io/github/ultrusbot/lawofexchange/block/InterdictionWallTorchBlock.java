package io.github.ultrusbot.lawofexchange.block;

import io.github.ultrusbot.lawofexchange.block.entity.InterdictionBlockEntity;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class InterdictionWallTorchBlock extends WallTorchBlock implements BlockEntityProvider {
    public InterdictionWallTorchBlock(Settings settings, ParticleEffect particleEffect) {
        super(settings, particleEffect);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new InterdictionBlockEntity();
    }
}
