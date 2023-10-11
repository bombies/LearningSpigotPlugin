package me.bombies.learningplugin.commands.misc.holograms;

import me.bombies.learningplugin.utils.classes.Coordinates;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class Hologram {
    private final String name;
    private final List<String> lines = new ArrayList<>();
    private final World world;
    private final Coordinates coordinates;

    public Hologram(String name, List<String> lines, World world, Coordinates coordinates) {
        this.name = name;
        this.lines.addAll(lines);
        this.world = world;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public List<String> getLines() {
        return lines;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Location getLocation() {
        return getCoordinates().toLocation(world);
    }

    public World getWorld() {
        return world;
    }

    public void editLine(int lineIndex, String newLine) {
        lines.set(lineIndex, MessageUtils.color(newLine));
    }

    public void addLine(String newLine) {
        lines.add(MessageUtils.color(newLine));
    }

    public void removeLine(int lineIndex) {
        if (lineIndex < 0 || lineIndex >= lines.size())
            return;
        lines.remove(lineIndex);
    }
}
