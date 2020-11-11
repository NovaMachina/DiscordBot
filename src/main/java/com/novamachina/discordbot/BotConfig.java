package com.novamachina.discordbot;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class BotConfig {
    private String token;
    private String[] authorizedUsers;
    private static BotConfig INSTANCE;
    private static Path configFile = Path.of("ExNihiloDiscordBotConfig.json");

    public static boolean loadConfig() {
        if(!Files.exists(configFile)) {
            return false;
        }
        try {
            List<String> fileContents = Files.readAllLines(configFile);
            StringBuilder builder = new StringBuilder();
            for(String s : fileContents) {
                builder.append(s);
            }
            INSTANCE = new GsonBuilder().disableHtmlEscaping().create().fromJson(builder.toString(), BotConfig.class);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static BotConfig getInstance() {
        if(INSTANCE == null) {
            System.out.println("Configs not loaded.");
        }
        return INSTANCE;
    }

    public static void generateConfig() {
        JsonObject configObj = new JsonObject();
        configObj.addProperty("token", "<INSERT DISCORD TOKEN HERE>");
        configObj.add("authorizedUsers", new JsonArray());

        String jsonString = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(configObj);

        try {
            Files.write(configFile, jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getToken() {
        return token;
    }

    public List<String> getAuthorizedUsers() {
        return Arrays.asList(authorizedUsers);
    }
}
