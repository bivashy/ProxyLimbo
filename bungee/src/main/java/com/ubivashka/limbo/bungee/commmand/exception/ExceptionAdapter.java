package com.ubivashka.limbo.bungee.commmand.exception;

import com.ubivashka.limbo.bungee.config.message.MessagesConfiguration;

import revxrsal.commands.bungee.exception.BungeeExceptionAdapter;
import revxrsal.commands.bungee.exception.InvalidPlayerException;
import revxrsal.commands.bungee.exception.SenderNotConsoleException;
import revxrsal.commands.bungee.exception.SenderNotPlayerException;
import revxrsal.commands.command.CommandActor;
import revxrsal.commands.exception.ArgumentParseException;
import revxrsal.commands.exception.CommandInvocationException;
import revxrsal.commands.exception.CooldownException;
import revxrsal.commands.exception.EnumNotFoundException;
import revxrsal.commands.exception.InvalidBooleanException;
import revxrsal.commands.exception.InvalidCommandException;
import revxrsal.commands.exception.InvalidHelpPageException;
import revxrsal.commands.exception.InvalidNumberException;
import revxrsal.commands.exception.InvalidSubcommandException;
import revxrsal.commands.exception.InvalidURLException;
import revxrsal.commands.exception.InvalidUUIDException;
import revxrsal.commands.exception.MissingArgumentException;
import revxrsal.commands.exception.NoPermissionException;
import revxrsal.commands.exception.NoSubcommandSpecifiedException;
import revxrsal.commands.exception.NumberNotInRangeException;
import revxrsal.commands.exception.SendableException;
import revxrsal.commands.exception.TooManyArgumentsException;

public class ExceptionAdapter extends BungeeExceptionAdapter {
    public static final String USAGE = "usage";
    public static final String NO_PERMISSION = "no-permission";
    public static final String PLAYER_OFFLINE = "player-offline";
    public static final String UNHANDLED_EXCEPTION = "unhandled-exception";
    private final MessagesConfiguration messages;

    public ExceptionAdapter(MessagesConfiguration messages) {
        this.messages = messages;
    }

    @Override
    public void senderNotPlayer(CommandActor actor, SenderNotPlayerException exception) {
    }

    @Override
    public void senderNotConsole(CommandActor actor,
            SenderNotConsoleException exception) {
    }

    @Override
    public void invalidPlayer(CommandActor actor, InvalidPlayerException exception) {
        actor.reply(PLAYER_OFFLINE);
    }

    @Override
    public void missingArgument(CommandActor actor, MissingArgumentException exception) {
        actor.reply(messages.getMessage(USAGE));
    }

    @Override
    public void invalidEnumValue(CommandActor actor, EnumNotFoundException exception) {
    }

    @Override
    public void invalidNumber(CommandActor actor, InvalidNumberException exception) {
    }

    @Override
    public void invalidUUID(CommandActor actor, InvalidUUIDException exception) {
    }

    @Override
    public void invalidURL(CommandActor actor, InvalidURLException exception) {
    }

    @Override
    public void invalidBoolean(CommandActor actor, InvalidBooleanException exception) {
    }

    @Override
    public void noPermission(CommandActor actor, NoPermissionException exception) {
        actor.reply(messages.getMessage(NO_PERMISSION));
    }

    @Override
    public void argumentParse(CommandActor actor, ArgumentParseException exception) {
    }

    @Override
    public void commandInvocation(CommandActor actor,
            CommandInvocationException exception) {
        onUnhandledException(actor, exception.getCause());
    }

    @Override
    public void tooManyArguments(CommandActor actor,
            TooManyArgumentsException exception) {
    }

    @Override
    public void invalidCommand(CommandActor actor, InvalidCommandException exception) {
    }

    @Override
    public void invalidSubcommand(CommandActor actor,
            InvalidSubcommandException exception) {
        actor.reply(messages.getMessage(USAGE));
    }

    @Override
    public void noSubcommandSpecified(CommandActor actor,
            NoSubcommandSpecifiedException exception) {
        actor.reply(messages.getMessage(USAGE));
    }

    @Override
    public void cooldown(CommandActor actor, CooldownException exception) {
    }

    @Override
    public void invalidHelpPage(CommandActor actor, InvalidHelpPageException exception) {
    }

    @Override
    public void sendableException(CommandActor actor, SendableException exception) {
        exception.sendTo(actor);
    }

    @Override
    public void numberNotInRange(CommandActor actor,
            NumberNotInRangeException exception) {
        super.numberNotInRange(actor, exception);
    }

    @Override
    public void onUnhandledException(CommandActor actor, Throwable throwable) {
        actor.reply(messages.getMessage(UNHANDLED_EXCEPTION));
        throwable.printStackTrace();
    }
}
