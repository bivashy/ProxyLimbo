package com.ubivashka.limbo.bungee.packet;

import net.md_5.bungee.protocol.packet.Respawn;

public class RespawnPacketBuilder extends Respawn {
    private Object dimension;
    private String dimensionName;
    private short gamemode;
    private short previousGamemode = -1;
    private String levelType = "default";

    public RespawnPacketBuilder withDimension(Object dimension) {
        this.dimension = dimension;
        return this;
    }

    public RespawnPacketBuilder withDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
        return this;
    }

    public RespawnPacketBuilder withGamemode(short gamemode) {
        this.gamemode = gamemode;
        return this;
    }

    public RespawnPacketBuilder withPreviousGamemode(short previousGamemode) {
        this.previousGamemode = previousGamemode;
        return this;
    }

    public RespawnPacketBuilder withLevelType(String levelType) {
        this.levelType = levelType;
        return this;
    }

    public Respawn build() {
        Respawn respawn = new Respawn();
        respawn.setDimension(dimension);
        respawn.setWorldName(dimensionName);
        respawn.setGameMode(gamemode);
        respawn.setPreviousGameMode(previousGamemode);
        respawn.setLevelType(levelType);
        return respawn;
    }
}
