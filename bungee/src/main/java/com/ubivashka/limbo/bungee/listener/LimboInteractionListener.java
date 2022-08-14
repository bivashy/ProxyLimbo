package com.ubivashka.limbo.bungee.listener;

import com.ubivashka.limbo.bungee.BungeeProxyLimbo;
import com.ubivashka.limbo.player.LimboPlayer;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
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
    public void onDisconnect(PlayerDisconnectEvent e) {
        plugin.removeLimboPlayer(e.getPlayer().getUniqueId());
    }
}
