package me.bombies.learningplugin.utils.classes;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.NumberConversions;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class Coordinates implements ConfigurationSerializable {

    private final Triple<Double, Double, Double> coordinates;
    @Getter
    private final double yaw;
    @Getter
    private final double pitch;

    public Coordinates(double x, double y, double z, @Nullable Double yaw, @Nullable Double pitch) {
        this.coordinates = Triple.of(x, y, z);
        this.yaw = yaw == null ? 0 : yaw;
        this.pitch = pitch == null ? 0 : pitch;
    }

    public Coordinates(double x, double y, double z) {
        this(x, y, z, null, null);
    }

    public Coordinates(double x, double y, double z, @Nullable Double yaw) {
        this(x, y, z, yaw, null);
    }

    public Coordinates(Location location) {
        this(location.getX(), location.getY(), location.getZ(), (double) location.getYaw(), (double) location.getPitch());
    }

    public double getX() {
        return coordinates.getLeft();
    }

    public double getY() {
        return coordinates.getMiddle();
    }

    public double getZ() {
        return coordinates.getRight();
    }

    public Location toLocation(@Nullable World world) {
        return new Location(world, getX(), getY(), getZ(), (float) getYaw(), (float) getPitch());
    }

    @Override
    public Map<String, Object> serialize() {
        final var serialized = new HashMap<String, Object>();
        serialized.put("x", getX());
        serialized.put("y", getY());
        serialized.put("z", getZ());
        serialized.put("yaw", getYaw());
        serialized.put("pitch", getPitch());
        return serialized;
    }

    public static Coordinates deserialize(Map<String, Object> deserialize) {
        final var x = NumberConversions.toDouble(deserialize.get("x"));
        final var y = NumberConversions.toDouble(deserialize.get("y"));
        final var z = NumberConversions.toDouble(deserialize.get("z"));
        final var triple = Triple.of(x, y, z);

        final var yaw = NumberConversions.toDouble(deserialize.get("yaw"));
        final var pitch = NumberConversions.toDouble(deserialize.get("pitch"));
        return new Coordinates(triple.getLeft(), triple.getMiddle(), triple.getRight(), yaw, pitch);
    }
}
