package com.novamachina.discordbot.command;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class TestCommand implements ICommand {
    @Override
    public void execute(MessageCreateEvent event) {
        event.getMessage().getChannel().block().createMessage(spec -> {
            spec.setContent("This is a test");
            spec.setEmbed(embed -> {
                embed.setTitle("Embed Title");
                embed.setDescription("Description");
                embed.setUrl("https://github.com/NovaMachina/ExNihiloSequentia/issues");
                embed.setAuthor("NovaMachina", null, null);
                embed.addField("command", "description", true);
                embed.addField("command2", "description2", false);
            });
        }).block();
    }

    @Override
    public String description() {
        return "Testing new bot features!";
    }
}
