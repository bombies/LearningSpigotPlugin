package me.bombies.learningplugin;

import me.bombies.learningplugin.commands.CommandManager;
import me.bombies.learningplugin.events.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LearningPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("LearningPlugin enabled!");
        EventManager.getInstance(this).registerEvents();
        CommandManager.getInstance(this).registerCommands();
    }

    @Override
    public void onDisable() {
        System.out.println("LearningPlugin disabled!");
    }
}
