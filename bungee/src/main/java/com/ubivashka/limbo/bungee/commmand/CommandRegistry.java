package com.ubivashka.limbo.bungee.commmand;

import com.ubivashka.limbo.bungee.BungeeProxyLimbo;
import com.ubivashka.limbo.bungee.commmand.exception.ExceptionAdapter;
import com.ubivashka.limbo.server.LimboServer;

import revxrsal.commands.CommandHandler;
import revxrsal.commands.bungee.core.BungeeHandler;
import revxrsal.commands.exception.SendMessageException;

public class CommandRegistry {
    public static final String LIMBO_NOT_FOUND = "limbo-not-found";
    private final BungeeProxyLimbo plugin;
    private final CommandHandler commandHandler;

    public CommandRegistry(BungeeProxyLimbo plugin) {
        this.plugin = plugin;
        this.commandHandler = new BungeeHandler(plugin).disableStackTraceSanitizing();
        register();
    }

    private void register() {
        commandHandler.setExceptionHandler(new ExceptionAdapter(plugin.getConfig().getMessages()));
        commandHandler.registerValueResolver(LimboServer.class, context -> plugin.getLimboServerContainer()
                .findFirst(server -> server.getName().equals(context.popForParameter()))
                .orElseThrow(() -> new SendMessageException(plugin.getConfig().getMessages().getMessage(LIMBO_NOT_FOUND))));
        commandHandler.register(new LimboCommand());
    }
}
