package com.ubivashka.limbo.protocol.nbt.registry.dimension.codec;

import com.ubivashka.limbo.nbt.CompoundTaggable;
import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.TagDataType;
import com.ubivashka.limbo.protocol.nbt.registry.biome.BiomeRegistry;
import com.ubivashka.limbo.protocol.nbt.registry.chat.ChatRegistry;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.DimensionRegistry;

import dev.simplix.protocolize.api.util.ProtocolVersions;

public class DimensionCodec implements CompoundTaggable {
    @TagData(key = "minecraft:dimension_type", type = TagDataType.COMPOUND)
    private DimensionRegistry dimensionRegistry;
    @TagData(key = "minecraft:worldgen/biome", type = TagDataType.COMPOUND)
    private BiomeRegistry biomeRegistry;
    @TagData(key = "minecraft:chat_type", type = TagDataType.COMPOUND, minVersion = ProtocolVersions.MINECRAFT_1_19)
    private ChatRegistry chatRegistry;

    public DimensionCodec(DimensionRegistry dimensionRegistry, BiomeRegistry biomeRegistry, ChatRegistry chatRegistry) {
        this.dimensionRegistry = dimensionRegistry;
        this.biomeRegistry = biomeRegistry;
        this.chatRegistry = chatRegistry;
    }

    public DimensionRegistry getDimensionRegistry() {
        return dimensionRegistry;
    }

    public BiomeRegistry getBiomeRegistry() {
        return biomeRegistry;
    }

    public ChatRegistry getChatRegistry() { // 1.19
        return chatRegistry;
    }
}
