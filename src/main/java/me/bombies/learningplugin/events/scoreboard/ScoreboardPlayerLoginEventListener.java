package me.bombies.learningplugin.events.scoreboard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboardPlayerLoginEventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final var player = event.getPlayer();
        final var scoreboardUtils = new ScoreboardUtils(player);
        player.setScoreboard(scoreboardUtils.createSidebar());
        player.setScoreboard(scoreboardUtils.createTabList());
    }
}
