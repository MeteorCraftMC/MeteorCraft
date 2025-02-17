package com.github.meteorcraft;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

import static com.github.meteorcraft.MeteorCraft.MOD_ID;

public class MeteorOres {
//    private static final ConfiguredFeature<?, ?> TIN_ORE = register(Identifier.of(MOD_ID, "tin_ore"), Feature.ORE
//            .configure(new OreFeatureConfig(
//                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
//                    MeteorBlocks.TIN_ORE.getDefaultState(),
//                    9)) // Vein size
//            .range(new RangeDecoratorConfig(
//                    // You can also use one of the other height providers if you don't want a uniform distribution
//                    UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
//            .spreadHorizontally()
//            .repeat(6));
//    private static final ConfiguredFeature<?, ?> DEEPSLATE_TIN_ORE = register(Identifier.of(MOD_ID, "deepslate_tin_ore"), Feature.ORE
//            .configure(new OreFeatureConfig(
//                    OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES,
//                    MeteorBlocks.DEEPSLATE_TIN_ORE.getDefaultState(),
//                    9)) // Vein size
//            .range(new RangeDecoratorConfig(
//                    // You can also use one of the other height providers if you don't want a uniform distribution
//                    UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(10)))) // Inclusive min and max height
//            .spreadHorizontally()
//            .repeat(6));
//    private static final ConfiguredFeature<?, ?> ALUMINUM_ORE = register(Identifier.of(MOD_ID, "aluminum_ore"), Feature.ORE
//            .configure(new OreFeatureConfig(
//                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
//                    MeteorBlocks.ALUMINUM_ORE.getDefaultState(),
//                    9)) // Vein size
//            .range(new RangeDecoratorConfig(
//                    // You can also use one of the other height providers if you don't want a uniform distribution
//                    UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
//            .spreadHorizontally()
//            .repeat(6));
//    private static final ConfiguredFeature<?, ?> DEEPSLATE_ALUMINUM_ORE = register(Identifier.of(MOD_ID, "deepslate_aluminum_ore"), Feature.ORE
//            .configure(new OreFeatureConfig(
//                    OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES,
//                    MeteorBlocks.DEEPSLATE_ALUMINUM_ORE.getDefaultState(),
//                    9)) // Vein size
//            .range(new RangeDecoratorConfig(
//                    // You can also use one of the other height providers if you don't want a uniform distribution
//                    UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(10)))) // Inclusive min and max height
//            .spreadHorizontally()
//            .repeat(6));
//
//    public static ConfiguredFeature<?, ?> register(Identifier identifier, ConfiguredFeature<?, ?> repeat) {
//        RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureRegistryKey = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, identifier);
//        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, configuredFeatureRegistryKey.getValue(), repeat);
//        //noinspection deprecation
//        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, configuredFeatureRegistryKey);
//        return repeat;
//    }
//
    public static void call() {}
}
