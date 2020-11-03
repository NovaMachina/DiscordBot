package com.novamachina.discordbot.command;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class EmptyCommand implements ICommand {
    @Override
    public void execute(MessageCreateEvent event) {
        event.getMessage().getChannel().block()
            .createMessage("You have entered an invalid command. Type `!help` to get a list of commands.").block();
    }

    @Override
    public String description() {
        return null;
    }
}
