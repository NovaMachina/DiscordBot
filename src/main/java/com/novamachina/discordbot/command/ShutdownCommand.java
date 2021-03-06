package com.novamachina.discordbot.command;

import com.novamachina.discordbot.BotConfig;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;

public class ShutdownCommand implements ICommand {
    @Override
    public void execute(MessageCreateEvent event) {
        User user = event.getMessage().getAuthor().get();
        String userString = user.getUsername() + "#" + user.getDiscriminator();
        if(BotConfig.getInstance().getAuthorizedUsers().contains(userString)) {
            event.getMessage().getChannel().block().createMessage("Shutting down...").block();
            System.exit(0);
        } else {
            // TODO: Add logging
            event.getMessage().getChannel().block().createMessage("You do not have permissions to shutdown the bot. This will be logged.").block();
        }
    }

    @Override
    public String description() {
        return "Shuts down the bot. Must have permissions to do so.";
    }
}
