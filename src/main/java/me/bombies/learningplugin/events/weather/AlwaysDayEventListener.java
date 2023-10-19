package me.bombies.learningplugin.events.weather;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.TimeSkipEvent;

public class AlwaysDayEventListener implements Listener {

    @EventHandler
    public void onTimeChange(TimeSkipEvent event) {
        if (event.getSkipReason() == TimeSkipEvent.SkipReason.COMMAND)
            event.setSkipAmount(0);
    }
}
