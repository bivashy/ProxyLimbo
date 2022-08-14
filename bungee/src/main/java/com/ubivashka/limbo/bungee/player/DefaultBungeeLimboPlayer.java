package com.ubivashka.limbo.bungee.player;

import java.util.UUID;

import com.ubivashka.limbo.server.LimboServer;

import dev.simplix.protocolize.api.Protocolize;
import dev.simplix.protocolize.api.player.ProtocolizePlayer;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class DefaultBungeeLimboPlayer implements BungeeLimboPlayer {
    private final ProxiedPlayer player;
    private final ProtocolizePlayer protocolizePlayer;
    private LimboServer limboServer;
    private boolean connecting = false;

    public DefaultBungeeLimboPlayer(ProxiedPlayer player) {
        this.player = player;
        this.protocolizePlayer = Protocolize.playerProvider().player(player.getUniqueId());
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public int getProtocolVersion() {
        return ((UserConnection) player).getPendingConnection().getVersion();
    }

    @Override
    public int getEntityId() {
        return ((UserConnection) player).getClientEntityId();
    }

    @Override
    public void sendPacket(Object packet) {
        protocolizePlayer.sendPacket(packet);
    }

    @Override
    public boolean isOnline() {
        return ProxyServer.getInstance().getPlayer(getUniqueId()) != null;
    }

    @Override
    public LimboServer getCurrentLimbo() {
        return limboServer;
    }

    @Override
    public void setCurrentLimbo(LimboServer limboServer) {
        if (this.limboServer != null)
            this.limboServer.disconnect(this);
        this.limboServer = limboServer;
    }

    @Override
    public boolean isConnecting() {
        return connecting;
    }

    @Override
    public void setConnecting(boolean connecting) {
        this.connecting = connecting;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }
}
