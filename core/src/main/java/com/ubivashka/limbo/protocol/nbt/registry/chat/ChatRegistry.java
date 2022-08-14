package com.ubivashka.limbo.protocol.nbt.registry.chat;

import com.ubivashka.limbo.protocol.nbt.ValueRegistry;
import com.ubivashka.limbo.protocol.nbt.ValueRegistry.RegistryEntry;
import com.ubivashka.limbo.protocol.nbt.registry.chat.ChatRegistry.ChatRegistryEntry;

public class ChatRegistry extends ValueRegistry<ChatRegistryEntry> {
    public static final String TYPE = "minecraft:chat_type";

    public ChatRegistry(ChatRegistryEntry... registryEntries) {
        super(TYPE, registryEntries);
    }

    public static class ChatRegistryEntry extends RegistryEntry<ChatEntry> {
        public ChatRegistryEntry(String name, int id, ChatEntry element) {
            super(name, id, element);
        }
    }
}
