package com.ubivashka.limbo.protocol.nbt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ubivashka.limbo.nbt.CompoundTaggable;
import com.ubivashka.limbo.nbt.resolver.annotation.ListTagAnnotation;
import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.TagDataType;
import com.ubivashka.limbo.protocol.nbt.ValueRegistry.RegistryEntry;

public abstract class ValueRegistry<T extends RegistryEntry<?>> implements CompoundTaggable {
    @TagData(type = TagDataType.STRING)
    private final String type;
    @ListTagAnnotation(value = TagDataType.COMPOUND,shouldResolve = true)
    @TagData(key = "value", type = TagDataType.LIST)
    private final List<T> entries = new ArrayList<>();

    public ValueRegistry(String type, T... registryEntries) {
        this.type = type;
        Collections.addAll(this.entries, registryEntries);
    }

    public List<T> getEntries() {
        return entries;
    }

    public static class RegistryEntry<E> implements CompoundTaggable {
        @TagData(type = TagDataType.STRING)
        private String name;
        @TagData(type = TagDataType.INT)
        private int id;
        @TagData(type = TagDataType.COMPOUND)
        private E element;

        public RegistryEntry(String name, int id, E element) {
            this.name = name;
            this.id = id;
            this.element = element;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public E getElement() {
            return element;
        }
    }
}
