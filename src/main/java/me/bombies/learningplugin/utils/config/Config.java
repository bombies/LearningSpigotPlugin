package me.bombies.learningplugin.utils.config;

import me.bombies.learningplugin.commands.misc.holograms.Hologram;
import me.bombies.learningplugin.commands.misc.holograms.HologramService;
import me.bombies.learningplugin.utils.classes.Coordinates;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

public class Config {

    public static void registerSerializers() {
        ConfigurationSerialization.registerClass(Coordinates.class);
        ConfigurationSerialization.registerClass(Hologram.class);
    }

    public static SpawnLocationConfig spawnLocation = null;
    public static HologramConfig holograms = null;

    public static void init(FileConfiguration config) {
        spawnLocation = SpawnLocationConfig.getInstance(config);
        holograms = HologramConfig.getInstance(config);

    }
}
