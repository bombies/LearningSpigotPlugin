package me.bombies.learningplugin.utils.messages;

import org.bukkit.ChatColor;

public class MessageUtils {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
