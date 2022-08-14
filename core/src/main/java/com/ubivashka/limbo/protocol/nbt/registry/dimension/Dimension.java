package com.ubivashka.limbo.protocol.nbt.registry.dimension;

import com.ubivashka.limbo.nbt.CompoundTaggable;
import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.TagDataType;

import dev.simplix.protocolize.api.util.ProtocolVersions;

public class Dimension implements CompoundTaggable {
    public static final DimensionBuilder OVERWORLD_BUILDER = Dimension.builder().withEffects("minecraft:the_end");
    @TagData(type = TagDataType.BYTE)
    private boolean piglinSafe;
    @TagData(type = TagDataType.INT, minVersion = ProtocolVersions.MINECRAFT_1_19)
    private int monsterSpawnLightLevel;
    @TagData(type = TagDataType.INT, minVersion = ProtocolVersions.MINECRAFT_1_19)
    private int monsterSpawnBlockLightLimit;
    @TagData(type = TagDataType.BYTE)
    private boolean natural;
    @TagData(type = TagDataType.FLOAT)
    private float ambientLight;
    @TagData(type = TagDataType.LONG, isOptional = true)
    private Long fixedTime;
    @TagData(type = TagDataType.STRING, method = "infiniburn")
    private String infiniburn;
    @TagData(type = TagDataType.BYTE)
    private boolean respawnAnchorWorks;
    @TagData(type = TagDataType.BYTE)
    private boolean hasSkylight;
    @TagData(type = TagDataType.BYTE)
    private boolean bedWorks;
    @TagData(type = TagDataType.STRING)
    private String effects;
    @TagData(type = TagDataType.BYTE)
    private boolean hasRaids;
    @TagData(type = TagDataType.INT, minVersion = ProtocolVersions.MINECRAFT_1_17)
    private int minY;
    @TagData(type = TagDataType.INT, minVersion = ProtocolVersions.MINECRAFT_1_17)
    private int height;
    @TagData(type = TagDataType.INT)
    private int logicalHeight;
    @TagData(type = TagDataType.FLOAT)
    private float coordinateScale;
    @TagData(type = TagDataType.BYTE)
    private boolean ultrawarm;
    @TagData(type = TagDataType.BYTE)
    private boolean hasCeiling;

    public Dimension(boolean piglinSafe, int monsterSpawnLightLevel, int monsterSpawnBlockLightLimit, boolean natural, float ambientLight, Long fixedTime,
            String infiniburn,
            boolean respawnAnchorWorks, boolean hasSkylight, boolean bedWorks, String effects,
            boolean hasRaids, int minY, int height, int logicalHeight, float coordinateScale, boolean ultrawarm, boolean hasCeiling) {
        this.piglinSafe = piglinSafe;
        this.monsterSpawnLightLevel = monsterSpawnLightLevel;
        this.monsterSpawnBlockLightLimit = monsterSpawnBlockLightLimit;
        this.natural = natural;
        this.ambientLight = ambientLight;
        this.fixedTime = fixedTime;
        this.infiniburn = infiniburn;
        this.respawnAnchorWorks = respawnAnchorWorks;
        this.hasSkylight = hasSkylight;
        this.bedWorks = bedWorks;
        this.effects = effects;
        this.hasRaids = hasRaids;
        this.minY = minY;
        this.height = height;
        this.logicalHeight = logicalHeight;
        this.coordinateScale = coordinateScale;
        this.ultrawarm = ultrawarm;
        this.hasCeiling = hasCeiling;
    }

    private String infiniburn(int protocolVersion) {
        if (protocolVersion >= ProtocolVersions.MINECRAFT_1_18_2)
            return "#" + infiniburn;
        return infiniburn;
    }

    public boolean isPiglinSafe() {
        return piglinSafe;
    }

    public int getMonsterSpawnLightLevel() {
        return monsterSpawnLightLevel;
    }

    public int getMonsterSpawnBlockLightLimit() {
        return monsterSpawnBlockLightLimit;
    }

    public boolean isNatural() {
        return natural;
    }

    public float getAmbientLight() {
        return ambientLight;
    }

    public Long getFixedTime() {
        return fixedTime;
    }

    public void setFixedTime(Long fixedTime) {
        this.fixedTime = fixedTime;
    }

    public String getInfiniburn() {
        return infiniburn;
    }

    public boolean isRespawnAnchorWorks() {
        return respawnAnchorWorks;
    }

    public boolean hasSkylight() {
        return hasSkylight;
    }

    public boolean isBedWorks() {
        return bedWorks;
    }

    public String getEffects() {
        return effects;
    }

    public boolean hasRaids() {
        return hasRaids;
    }

    public int getLogicalHeight() {
        return logicalHeight;
    }

    public float getCoordinateScale() {
        return coordinateScale;
    }

    public boolean isUltrawarm() {
        return ultrawarm;
    }

    public boolean hasCeiling() {
        return hasCeiling;
    }

    public enum Effects {
        OVERWORLD("minecraft:overworld"), NETHER("minecraft:the_nether"), END("minecraft:the_end");
        private final String tag;

        Effects(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }

    public enum Type {
        NETHER("minecraft:the_nether", -1), OVERWORLD("minecraft:overworld", 0), END("minecraft:the_end", 1);
        private final String effectsType;
        private final int dimensionId;

        Type(String effectsType, int dimensionId) {
            this.effectsType = effectsType;
            this.dimensionId = dimensionId;
        }

        public String getEffectsType() {
            return effectsType;
        }

        public int getDimensionId() {
            return dimensionId;
        }
    }

    public static DimensionBuilder builder() {
        return new DimensionBuilder();
    }

    public static final class DimensionBuilder {
        private boolean piglinSafe = false;
        private int monsterSpawnLightLevel = 0;
        private int monsterSpawnBlockLightLimit = 0;
        private boolean natural = false;
        private float ambientLight = 0;
        private Long fixedTime = null;
        private String infiniburn = "minecraft:infiniburn_overworld";
        private boolean respawnAnchorWorks = false;
        private boolean hasSkylight = true;
        private boolean bedWorks = false;
        private String effects = Type.OVERWORLD.getEffectsType();
        private boolean hasRaids = false;
        private int minY = -64;
        private int height = 256;
        private int logicalHeight = 256;
        private float coordinateScale = 1.0f;
        private boolean ultrawarm = false;
        private boolean hasCeiling = false;

        private DimensionBuilder() {
        }

        public DimensionBuilder withPiglinSafe(boolean piglinSafe) {
            this.piglinSafe = piglinSafe;
            return this;
        }

        public DimensionBuilder withMonsterSpawnLightLevel(int monsterSpawnLightLevel) {
            this.monsterSpawnLightLevel = monsterSpawnLightLevel;
            return this;
        }

        public DimensionBuilder withMonsterSpawnBlockLightLimit(int monsterSpawnBlockLightLimit) {
            this.monsterSpawnBlockLightLimit = monsterSpawnBlockLightLimit;
            return this;
        }


        public DimensionBuilder withNatural(boolean natural) {
            this.natural = natural;
            return this;
        }

        public DimensionBuilder withAmbientLight(float ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public DimensionBuilder withFixedTime(Long fixedTime) {
            this.fixedTime = fixedTime;
            return this;
        }

        public DimensionBuilder withInfiniburn(String infiniburn) {
            this.infiniburn = infiniburn;
            return this;
        }

        public DimensionBuilder withRespawnAnchorWorks(boolean respawnAnchorWorks) {
            this.respawnAnchorWorks = respawnAnchorWorks;
            return this;
        }

        public DimensionBuilder withHasSkylight(boolean hasSkylight) {
            this.hasSkylight = hasSkylight;
            return this;
        }

        public DimensionBuilder withBedWorks(boolean bedWorks) {
            this.bedWorks = bedWorks;
            return this;
        }

        public DimensionBuilder withEffects(String effects) {
            this.effects = effects;
            return this;
        }

        public DimensionBuilder withHasRaids(boolean hasRaids) {
            this.hasRaids = hasRaids;
            return this;
        }

        public DimensionBuilder withMinY(int minY) {
            this.minY = minY;
            return this;
        }

        public DimensionBuilder withLogicalHeight(int logicalHeight) {
            this.logicalHeight = logicalHeight;
            return this;
        }

        public DimensionBuilder withHeight(int height) {
            this.height = height;
            return this;
        }


        public DimensionBuilder withCoordinateScale(float coordinateScale) {
            this.coordinateScale = coordinateScale;
            return this;
        }

        public DimensionBuilder withUltrawarm(boolean ultrawarm) {
            this.ultrawarm = ultrawarm;
            return this;
        }

        public DimensionBuilder withHasCeiling(boolean hasCeiling) {
            this.hasCeiling = hasCeiling;
            return this;
        }

        public Dimension build() {
            return new Dimension(piglinSafe, monsterSpawnLightLevel, monsterSpawnBlockLightLimit, natural, ambientLight, fixedTime, infiniburn,
                    respawnAnchorWorks, hasSkylight, bedWorks, effects,
                    hasRaids, minY, height, logicalHeight, coordinateScale, ultrawarm,
                    hasCeiling);
        }
    }
}
