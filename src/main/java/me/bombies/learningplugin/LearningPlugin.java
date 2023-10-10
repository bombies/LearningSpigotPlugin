package me.bombies.learningplugin;

import me.bombies.learningplugin.commands.CommandManager;
import me.bombies.learningplugin.events.EventManager;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class LearningPlugin extends JavaPlugin {

    public static JavaPlugin core = null;
    public static FileConfiguration config = null;
    public static Server server = null;

    @Override
    public void onEnable() {
        core = this;

        saveDefaultConfig();
        config = getConfig();
        server = getServer();

        EventManager.getInstance().registerEvents();
        CommandManager.getInstance(this).registerCommands();
    }

    @Override
    public void onDisable() {
        // Saving logic
    }
}
