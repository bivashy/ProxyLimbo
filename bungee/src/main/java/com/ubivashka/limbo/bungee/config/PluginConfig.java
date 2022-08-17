package com.ubivashka.limbo.bungee.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import com.ubivashka.configuration.BungeeConfigurationProcessor;
import com.ubivashka.configuration.ConfigurationProcessor;
import com.ubivashka.configuration.annotation.ConfigField;
import com.ubivashka.configuration.annotation.ImportantField;
import com.ubivashka.limbo.bungee.config.limbo.LimboSettings;
import com.ubivashka.limbo.bungee.config.message.MessagesConfiguration;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class PluginConfig {
    public static final ConfigurationProcessor CONFIGURATION_PROCESSOR = new BungeeConfigurationProcessor();
    private final Configuration configuration;
    @ImportantField
    @ConfigField("limbo")
    private List<LimboSettings> limboSettings;
    @ImportantField
    @ConfigField("messages")
    private MessagesConfiguration messages;

    public PluginConfig(Plugin plugin) {
        this.configuration = saveDefaultConfiguration(plugin);
        CONFIGURATION_PROCESSOR.resolve(configuration, this);
    }

    public List<LimboSettings> getLimboSettings() {
        return limboSettings;
    }

    public MessagesConfiguration getMessages() {
        return messages;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    private Configuration saveDefaultConfiguration(Plugin plugin) {
        try {
            if (!plugin.getDataFolder().exists())
                plugin.getDataFolder().mkdir();
            File configurationFile = new File(plugin.getDataFolder(), "config.yml");
            if (!configurationFile.exists()) {
                try (InputStream inputStream = plugin.getResourceAsStream("config.yml")) {
                    Files.copy(inputStream, configurationFile.toPath());
                }
            }

            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(configurationFile);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
