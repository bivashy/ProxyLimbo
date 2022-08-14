package com.ubivashka.limbo.protocol.nbt.registry.biome;

import com.ubivashka.limbo.protocol.nbt.ValueRegistry;
import com.ubivashka.limbo.protocol.nbt.registry.biome.BiomeRegistry.BiomeRegistryEntry;

public class BiomeRegistry extends ValueRegistry<BiomeRegistryEntry> {
    public static final String TYPE = "minecraft:worldgen/biome";

    public BiomeRegistry(BiomeRegistryEntry... biomeRegistryEntries) {
        super(TYPE, biomeRegistryEntries);
    }

    public static class BiomeRegistryEntry extends RegistryEntry<Biome> {
        public BiomeRegistryEntry(String name, int id, Biome element) {
            super(name, id, element);
        }
    }
}
