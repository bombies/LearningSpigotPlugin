package me.bombies.learningplugin.utils.config;

import me.bombies.learningplugin.utils.classes.Coordinates;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnLocationConfig extends AbstractConfig {

    private static SpawnLocationConfig instance = null;

    private SpawnLocationConfig(FileConfiguration config) {
        super(config);
    }

    static SpawnLocationConfig getInstance(FileConfiguration config) {
        if (instance == null)
            instance = new SpawnLocationConfig(config);
        return instance;
    }

    public Coordinates getSpawnLocationCoords() {
        final double x = getDouble("spawn.location.x");
        final double y = getDouble("spawn.location.y");
        final double z = getDouble("spawn.location.z");
        final double yaw = getDouble("spawn.location.yaw");
        return new Coordinates(x, y, z, yaw);
    }

    public String getSpawnWorldName() {
        return getString("spawn.world");
    }

    public World getSpawnWorld() {
        final String worldName = getSpawnWorldName();
        if (worldName == null)
            return null;
        return Bukkit.getWorld(worldName);
    }

    public Location getSpawnLocation() {
        final Coordinates coords = getSpawnLocationCoords();
        final World world = getSpawnWorld();
        if (world == null || coords == null)
            return null;
        return new Location(world, coords.getX(), coords.getY(), coords.getZ(), (float) coords.getYaw(), 0);
    }

    public void setSpawnLocation(Location location) {
        final World world = location.getWorld();
        if (world == null)
            throw new IllegalArgumentException("Location must have a world!");

        setSpawnWorld(world);
        set("spawn.location.x", location.getX());
        set("spawn.location.y", location.getY());
        set("spawn.location.z", location.getZ());
        set("spawn.location.yaw", location.getYaw());
        save();
    }

    public void setSpawnWorld(World world) {
        set("spawn.world", world.getName());
    }
}
