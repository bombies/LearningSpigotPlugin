package me.bombies.learningplugin.utils;

import me.bombies.learningplugin.LearningPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

public class PersistentDataHandler {
    private final PersistentDataHolder persistentDataHolder;

    public PersistentDataHandler(PersistentDataHolder persistentDataHolder) {
        this.persistentDataHolder = persistentDataHolder;
    }

    public void set(String key, String value) {
        persistentDataHolder.getPersistentDataContainer()
                .set(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.STRING, value);
    }

    public void set(String key, int value) {
        persistentDataHolder.getPersistentDataContainer()
                .set(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.INTEGER, value);
    }

    public void set(String key, double value) {
        persistentDataHolder.getPersistentDataContainer()
                .set(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.DOUBLE, value);
    }

    public void set(String key, boolean value) {
        persistentDataHolder.getPersistentDataContainer()
                .set(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.BOOLEAN, value);
    }

    public String getString(String key) {
        return persistentDataHolder.getPersistentDataContainer()
                .get(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.STRING);
    }

    public String getString(NamespacedKey key) {
        return persistentDataHolder.getPersistentDataContainer()
                .get(key, PersistentDataType.STRING);
    }

    public Integer getInt(String key) {
        return persistentDataHolder.getPersistentDataContainer()
                .get(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.INTEGER);
    }

    public Double getDouble(String key) {
        final var value = persistentDataHolder.getPersistentDataContainer()
                .get(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.DOUBLE);
        return persistentDataHolder.getPersistentDataContainer()
                .get(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.DOUBLE);
    }

    public boolean getBoolean(String key) {
        final var value = persistentDataHolder.getPersistentDataContainer()
                .get(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.BOOLEAN);
        return value != null && value;
    }

    public boolean has(String key) {
        return persistentDataHolder.getPersistentDataContainer()
                .has(new NamespacedKey(LearningPlugin.core, key), PersistentDataType.STRING);
    }
}
