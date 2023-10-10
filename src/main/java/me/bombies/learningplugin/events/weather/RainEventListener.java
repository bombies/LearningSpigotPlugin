package me.bombies.learningplugin.events.weather;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class RainEventListener implements Listener {

    @EventHandler
    public void onRain(WeatherChangeEvent e) {
        if (e.toWeatherState())
            e.setCancelled(true);
    }
}
