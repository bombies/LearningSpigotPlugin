package me.bombies.learningplugin.commands.misc.login;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class LoginService {
    private static final ArrayList<UUID> loggedInPlayers = new ArrayList<>();

    public static boolean playerIsLoggedIn(Player player) {
        return loggedInPlayers.contains(player.getUniqueId());
    }

    public static void removeLoggedInUser(Player player) {
        loggedInPlayers.remove(player.getUniqueId());
    }

    public static void login(Player player) {
        if (!playerIsLoggedIn(player))
            loggedInPlayers.add(player.getUniqueId());
    }

}
