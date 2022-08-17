package com.ubivashka.limbo.bungee.config.message;

import java.util.HashMap;

import com.ubivashka.configuration.ConfigurationHolder;
import com.ubivashka.configuration.holder.ConfigurationSectionHolder;

import net.md_5.bungee.api.ChatColor;

public class MessagesConfiguration implements ConfigurationHolder {
    private HashMap<String, String> messages = new HashMap<>();
    private HashMap<String, MessagesConfiguration> subMessages = new HashMap<>();

    public MessagesConfiguration(ConfigurationSectionHolder sectionHolder) {
        for (String key : sectionHolder.keys()) {
            if (sectionHolder.isSection(key)) {
                addSubMessages(key, sectionHolder);
                continue;
            }
            addMessage(key, sectionHolder.getString(key));
        }
    }

    public String getMessage(String key) {
        return messages.getOrDefault(key, String.format("Message with key %s not found", key));
    }

    public MessagesConfiguration getSubMessages(String key) {
        return subMessages.get(key);
    }

    private void addSubMessages(String key, ConfigurationSectionHolder sectionHolder) {
        subMessages.put(key, new MessagesConfiguration(sectionHolder.section(key)));
    }

    private void addMessage(String key, String message) {
        messages.put(key, color(message));
    }

    private String color(String text) {
        if (text == null)
            throw new IllegalArgumentException("Cannot color null text: " + text);
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}

