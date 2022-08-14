package com.ubivashka.limbo.protocol.nbt.registry.biome.effect;

import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.TagDataType;
import com.ubivashka.limbo.protocol.nbt.registry.biome.Biome.BiomeParticle;

import dev.simplix.protocolize.api.util.ProtocolVersions;

public class BiomeEffects {
    @TagData(type = TagDataType.INT)
    private int skyColor;
    @TagData(type = TagDataType.INT)
    private int waterFogColor;
    @TagData(type = TagDataType.INT)
    private int fogColor;
    @TagData(type = TagDataType.INT)
    private int waterColor;
    @TagData(type = TagDataType.INT, isOptional = true)
    private Integer foliageColor;
    @TagData(type = TagDataType.INT, isOptional = true)
    private Integer grassColor;
    @TagData(type = TagDataType.STRING, isOptional = true)
    private String grassColorModifier;
    @TagData(type = TagDataType.COMPOUND, isOptional = true)
    private Music music;
    @TagData(type = TagDataType.STRING, isOptional = true)
    private String ambientSound;
    @TagData(type = TagDataType.COMPOUND, isOptional = true)
    private AdditionsSound additionsSound;
    @TagData(type = TagDataType.COMPOUND, isOptional = true)
    private MoodSound moodSound;
    @TagData(type = TagDataType.COMPOUND, isOptional = true, minVersion = ProtocolVersions.MINECRAFT_1_19)
    private BiomeParticle particle;

    public BiomeEffects(int skyColor, int waterFogColor, int fogColor, int waterColor, Integer foliageColor, Integer grassColor, String grassColorModifier, Music music, String ambientSound,
                        AdditionsSound additionsSound, MoodSound moodSound, BiomeParticle particle) {
        this.skyColor = skyColor;
        this.waterFogColor = waterFogColor;
        this.fogColor = fogColor;
        this.waterColor = waterColor;
        this.foliageColor = foliageColor;
        this.grassColor = grassColor;
        this.grassColorModifier = grassColorModifier;
        this.music = music;
        this.ambientSound = ambientSound;
        this.additionsSound = additionsSound;
        this.moodSound = moodSound;
        this.particle = particle;
    }

    public int getSkyColor() {
        return skyColor;
    }

    public int getWaterFogColor() {
        return waterFogColor;
    }

    public int getFogColor() {
        return fogColor;
    }

    public int getWaterColor() {
        return waterColor;
    }

    public Integer getFoliageColor() {
        return foliageColor;
    }

    public Integer getGrassColor() {
        return grassColor;
    }

    public String getGrassColorModifier() {
        return grassColorModifier;
    }

    public Music getMusic() {
        return music;
    }

    public String getAmbientSound() {
        return ambientSound;
    }

    public AdditionsSound getAdditionsSound() {
        return additionsSound;
    }

    public MoodSound getMoodSound() {
        return moodSound;
    }

    public BiomeParticle getParticle() {
        return particle;
    }

    public static class Music {
        @TagData(type = TagDataType.BYTE)
        private boolean replaceCurrentMusic;
        @TagData(type = TagDataType.STRING)
        private String sound;
        @TagData(type = TagDataType.INT)
        private int maxDelay;
        @TagData(type = TagDataType.INT)
        private int minDelay;

        public Music(boolean replaceCurrentMusic, String sound, int maxDelay, int minDelay) {
            this.replaceCurrentMusic = replaceCurrentMusic;
            this.sound = sound;
            this.maxDelay = maxDelay;
            this.minDelay = minDelay;
        }

        public boolean shouldReplaceCurrentMusic() {
            return replaceCurrentMusic;
        }

        public String getSound() {
            return sound;
        }

        public int getMaxDelay() {
            return maxDelay;
        }

        public int getMinDelay() {
            return minDelay;
        }
    }

    public static class AdditionsSound {
        @TagData(type = TagDataType.STRING)
        private String sound;
        @TagData(type = TagDataType.DOUBLE)
        private double tickChance;

        public AdditionsSound(String sound, double tickChance) {
            this.sound = sound;
            this.tickChance = tickChance;
        }

        public String getSound() {
            return sound;
        }

        public double getTickChance() {
            return tickChance;
        }
    }

    public static class MoodSound {
        @TagData(type = TagDataType.STRING)
        private String sound;
        @TagData(type = TagDataType.DOUBLE)
        private double tickChance;
        @TagData(type = TagDataType.DOUBLE)
        private double offset;
        @TagData(type = TagDataType.INT)
        private int blockSearchExtent;

        public MoodSound(String sound, double tickChance, double offset, int blockSearchExtent) {
            this.sound = sound;
            this.tickChance = tickChance;
            this.offset = offset;
            this.blockSearchExtent = blockSearchExtent;
        }

        public String getSound() {
            return sound;
        }

        public double getTickChance() {
            return tickChance;
        }

        public double getOffset() {
            return offset;
        }

        public int getBlockSearchExtent() {
            return blockSearchExtent;
        }
    }

    public static BiomeEffectsBuilder builder() {
        return new BiomeEffectsBuilder();
    }

    public static final class BiomeEffectsBuilder {
        private int skyColor = 6592250; // RGB: 100,150,250
        private int waterFogColor = 329010; // RGB: 5,5,50
        private int fogColor = 12505855; // RGB: 190,210,255
        private int waterColor = 4290790; //RGB: 65,120,230
        private Integer foliageColor = null;
        private Integer grassColor = null;
        private String grassColorModifier;
        private Music music = null;
        private String ambientSound = null;
        private AdditionsSound additionsSound = null;
        private MoodSound moodSound = null;
        private BiomeParticle particle = null; // 1.19

        private BiomeEffectsBuilder() {
        }

        public BiomeEffectsBuilder withSkyColor(int skyColor) {
            this.skyColor = skyColor;
            return this;
        }

        public BiomeEffectsBuilder withWaterFogColor(int waterFogColor) {
            this.waterFogColor = waterFogColor;
            return this;
        }

        public BiomeEffectsBuilder withFogColor(int fogColor) {
            this.fogColor = fogColor;
            return this;
        }

        public BiomeEffectsBuilder withWaterColor(int waterColor) {
            this.waterColor = waterColor;
            return this;
        }

        public BiomeEffectsBuilder withFoliageColor(Integer foliageColor) {
            this.foliageColor = foliageColor;
            return this;
        }

        public BiomeEffectsBuilder withGrassColor(Integer grassColor) {
            this.grassColor = grassColor;
            return this;
        }

        public BiomeEffectsBuilder withGrassColorModifier(String grassColorModifier) {
            this.grassColorModifier = grassColorModifier;
            return this;
        }

        public BiomeEffectsBuilder withMusic(Music music) {
            this.music = music;
            return this;
        }

        public BiomeEffectsBuilder withAmbientSound(String ambientSound) {
            this.ambientSound = ambientSound;
            return this;
        }

        public BiomeEffectsBuilder withAdditionsSound(AdditionsSound additionsSound) {
            this.additionsSound = additionsSound;
            return this;
        }

        public BiomeEffectsBuilder withMoodSound(MoodSound moodSound) {
            this.moodSound = moodSound;
            return this;
        }

        public BiomeEffectsBuilder withParticle(BiomeParticle particle) {
            this.particle = particle;
            return this;
        }

        public BiomeEffects build() {
            return new BiomeEffects(skyColor, waterFogColor, fogColor, waterColor, foliageColor, grassColor, grassColorModifier, music, ambientSound, additionsSound, moodSound, particle);
        }
    }
}