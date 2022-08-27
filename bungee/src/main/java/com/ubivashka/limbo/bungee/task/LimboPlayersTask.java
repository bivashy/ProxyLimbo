package com.ubivashka.limbo.bungee.task;

import java.util.concurrent.ThreadLocalRandom;

import com.ubivashka.limbo.bungee.BungeeProxyLimbo;
import com.ubivashka.limbo.player.LimboPlayer;

import net.md_5.bungee.protocol.packet.KeepAlive;

public class LimboPlayersTask implements Runnable {
    private final BungeeProxyLimbo plugin;

    public LimboPlayersTask(BungeeProxyLimbo plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (LimboPlayer player : plugin.getLimboPlayers().values()) {
            if (!player.isOnline())
                continue;
            if (player.getCurrentLimbo() == null)
                continue;
            if (player.isConnecting())
                player.setConnecting(false);
            player.sendPacket(new KeepAlive(ThreadLocalRandom.current().nextInt()));
        }
    }
}
