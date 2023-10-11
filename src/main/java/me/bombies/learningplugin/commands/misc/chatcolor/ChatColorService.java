package me.bombies.learningplugin.commands.misc.chatcolor;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ChatColorService {

    private final static HashMap<UUID, ChatColor> playerColors = new HashMap<>();

    public static ChatColor getPlayerColor(UUID uuid) {
        return playerColors.get(uuid);
    }

    public static ChatColor getPlayerColor(Player player) {
        final var color = getPlayerColor(player.getUniqueId());
        return color != null ? color : ChatColor.WHITE;
    }

    public static void setPlayerColor(UUID uuid, ChatColor color) {
        playerColors.put(uuid, color);
    }

    public static void setPlayerColor(Player player, ChatColor color) {
        setPlayerColor(player.getUniqueId(), color);
    }

    public static void removePlayerColor(UUID uuid) {
        playerColors.remove(uuid);
    }

    public static void removePlayerColor(Player player) {
        removePlayerColor(player.getUniqueId());
    }

}
