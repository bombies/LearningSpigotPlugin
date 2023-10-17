package me.bombies.learningplugin.events.custom;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameListener implements Listener {

    @EventHandler
    public void onGameEnd(GameEndCustomEvent event) {
        final var winner = event.getWinner();
        LearningPlugin.server.broadcastMessage(MessageUtils.color("""
                &6&lGame ended!
                &eWinner: &f&l%s
                """.formatted(winner.getName())));
    }
}
