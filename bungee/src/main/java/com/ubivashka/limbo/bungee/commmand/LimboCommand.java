package com.ubivashka.limbo.bungee.commmand;

import com.ubivashka.limbo.bungee.BungeeProxyLimbo;
import com.ubivashka.limbo.bungee.server.BungeeLimboServer;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Dependency;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bungee.annotation.CommandPermission;
import revxrsal.commands.command.CommandActor;
import revxrsal.commands.exception.SendMessageException;

@Command("limbo")
public class LimboCommand {
    public static final String SUCCESSFULLY_CONNECTED = "successfully-connected";
    @Dependency
    private BungeeProxyLimbo plugin;

    @CommandPermission("limbo.send.another")
    @Subcommand("send")
    public void send(ProxiedPlayer player, BungeeLimboServer limboServer) {
        limboServer.connect(plugin.fetchLimboPlayer(player));
        throw new SendMessageException(plugin.getConfig().getMessages().getMessage(SUCCESSFULLY_CONNECTED));
    }

    @CommandPermission("limbo.send")
    @Subcommand("send")
    public void send(CommandActor actor, ProxiedPlayer player, BungeeLimboServer limboServer) {
        limboServer.connect(plugin.fetchLimboPlayer(player));
        throw new SendMessageException(plugin.getConfig().getMessages().getMessage(SUCCESSFULLY_CONNECTED));
    }
}
