package me.bombies.learningplugin.events;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.events.blockbreaksound.BlockBreakListener;
import me.bombies.learningplugin.events.welcomer.PlayerWelcomeEvent;
import me.bombies.learningplugin.events.xpbottle.XPBottleBreakListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.Arrays;

public class EventManager {

    private static EventManager instance;

    private final Plugin plugin;
    private final PluginManager pluginManager;
    private final ArrayList<Listener> eventListeners = new ArrayList<>();

    private EventManager() {
        this.pluginManager = LearningPlugin.core.getServer().getPluginManager();
        this.plugin = LearningPlugin.core;

        addListeners(
                new PlayerWelcomeEvent(),
                new XPBottleBreakListener(),
                new BlockBreakListener()
//                new LoginFreezeEvent()
        );
    }

    public void registerEvents() {
        for (Listener eventListener : eventListeners)
            pluginManager.registerEvents(eventListener, plugin);
    }

    public static EventManager getInstance() {
        if (instance == null)
            instance = new EventManager();
        return instance;
    }

    private void addListeners(Listener... listeners) {
        eventListeners.addAll(Arrays.asList(listeners));
    }
}
