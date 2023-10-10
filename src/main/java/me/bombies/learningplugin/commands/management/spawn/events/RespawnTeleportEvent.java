package me.bombies.learningplugin.commands.management.spawn.events;

import me.bombies.learningplugin.utils.config.Config;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnTeleportEvent implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        final Location spawnLocation = Config.spawnLocation.getSpawnLocation();
        if (spawnLocation == null)
            return;
        event.setRespawnLocation(spawnLocation);
    }
}
