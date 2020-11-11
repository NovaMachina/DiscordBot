package com.novamachina.discordbot;

import com.novamachina.discordbot.command.EmptyCommand;
import com.novamachina.discordbot.command.HelpCommand;
import com.novamachina.discordbot.command.ICommand;
import com.novamachina.discordbot.command.IssueCommand;
import com.novamachina.discordbot.command.ShutdownCommand;
import com.novamachina.discordbot.command.SuggestionCommand;
import com.novamachina.discordbot.command.TestCommand;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final Map<String, ICommand> commands = new HashMap<>();

    static {
        commands.put("!shutdown", new ShutdownCommand());
        commands.put("!suggestion", new SuggestionCommand());
        commands.put("!issue", new IssueCommand());
        commands.put("!test", new TestCommand());
        commands.put("!help", new HelpCommand());
    }

    public static void main(String[] args) {
        if(!BotConfig.loadConfig()) {
            BotConfig.generateConfig();
            System.out.println("Missing configuration file. Generating...");
            System.exit(0);
        }

        GatewayDiscordClient client = DiscordClientBuilder
            .create(BotConfig.getInstance().getToken()).build().login().block();

        client.getEventDispatcher().on(ReadyEvent.class).subscribe(event -> {
            User self = event.getSelf();
            System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
        });

        client.getEventDispatcher().on(MessageCreateEvent.class)
            .subscribe(event -> {
                final String content = event.getMessage().getContent();
                if(content.startsWith("!")) {
                    commands.getOrDefault(content, new EmptyCommand()).execute(event);
                }
            });

        client.onDisconnect().block();
    }
}
