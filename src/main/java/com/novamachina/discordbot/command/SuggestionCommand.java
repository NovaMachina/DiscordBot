package com.novamachina.discordbot.command;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class SuggestionCommand implements ICommand {
    @Override
    public void execute(MessageCreateEvent event) {
        event.getMessage().getChannel().block()
            .createMessage(spec -> {
                spec.setEmbed(embed -> {
                    embed.setTitle("Make Suggestion");
                    embed.setDescription("If you would like to make a suggestion, do so on GitHub so I can track it there.");
                    embed.setUrl("https://github.com/NovaMachina/ExNihiloSequentia/issues");
                });
            })
            .block();
    }

    @Override
    public String description() {
        return "Displays the link to make a suggestion";
    }
}
