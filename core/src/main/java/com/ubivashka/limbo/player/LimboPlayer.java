package com.ubivashka.limbo.player;

import java.util.UUID;

import com.ubivashka.limbo.function.Castable;
import com.ubivashka.limbo.server.LimboServer;


public interface LimboPlayer extends Castable<LimboPlayer> {
    String getName();

    UUID getUniqueId();

    int getProtocolVersion();

    int getEntityId();

    boolean isOnline();

    void sendPacket(Object packet);

    LimboServer getCurrentLimbo();

    void setCurrentLimbo(LimboServer limbo);

    boolean isConnecting();

    void setConnecting(boolean connecting);
}
