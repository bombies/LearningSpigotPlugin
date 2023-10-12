package me.bombies.learningplugin.utils.config;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.utils.classes.Pair;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;

public abstract class AbstractConfig {

    private final FileConfiguration pluginConfig;

    protected AbstractConfig(FileConfiguration config) {
        this.pluginConfig = config;
    }


    protected Object getRaw(String path) {
        return pluginConfig.get(path);
    }

    protected <T> T get(String path) {
        return (T) getRaw(path);
    }

    protected List<Map<String, Object>> getMapList(String path) {
        return pluginConfig.getMapList(path)
                .stream()
                .map((o) -> (Map<String, Object>) o)
                .toList();
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

    protected List<String> getStringList(String path) {
        return pluginConfig.getStringList(path);
    }

    protected List<Integer> getIntList(String path) {
        return pluginConfig.getIntegerList(path);
    }

    protected List<Double> getDoubleList(String path) {
        return pluginConfig.getDoubleList(path);
    }

    protected void delete(String path) {
        if (pluginConfig.get(path) != null)
            pluginConfig.set(path, null);
    }

    @SafeVarargs
    protected final void set(Pair<String, Object>... entries) {
        for (final var entry : entries)
            set(entry.getLeft(), entry.getRight());
    }

    protected void save() {
        LearningPlugin.core.saveConfig();
    }
}
