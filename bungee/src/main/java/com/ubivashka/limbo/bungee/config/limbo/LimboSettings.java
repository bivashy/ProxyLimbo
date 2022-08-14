package com.ubivashka.limbo.bungee.config.limbo;

import com.ubivashka.configuration.ConfigurationHolder;
import com.ubivashka.configuration.annotation.ConfigField;
import com.ubivashka.configuration.annotation.ImportantField;
import com.ubivashka.configuration.holder.ConfigurationSectionHolder;
import com.ubivashka.limbo.ProxyLimbo;
import com.ubivashka.limbo.bungee.config.PluginConfig;
import com.ubivashka.limbo.bungee.config.limbo.biome.BiomeSettings;
import com.ubivashka.limbo.bungee.config.limbo.dimension.DimensionSettings;
import com.ubivashka.limbo.bungee.server.BungeeLimboServer;
import com.ubivashka.limbo.protocol.nbt.registry.biome.Biome.Category;
import com.ubivashka.limbo.protocol.nbt.registry.biome.Biome.Type;
import com.ubivashka.limbo.protocol.nbt.registry.biome.BiomeRegistry;
import com.ubivashka.limbo.protocol.nbt.registry.chat.ChatGameInfo;
import com.ubivashka.limbo.protocol.nbt.registry.chat.ChatRegistry;
import com.ubivashka.limbo.protocol.nbt.registry.chat.ChatRegistry.ChatRegistryEntry;
import com.ubivashka.limbo.protocol.nbt.registry.chat.ChatSystem;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.DimensionRegistry;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.codec.DimensionCodec;

public class LimboSettings implements ConfigurationHolder {
    @ConfigField("gamemode")
    private int gamemode = 2;
    @ConfigField("brand")
    private String brand = "limbo";
    @ConfigField("coordinates")
    private CoordinateSettings coordinateSettings = new CoordinateSettings(0d, 0d, 0d, 0f, 0f);
    @ConfigField("biome")
    private BiomeSettings biomeSettings = new BiomeSettings(Category.PLAINS, Type.PLAINS);
    @ImportantField
    @ConfigField("dimension")
    private DimensionSettings dimensionSettings;

    private final DimensionCodec dimensionCodec;
    private final BungeeLimboServer limboServer;
    private final String name;

    public LimboSettings(ConfigurationSectionHolder sectionHolder) {
        PluginConfig.CONFIGURATION_PROCESSOR.resolve(sectionHolder, this);

        DimensionRegistry dimensionRegistry = new DimensionRegistry(dimensionSettings.getDimensionRegistryEntry());

        BiomeRegistry biomeRegistry = new BiomeRegistry(biomeSettings.getBiomeRegistryEntry());

        ChatRegistry chatRegistry = new ChatRegistry(new ChatRegistryEntry(ChatSystem.NAME, 0, new ChatSystem()),
                new ChatRegistryEntry(ChatGameInfo.NAME, 1, new ChatGameInfo()));

        this.name = sectionHolder.key();
        dimensionCodec = new DimensionCodec(dimensionRegistry, biomeRegistry, chatRegistry);
        limboServer = new BungeeLimboServer(this);
        ProxyLimbo.instance().getLimboServerContainer().add(limboServer);
    }

    public String getBrand() {
        return brand;
    }

    public CoordinateSettings getCoordinateSettings() {
        return coordinateSettings;
    }

    public BiomeSettings getBiomeSettings() {
        return biomeSettings;
    }

    public DimensionSettings getDimensionSettings() {
        return dimensionSettings;
    }

    public DimensionCodec getDimensionCodec() {
        return dimensionCodec;
    }

    public BungeeLimboServer getLimboServer() {
        return limboServer;
    }

    public int getGamemode() {
        return gamemode;
    }

    public String getName() {
        return name;
    }
}
