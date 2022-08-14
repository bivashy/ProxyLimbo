package com.ubivashka.limbo.protocol.nbt.registry.biome;


import com.ubivashka.limbo.nbt.CompoundTaggable;
import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.TagDataType;
import com.ubivashka.limbo.protocol.nbt.registry.biome.effect.BiomeEffects;

import dev.simplix.protocolize.api.util.ProtocolVersions;

public class Biome implements CompoundTaggable {
    @TagData(type = TagDataType.STRING)
    private Precipitation precipitation;
    @TagData(type = TagDataType.FLOAT)
    private float depth;
    @TagData(type = TagDataType.FLOAT)
    private float temperature;
    @TagData(type = TagDataType.FLOAT)
    private float scale;
    @TagData(type = TagDataType.FLOAT)
    private float downfall;
    @TagData(type = TagDataType.STRING)
    private Category category;
    @TagData(type = TagDataType.STRING, isOptional = true)
    private String temperatureModifier;
    @TagData(type = TagDataType.COMPOUND)
    private BiomeEffects effects;
    @TagData(type = TagDataType.COMPOUND, isOptional = true, maxVersion = ProtocolVersions.MINECRAFT_1_18_2)
    private BiomeParticle particle;

    public Biome(Precipitation precipitation, float depth, float temperature, float scale, float downfall, Category category, String temperatureModifier,
            BiomeEffects effects, BiomeParticle particle) {
        this.precipitation = precipitation;
        this.depth = depth;
        this.temperature = temperature;
        this.scale = scale;
        this.downfall = downfall;
        this.category = category;
        this.temperatureModifier = temperatureModifier;
        this.effects = effects;
        this.particle = particle;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public float getDepth() {
        return depth;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getScale() {
        return scale;
    }

    public float getDownfall() {
        return downfall;
    }

    public Category getCategory() {
        return category;
    }

    public String getTemperatureModifier() {
        return temperatureModifier;
    }

    public BiomeEffects getEffects() {
        return effects;
    }

    public BiomeParticle getParticle() {
        return particle;
    }

    public static class BiomeParticle {
        @TagData(type = TagDataType.FLOAT)
        private float probability;
        @TagData(type = TagDataType.COMPOUND)
        private Options options;

        public BiomeParticle(float probability, Options options) {
            this.probability = probability;
            this.options = options;
        }

        public float getProbability() {
            return probability;
        }

        public Options getOptions() {
            return options;
        }

        public static class Options {
            @TagData(type = TagDataType.STRING)
            private String type;

            public Options(String type) {
                this.type = type;
            }

            public String getType() {
                return type;
            }
        }
    }

    public enum Category {
        OCEAN, PLAINS, DESERT, FOREST, EXTREME_HILLS, TAIGA, SWAMP, RIVER, NETHER, THE_END, ICY, MUSHROOM, BEACH, JUNGLE, MESA, SAVANNA, NONE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Type {
        THE_VOID, PLAINS, SUNFLOWER_PLAINS, SNOWY_PLAINS, ICE_SPIKES, DESERT, SWAMP, MANGROVE_SWAMP, FOREST, FLOWER_FOREST, BIRCH_FOREST, DARK_FOREST,
        OLD_GROWTH_BIRCH_FOREST, OLD_GROWTH_PINE_TAIGA, OLD_GROWTH_SPRUCE_TAIGA, TAIGA, SNOWY_TAIGA, SAVANNA, SAVANNA_PLATEAU, WINDSWEPT_HILLS,
        WINDSWEPT_GRAVELLY_HILLS, WINDSWEPT_FOREST, WINDSWEPT_SAVANNA, JUNGLE, SPARSE_JUNGLE, BAMBOO_JUNGLE, BADLANDS, ERODED_BADLANDS, WOODED_BADLANDS,
        MEADOW, GROVE, SNOWY_SLOPES, FROZEN_PEAKS, JAGGED_PEAKS, STONY_PEAKS, RIVER, FROZEN_RIVER, BEACH, SNOWY_BEACH, STONY_SHORE, WARM_OCEAN,
        LUKEWARM_OCEAN, DEEP_LUKEWARM_OCEAN, OCEAN, DEEP_OCEAN, COLD_OCEAN, DEEP_COLD_OCEAN, FROZEN_OCEAN, DEEP_FROZEN_OCEAN, MUSHROOM_FIELDS,
        DRIPSTONE_CAVES, LUSH_CAVES, DEEP_DARK, NETHER_WASTES, WARPED_FOREST, CRIMSON_FOREST, SOUL_SAND_VALLEY, BASALT_DELTAS, THE_END, END_HIGHLANDS,
        END_MIDLANDS, SMALL_END_ISLANDS, END_BARRENS;
        private static final String NBT_START = "minecraft:";
        private final String nbt;

        Type() {
            nbt = NBT_START + name().toLowerCase();
        }

        public String getNbt() {
            return nbt;
        }
    }

    public enum Precipitation {
        RAIN, SNOW, NONE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public static BiomeBuilder builder() {
        return new BiomeBuilder();
    }

    public static final class BiomeBuilder {
        private Precipitation precipitation = Precipitation.NONE;
        private float depth = 0.125f;
        private float temperature = 0.8f;
        private float scale = 0.05f;
        private float downfall = 0.4f;
        private Category category = Category.PLAINS;
        private String temperatureModifier;
        private BiomeEffects effects = BiomeEffects.builder().build();
        private BiomeParticle particle;

        private BiomeBuilder() {
        }

        public BiomeBuilder withPrecipitation(Precipitation precipitation) {
            this.precipitation = precipitation;
            return this;
        }

        public BiomeBuilder withDepth(float depth) {
            this.depth = depth;
            return this;
        }

        public BiomeBuilder withTemperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public BiomeBuilder withScale(float scale) {
            this.scale = scale;
            return this;
        }

        public BiomeBuilder withDownfall(float downfall) {
            this.downfall = downfall;
            return this;
        }

        public BiomeBuilder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public BiomeBuilder withTemperatureModifier(String temperatureModifier) {
            this.temperatureModifier = temperatureModifier;
            return this;
        }

        public BiomeBuilder withEffects(BiomeEffects effects) {
            this.effects = effects;
            return this;
        }

        public BiomeBuilder withParticle(BiomeParticle particle) {
            this.particle = particle;
            return this;
        }

        public Biome build() {
            return new Biome(precipitation, depth, temperature, scale, downfall, category, temperatureModifier, effects, particle);
        }
    }
}
