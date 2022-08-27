package com.ubivashka.limbo.bungee.config.limbo.biome;

import com.ubivashka.configuration.ConfigurationHolder;
import com.ubivashka.configuration.annotation.ConfigField;
import com.ubivashka.configuration.holder.ConfigurationSectionHolder;
import com.ubivashka.limbo.bungee.config.PluginConfig;
import com.ubivashka.limbo.protocol.nbt.registry.biome.Biome;
import com.ubivashka.limbo.protocol.nbt.registry.biome.Biome.Category;
import com.ubivashka.limbo.protocol.nbt.registry.biome.Biome.Type;
import com.ubivashka.limbo.protocol.nbt.registry.biome.BiomeRegistry.BiomeRegistryEntry;

public class BiomeSettings implements ConfigurationHolder {
    @ConfigField
    private Biome.Category category = Category.PLAINS;
    @ConfigField
    private Biome.Type type = Type.PLAINS;
    private final BiomeRegistryEntry biomeRegistryEntry;

    public BiomeSettings(ConfigurationSectionHolder sectionHolder) {
        PluginConfig.CONFIGURATION_PROCESSOR.resolve(sectionHolder, this);
        Biome biome = Biome.builder().withCategory(category).build();
        biomeRegistryEntry = new BiomeRegistryEntry(type.getNbt(), 0, biome);
    }

    public BiomeSettings(Category category, Type type) {
        this.category = category;
        this.type = type;
        Biome biome = Biome.builder().withCategory(category).build();
        biomeRegistryEntry = new BiomeRegistryEntry(type.getNbt(), 0, biome);
    }

    public BiomeRegistryEntry getBiomeRegistryEntry() {
        return biomeRegistryEntry;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }
}
