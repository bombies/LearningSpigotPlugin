package me.bombies.learningplugin;

import me.bombies.learningplugin.commands.CommandManager;
import me.bombies.learningplugin.commands.misc.nick.NickService;
import me.bombies.learningplugin.events.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LearningPlugin extends JavaPlugin {

    public static JavaPlugin core = null;

    @Override
    public void onEnable() {
        core = this;

        EventManager.getInstance().registerEvents();
        CommandManager.getInstance(this).registerCommands();
    }

    @Override
    public void onDisable() {
        // Saving logic
    }
}
