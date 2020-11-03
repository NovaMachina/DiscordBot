package com.novamachina.discordbot.command;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class IssueCommand implements ICommand {
    @Override
    public void execute(MessageCreateEvent event) {
        event.getMessage().getChannel().block()
            .createMessage(spec -> {
                spec.setEmbed(embed -> {
                    embed.setTitle("Report Issue");
                    embed.setDescription("To report and issue or a bug, do so on GitHub so I can track it there.");
                    embed.setUrl("https://github.com/NovaMachina/ExNihiloSequentia/issues");
                });
            })
            .block();
    }

    @Override
    public String description() {
        return "Displays the link to report an issue";
    }
}
