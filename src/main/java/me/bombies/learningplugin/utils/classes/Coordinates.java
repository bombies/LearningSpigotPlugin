package me.bombies.learningplugin.utils.classes;

import javax.annotation.Nullable;

public class Coordinates {

    private final Triple<Double, Double, Double> coordinates;
    private final double yaw;
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

    public double getX() {
        return coordinates.getLeft();
    }

    public double getY() {
        return coordinates.getMiddle();
    }

    public double getZ() {
        return coordinates.getRight();
    }

    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }
}
