package com.ubivashka.limbo.bungee.config.limbo.dimension;

import com.ubivashka.configuration.ConfigurationHolder;
import com.ubivashka.configuration.annotation.ConfigField;
import com.ubivashka.configuration.holder.ConfigurationSectionHolder;
import com.ubivashka.limbo.bungee.config.PluginConfig;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.Dimension;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.Dimension.DimensionBuilder;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.Dimension.Type;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.DimensionRegistry.DimensionRegistryEntry;

public class DimensionSettings implements ConfigurationHolder {
    @ConfigField
    private String name = "limbo-dimension";
    @ConfigField
    private Dimension.Type type = Type.OVERWORLD;
    @ConfigField("fixed-time")
    private Long fixedTime;
    private final DimensionRegistryEntry dimensionRegistryEntry;

    public DimensionSettings(ConfigurationSectionHolder sectionHolder) {
        PluginConfig.CONFIGURATION_PROCESSOR.resolve(sectionHolder, this);
        DimensionBuilder dimensionBuilder = Dimension.builder().withEffects(type.getEffectsType());
        if (fixedTime != null)
            dimensionBuilder.withFixedTime(fixedTime);
        dimensionRegistryEntry = new DimensionRegistryEntry(name, 0, dimensionBuilder.build());
    }

    public DimensionRegistryEntry getDimensionRegistryEntry() {
        return dimensionRegistryEntry;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Long getFixedTime() {
        return fixedTime;
    }
}
