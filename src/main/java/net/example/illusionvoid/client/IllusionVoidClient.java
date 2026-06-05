package net.example.illusionvoid.client;

import net.example.illusionvoid.IllusionVoidMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IllusionVoidClient implements ClientModInitializer {
    
    // Карта для отслеживания затемнённых блоков
    private static final Map<BlockPos, Integer> activeDarkBlocks = new HashMap<>();
    
    @Override
    public void onInitializeClient() {
        WorldRenderEvents.END.register(context -> {
            updateDarkBlocks();
            renderDarknessOverlay();
        });
        
        System.out.println("Illusion Void Client initialized!");
    }
    
    private void updateDarkBlocks() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;
        
        BlockPos playerPos = client.player.getBlockPos();
        int radius = 6; // Радиус проверки
        
        // Очищаем старые записи
        activeDarkBlocks.clear();
        
        // Проверяем блоки в радиусе
        for (int x = -radius; x <= radius; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = playerPos.add(x, y, z);
                    if (client.world.getBlockState(checkPos).isOf(IllusionVoidMod.ILLUSION_VOID_BLOCK)) {
                        double distance = playerPos.getSquaredDistance(checkPos);
                        if (distance <= 25) { // Радиус 5 блоков
                            int intensity = calculateIntensity(distance);
                            activeDarkBlocks.put(checkPos, intensity);
                        }
                    }
                }
            }
        }
    }
    
    private int calculateIntensity(double squaredDistance) {
        // Чем ближе игрок, тем темнее эффект
        if (squaredDistance <= 1) return 255; // Максимальная чернота
        if (squaredDistance <= 3) return 200;
        if (squaredDistance <= 4) return 150;
        if (squaredDistance <= 5) return 80;
        return 0;
    }
    
    private void renderDarknessOverlay() {
        if (activeDarkBlocks.isEmpty()) return;
        
        // Здесь будет код для отрисовки черного оверлея на экране
        // Вместо изменения текстуры блока, мы рисуем черный слой поверх экрана
        // с разной прозрачностью в зависимости от близости
    }
}