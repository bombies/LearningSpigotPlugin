package me.bombies.learningplugin;

import me.bombies.learningplugin.commands.CommandManager;
import me.bombies.learningplugin.events.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LearningPlugin extends JavaPlugin {

    public static JavaPlugin core = null;

    @Override
    public void onEnable() {
        System.out.println("LearningPlugin enabled!");
        core = this;

        EventManager.getInstance(this).registerEvents();
        CommandManager.getInstance(this).registerCommands();
    }

    @Override
    public void onDisable() {
        System.out.println("LearningPlugin disabled!");
    }
}
