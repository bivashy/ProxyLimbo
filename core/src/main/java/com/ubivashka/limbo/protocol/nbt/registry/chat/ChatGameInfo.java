package com.ubivashka.limbo.protocol.nbt.registry.chat;

import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.CompoundTag;
import com.ubivashka.limbo.nbt.type.TagDataType;

import dev.simplix.protocolize.api.util.ProtocolVersions;

public class ChatGameInfo implements ChatEntry {
    public static final String NAME = "minecraft:game_info";
    @TagData(type = TagDataType.COMPOUND, minVersion = ProtocolVersions.MINECRAFT_1_19)
    private CompoundTag overlay;

    public ChatGameInfo(CompoundTag overlay) {
        this.overlay = overlay;
    }

    public ChatGameInfo() {
        this(new CompoundTag());
    }

    public CompoundTag getOverlay() {
        return overlay;
    }
}
