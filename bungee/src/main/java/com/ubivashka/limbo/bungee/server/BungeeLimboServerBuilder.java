package com.ubivashka.limbo.bungee.server;

import com.ubivashka.limbo.bungee.config.limbo.LimboSettings;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.Dimension;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.Dimension.Type;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.DimensionRegistry.DimensionRegistryEntry;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.codec.DimensionCodec;
import com.ubivashka.limbo.protocol.packet.UpdatePlayerPositionPacket;
import com.ubivashka.limbo.server.LimboServer.LimboServerBuilder;

public class BungeeLimboServerBuilder implements LimboServerBuilder {
    private DimensionCodec dimensionCodec;
    private DimensionRegistryEntry dimensionRegistryEntry;
    private UpdatePlayerPositionPacket packet = new UpdatePlayerPositionPacket();
    private String name = "limbo";
    private String brand;
    private short gamemode = 0;
    private Dimension.Type dimensionType = Type.OVERWORLD;

    public BungeeLimboServerBuilder withSettings(LimboSettings settings) {
        withDimensionCodec(settings.getDimensionCodec());
        withDimensionRegistryEntry(settings.getDimensionSettings().getDimensionRegistryEntry());
        withName(settings.getName());
        withGamemode((short) settings.getGamemode());
        withDimensionType(settings.getDimensionSettings().getType());
        withPositionUpdatePacket(settings.getCoordinateSettings().getPlayerPositionPacket());
        if (settings.getBrand() != null)
            withBrand(settings.getBrand());
        return this;
    }

    @Override
    public LimboServerBuilder withDimensionCodec(DimensionCodec codec) {
        this.dimensionCodec = codec;
        return this;
    }

    @Override
    public LimboServerBuilder withDimensionType(Dimension.Type dimensionType) {
        this.dimensionType = dimensionType;
        return this;
    }

    @Override
    public LimboServerBuilder withDimensionRegistryEntry(DimensionRegistryEntry dimensionRegistryEntry) {
        this.dimensionRegistryEntry = dimensionRegistryEntry;
        return this;
    }

    @Override
    public LimboServerBuilder withPositionUpdatePacket(UpdatePlayerPositionPacket packet) {
        this.packet = packet;
        return this;
    }

    @Override
    public LimboServerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public LimboServerBuilder withGamemode(short gamemode) {
        this.gamemode = gamemode;
        return this;
    }

    @Override
    public LimboServerBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    @Override
    public BungeeLimboServer build() {
        return new BungeeLimboServer(dimensionCodec, dimensionRegistryEntry, name, gamemode, brand, dimensionType, packet);
    }
}
