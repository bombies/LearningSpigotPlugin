package me.bombies.learningplugin.events;

import me.bombies.learningplugin.commands.misc.login.events.LoginFreezeEvent;
import me.bombies.learningplugin.events.welcomer.PlayerWelcomeEvent;
import me.bombies.learningplugin.events.xpbottle.XPBottleBreakListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.Arrays;

public class EventManager {

    private static EventManager instance;

    private Plugin plugin;
    private PluginManager pluginManager;
    private ArrayList<Listener> eventListeners = new ArrayList<>();

    private EventManager(Plugin plugin) {
        this.pluginManager = plugin.getServer().getPluginManager();
        this.plugin = plugin;

        addListeners(
                new PlayerWelcomeEvent(),
                new XPBottleBreakListener()
//                new LoginFreezeEvent()
        );
    }

    public void registerEvents() {
        for (Listener eventListener : eventListeners)
            pluginManager.registerEvents(eventListener, plugin);
    }

    public static EventManager getInstance(Plugin plugin) {
        if (instance == null)
            instance = new EventManager(plugin);
        return instance;
    }

    private void addListeners(Listener... listeners) {
        eventListeners.addAll(Arrays.asList(listeners));
    }
}
