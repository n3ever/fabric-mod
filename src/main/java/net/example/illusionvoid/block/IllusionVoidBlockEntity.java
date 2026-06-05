package net.example.illusionvoid.block;

import net.example.illusionvoid.IllusionVoidMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class IllusionVoidBlockEntity extends BlockEntity {
    
    private static final int CHECK_RADIUS = 5; // Радиус проверки игроков
    private int ticksSinceLastCheck = 0;
    
    public IllusionVoidBlockEntity(BlockPos pos, BlockState state) {
        super(null, pos, state); // Нужно будет зарегистрировать тип
    }
    
    public static void tick(World world, BlockPos pos, BlockState state, IllusionVoidBlockEntity blockEntity) {
        if (world.isClient) {
            // На клиенте тоже проверяем для визуального эффекта
            boolean playerNearby = blockEntity.isPlayerNearby(world, pos);
            // Здесь будет обновление рендера
        }
    }
    
    private boolean isPlayerNearby(World world, BlockPos pos) {
        Box checkBox = new Box(pos).expand(CHECK_RADIUS);
        return !world.getPlayers().isEmpty() && 
               world.getPlayers().stream()
                   .anyMatch(player -> player.getBoundingBox().intersects(checkBox));
    }
}