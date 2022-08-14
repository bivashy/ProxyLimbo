package com.ubivashka.limbo.protocol.nbt.registry.dimension;

import com.ubivashka.limbo.protocol.nbt.ValueRegistry;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.DimensionRegistry.DimensionRegistryEntry;


public class DimensionRegistry extends ValueRegistry<DimensionRegistryEntry> {
    public static final String TYPE = "minecraft:dimension_type";

    public DimensionRegistry(DimensionRegistryEntry... dimensionRegistryEntries) {
        super(TYPE, dimensionRegistryEntries);
    }

    public static class DimensionRegistryEntry extends RegistryEntry<Dimension> {
        public DimensionRegistryEntry(String name, int id, Dimension element) {
            super(name, id, element);
        }
    }
}