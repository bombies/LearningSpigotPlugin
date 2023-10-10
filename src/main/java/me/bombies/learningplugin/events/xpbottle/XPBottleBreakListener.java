package me.bombies.learningplugin.events.xpbottle;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class XPBottleBreakListener implements Listener {

    @EventHandler
    public void onXPBottleBreak(ExpBottleEvent event) {
        final Player player = (Player) event.getEntity().getShooter();

        if (player == null)
            return;

        player.sendMessage("You broke an XP bottle! You got " + event.getExperience() + " XP!");
    }
}
