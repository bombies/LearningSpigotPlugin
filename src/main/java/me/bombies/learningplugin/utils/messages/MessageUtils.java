package me.bombies.learningplugin.utils.messages;

import me.bombies.learningplugin.LearningPlugin;
import org.bukkit.ChatColor;

import java.util.regex.Pattern;

public class MessageUtils {
    private final static Pattern COLOUR_CODE_REGEX = Pattern.compile("(?i)&([0-9a-fA-Fk-oK-OrRxX])|&x([0-9A-Fa-f]{6})|&x([0-9A-Fa-f]{3})|&#([0-9A-Fa-f]{6})");

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String stripColor(String message) {
        return message.replaceAll(COLOUR_CODE_REGEX.pattern(), "");
    }
}
