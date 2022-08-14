package com.ubivashka.limbo.bungee.player;

import com.ubivashka.limbo.player.LimboPlayer;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public interface BungeeLimboPlayer extends LimboPlayer {
    ProxiedPlayer getPlayer();
}
