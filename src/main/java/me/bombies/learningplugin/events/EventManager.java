package me.bombies.learningplugin.events;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.commands.management.spawn.events.RespawnTeleportEventListener;
import me.bombies.learningplugin.commands.misc.holograms.HologramEventListener;
import me.bombies.learningplugin.commands.misc.invsee.InventorySeeMoveEventListener;
import me.bombies.learningplugin.commands.misc.nuke.NukeEventListener;
import me.bombies.learningplugin.commands.misc.signs.SignEventListener;
import me.bombies.learningplugin.commands.teleportbow.TeleportBowEventListener;
import me.bombies.learningplugin.events.custom.GameListener;
import me.bombies.learningplugin.events.customchat.CustomChatEventListener;
import me.bombies.learningplugin.events.weather.RainEventListener;
import me.bombies.learningplugin.events.welcomer.PlayerWelcomeEventListener;
import org.bukkit.event.Event;
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
                new PlayerWelcomeEventListener(),
//                new XPBottleBreakListener(),
//                new BlockBreakListener(),
//                new LoginFreezeEvent()
                new CustomChatEventListener(),
                new RespawnTeleportEventListener(),
                new RainEventListener(),
                new InventorySeeMoveEventListener(),
                new TeleportBowEventListener(),
                new HologramEventListener(),
                new SignEventListener(),
                new NukeEventListener(),
                new GameListener()
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

    public static <E extends Event> void call(E event) {
        LearningPlugin.core.getServer().getPluginManager().callEvent(event);
    }

    private void addListeners(Listener... listeners) {
        eventListeners.addAll(Arrays.asList(listeners));
    }
}
