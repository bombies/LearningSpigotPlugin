package me.bombies.learningplugin.utils.messages;

public class DefaultMessage {
    public final static String INSUFFICIENT_PERMS = MessageUtils.color("&cYou don't have permission to do that!");

    public static String PLAYER_NOT_FOUND(String name) {
        return MessageUtils.color(String.format("&c%s was not found!", name));
    }

    public static String USAGE(String usage) {
        return MessageUtils.color(String.format("&cUsage: /%s", usage));
    }

    public final static String PLAYER_NOT_FOUND = MessageUtils.color("&cPlayer not found!");
}
