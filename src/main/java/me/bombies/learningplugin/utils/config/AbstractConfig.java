package me.bombies.learningplugin.utils.config;

import me.bombies.learningplugin.LearningPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class AbstractConfig {

     protected final FileConfiguration pluginConfig = LearningPlugin.config;


    protected Object get(String path) {
        return pluginConfig.get(path);
    }

    protected String getString(String path) {
        return pluginConfig.getString(path);
    }

    protected double getDouble(String path) {
        return pluginConfig.getDouble(path);
    }

    protected void setAndSave(String path, Object value) {
        set(path, value);
        save();
    }

    protected void set(String path, Object value) {
        pluginConfig.set(path, value);
    }

    protected void save() {
        LearningPlugin.core.saveConfig();
    }
}
