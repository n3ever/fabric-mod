package net.example.illusionvoid;

import net.example.illusionvoid.block.IllusionVoidBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class IllusionVoidMod implements ModInitializer {
    
    public static final String MOD_ID = "illusion_void";
    
    public static final Block ILLUSION_VOID_BLOCK = new IllusionVoidBlock(
        FabricBlockSettings.copyOf(Blocks.GLASS)
            .nonOpaque()
            .luminance(state -> 0)
            .strength(0.5f)
            .requiresTool()
    );
    
    @Override
    public void onInitialize() {
        // Регистрация блока
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "illusion_void_block"), ILLUSION_VOID_BLOCK);
        
        // Регистрация предмета (для получения блока)
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "illusion_void_block"),
            new BlockItem(ILLUSION_VOID_BLOCK, new FabricItemSettings()));
        
        System.out.println("Illusion Void Block mod initialized!");
    }
}