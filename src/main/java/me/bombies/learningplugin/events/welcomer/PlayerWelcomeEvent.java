package me.bombies.learningplugin.events.welcomer;

import me.bombies.learningplugin.commands.misc.login.LoginService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerWelcomeEvent implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        LoginService.removeLoggedInUser(player);
        event.setQuitMessage(String.format("%s left the server", player.getName()));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        event.setJoinMessage(String.format("Welcome to the server, %s!", player.getName()));
    }

}
