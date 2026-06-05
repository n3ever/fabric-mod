package net.example.illusionvoid.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class IllusionVoidBlock extends BlockWithEntity {
    
    public IllusionVoidBlock(Settings settings) {
        super(settings);
    }
    
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Блок не имеет контура - через него можно пройти
        return VoxelShapes.empty();
    }
    
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Блок не коллидится - можно проходить сквозь него
        return VoxelShapes.empty();
    }
    
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IllusionVoidBlockEntity(pos, state);
    }
    
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            if (world.getBlockEntity(pos) instanceof IllusionVoidBlockEntity blockEntity) {
                // Очищаем данные при разрушении блока
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}