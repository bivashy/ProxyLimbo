package com.ubivashka.limbo.nbt.resolver;

import java.lang.reflect.Field;

import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.CompoundTag;

public class CompoundTagDataResolverContext {
    private final CompoundTagDataResolver<?> resolver;
    private final CompoundTag compoundTag;
    private final TagData tagData;
    private final Field field;
    private final String key;
    private final int protocolId;

    public CompoundTagDataResolverContext(CompoundTagDataResolver<?> resolver, CompoundTag compoundTag, TagData tagData, Field field, String key, int protocolId) {
        this.resolver = resolver;
        this.compoundTag = compoundTag;
        this.tagData = tagData;
        this.field = field;
        this.key = key;
        this.protocolId = protocolId;
    }

    public CompoundTagDataResolver<?> getResolver() {
        return resolver;
    }

    public CompoundTag getCompoundTag() {
        return compoundTag;
    }

    public TagData getTagData() {
        return tagData;
    }

    public Field getField() {
        return field;
    }

    public String getKey() {
        return key;
    }

    public int getProtocolId() {
        return protocolId;
    }
}
