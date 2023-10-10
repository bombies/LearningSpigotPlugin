package me.bombies.learningplugin.commands.misc.nick;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class NickService {
    private static final HashMap<UUID, String> userNicknames = new HashMap<>();

    public static void setNickname(UUID uuid, String nickname) {
        userNicknames.put(uuid, nickname);
    }

    public static String getNickname(UUID uuid) {
        // Should implement a cache-first database-after strategy here
        return userNicknames.get(uuid);
    }

    public static void removeNickname(UUID uuid) {
        userNicknames.remove(uuid);
    }

}
