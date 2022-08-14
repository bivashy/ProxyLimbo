package com.ubivashka.limbo.bungee.packet;

import java.util.Set;

import net.md_5.bungee.protocol.packet.Login;
import se.llbit.nbt.NamedTag;

public class LoginPacketBuilder {
    private int entityId;
    private short gamemode;
    private short previousGamemode = -1;
    private String levelType;
    private Set<String> dimensionNames;
    private String dimensionName;
    private NamedTag dimensionCodec;
    private Object dimension;

    public LoginPacketBuilder withEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public LoginPacketBuilder withGamemode(short gamemode) {
        this.gamemode = gamemode;
        return this;
    }

    public LoginPacketBuilder withPreviousGamemode(short previousGamemode) {
        this.previousGamemode = previousGamemode;
        return this;
    }

    public LoginPacketBuilder withLevelType(String levelType) {
        this.levelType = levelType;
        return this;
    }

    public LoginPacketBuilder withDimensionNames(Set<String> dimensionNames) {
        this.dimensionNames = dimensionNames;
        return this;
    }

    public LoginPacketBuilder withDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
        return this;
    }

    public LoginPacketBuilder withDimensionCodec(NamedTag dimensionCodec) {
        this.dimensionCodec = dimensionCodec;
        return this;
    }

    public LoginPacketBuilder withDimension(Object dimension) {
        this.dimension = dimension;
        return this;
    }

    public Login build() {
        Login login = new Login();
        login.setEntityId(entityId);
        login.setGameMode(gamemode);
        login.setPreviousGameMode(previousGamemode);
        login.setLevelType(levelType);
        login.setWorldNames(dimensionNames);
        login.setWorldName(dimensionName);
        login.setDimensions(dimensionCodec);
        login.setDimension(dimension);
        return login;
    }

}
