package com.ubivashka.limbo.bungee.listener;

import com.ubivashka.limbo.bungee.BungeeProxyLimbo;
import com.ubivashka.limbo.player.LimboPlayer;

import net.md_5.bungee.ServerConnection;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LimboInteractionListener implements Listener {
    private final BungeeProxyLimbo plugin;

    public LimboInteractionListener(BungeeProxyLimbo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerConnect(ServerConnectEvent e) {
        LimboPlayer limboPlayer = plugin.fetchLimboPlayer(e.getPlayer());
        if (!limboPlayer.isConnecting())
            return;
        limboPlayer.setConnecting(false);
        e.setCancelled(true);
    }

    @EventHandler
    public void onServerConnected(ServerConnectedEvent e) {
        LimboPlayer limboPlayer = plugin.fetchLimboPlayer(e.getPlayer());
        if (!limboPlayer.isConnecting() || limboPlayer.getCurrentLimbo() == null)
            return;
        if (e.getServer() instanceof ServerConnection) {
            ServerConnection connection = (ServerConnection) e.getServer();
            connection.setObsolete(true);
            connection.getCh().close();
        }
        limboPlayer.getCurrentLimbo().connect(limboPlayer);
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent e) {
        plugin.removeLimboPlayer(e.getPlayer().getUniqueId());
    }
}
