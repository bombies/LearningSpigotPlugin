package me.bombies.learningplugin.commands.misc.holograms;

import lombok.Getter;
import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.utils.classes.Coordinates;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Hologram implements ConfigurationSerializable {
    private final String name;
    private final ArrayList<String> lines = new ArrayList<>();
    private final World world;
    private final Coordinates coordinates;

    public Hologram(String name, ArrayList<String> lines, World world, Coordinates coordinates) {
        this.name = name;
        this.lines.addAll(lines);
        this.world = world;
        this.coordinates = coordinates;
    }

    public Location getLocation() {
        return getCoordinates().toLocation(world);
    }

    public List<String> getColoredLines() {
        return lines.stream().map(MessageUtils::color).toList();
    }

    public void editLine(int lineIndex, String newLine) {
        lines.set(lineIndex, newLine);
    }

    public void addLine(String newLine) {
        lines.add(newLine);
    }

    public void removeLine(int lineIndex) {
        if (lineIndex < 0 || lineIndex >= lines.size())
            return;
        lines.remove(lineIndex);
    }

    @Override
    public Map<String, Object> serialize() {
        final var serialized = new HashMap<String, Object>();
        serialized.put("name", name);
        serialized.put("lines", lines);
        serialized.put("world", world.getName());
        serialized.put("coordinates", coordinates);
        return serialized;
    }

    public static Hologram deserialize(Map<String, Object> deserialize) {
        final var name = (String) deserialize.get("name");
        final var lines = (ArrayList<String>) deserialize.get("lines");
        final var worldName = (String) deserialize.get("world");
        final var world = Bukkit.getWorld(worldName);
        final var coordinatesObj = (Coordinates) deserialize.get("coordinates");
        return new Hologram(name, lines, world, coordinatesObj);
    }
}
