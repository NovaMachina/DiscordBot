package com.novamachina.discordbot.command;

import com.novamachina.discordbot.Main;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.Map;

public class HelpCommand implements ICommand {
    @Override
    public void execute(MessageCreateEvent event) {

        event.getMessage().getChannel().block().createMessage(spec -> {
            spec.setEmbed(embed -> {
                for(Map.Entry<String, ICommand> command : Main.commands.entrySet()) {
                    embed.addField(command.getKey(), command.getValue().description(), false);
                }
            });
        }).block();
    }

    @Override
    public String description() {
        return "Displays this help message.";
    }
}
