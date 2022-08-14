package com.ubivashka.limbo.protocol.nbt.registry.chat;

import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.CompoundTag;
import com.ubivashka.limbo.nbt.type.TagDataType;

import dev.simplix.protocolize.api.util.ProtocolVersions;

public class ChatSystem implements ChatEntry {
    public static final String NAME = "minecraft:system";
    @TagData(type = TagDataType.COMPOUND, minVersion = ProtocolVersions.MINECRAFT_1_19)
    private CompoundTag chat;
    @TagData(type = TagDataType.COMPOUND, minVersion = ProtocolVersions.MINECRAFT_1_19)
    private ChatNarration narration;

    public ChatSystem(CompoundTag chat, ChatNarration narration) {
        this.chat = chat;
        this.narration = narration;
    }

    public ChatSystem() {
        this(new CompoundTag(), new ChatNarration());
    }

    public CompoundTag getChat() {
        return chat;
    }

    public ChatNarration getNarration() {
        return narration;
    }

    public static class ChatNarration {
        @TagData(type = TagDataType.COMPOUND, minVersion = ProtocolVersions.MINECRAFT_1_19)
        private String priority;

        public ChatNarration(String priority) {
            this.priority = priority;
        }

        public ChatNarration() {
            this("system");
        }

        public String getPriority() {
            return priority;
        }
    }
}
