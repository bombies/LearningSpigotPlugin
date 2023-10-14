package me.bombies.learningplugin.commands.misc.holograms;

import lombok.Getter;
import me.bombies.learningplugin.utils.classes.Coordinates;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Hologram implements ConfigurationSerializable {
    private final String name;
    private final ArrayList<String> lines = new ArrayList<>();
    @Nullable
    private Material itemMaterial;
    private final World world;
    private final Coordinates coordinates;

    public Hologram(String name, ArrayList<String> lines, @Nullable Material itemMaterial, World world, Coordinates coordinates) {
        this.name = name;
        this.itemMaterial = itemMaterial;
        this.lines.addAll(lines);
        this.world = world;
        this.coordinates = coordinates;
    }

    public Hologram(String name, ArrayList<String> lines, World world, Coordinates coordinates) {
        this(name, lines, null, world, coordinates);
    }

    public Location getLocation() {
        return getCoordinates().toLocation(world);
    }

    public List<String> getColoredLines() {
        return lines.stream().map(MessageUtils::color).toList();
    }

    public void editLine(int lineID, String newLine) {
        lines.set(lineID - 1, newLine);
    }

    public void editItem(Material itemMaterial) {
        this.itemMaterial = itemMaterial;
    }

    public void addLine(String newLine) {
        lines.add(newLine);
    }

    public void removeLine(int lineID) {
        if (lineID < 0 || lineID > lines.size())
            return;
        lines.remove(lineID - 1);
    }

    @Override
    public Map<String, Object> serialize() {
        final var serialized = new HashMap<String, Object>();
        serialized.put("name", name);
        serialized.put("lines", lines);
        serialized.put("world", world.getName());
        serialized.put("coordinates", coordinates);
        if (itemMaterial != null)
            serialized.put("item", itemMaterial.name());
        return serialized;
    }

    public static Hologram deserialize(Map<String, Object> deserialize) {
        final var name = (String) deserialize.get("name");
        final var lines = (ArrayList<String>) deserialize.get("lines");
        final var worldName = (String) deserialize.get("world");
        final var world = Bukkit.getWorld(worldName);
        final var coordinatesObj = (Coordinates) deserialize.get("coordinates");
        final var itemMaterialName = (String) deserialize.get("item");
        final var itemMaterial = itemMaterialName == null ? null : Material.matchMaterial(itemMaterialName);
        return new Hologram(name, lines, itemMaterial, world, coordinatesObj);
    }
}
