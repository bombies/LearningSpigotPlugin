package me.bombies.learningplugin.commands.misc.holograms;

import me.bombies.learningplugin.utils.PersistentDataHandler;
import me.bombies.learningplugin.utils.classes.Coordinates;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HologramService {

    private final static List<Hologram> holograms = new ArrayList<>();

    /**
     * Add a hologram to the world.
     *
     * @param hologram Hologram to add
     * @throws IllegalArgumentException If hologram with this name already exists
     */
    public static void addHologram(Hologram hologram) throws IllegalArgumentException {
        if (holograms.stream().anyMatch(h -> h.getName().equalsIgnoreCase(hologram.getName())))
            throw new IllegalArgumentException("Hologram with this name already exists");
        holograms.add(hologram);
    }

    /**
     * Add a hologram to the world.
     *
     * @param name     The name of the hologram
     * @param location The location of the hologram
     * @throws IllegalArgumentException If hologram with this name already exists
     */
    public static void addHologram(String name, Location location, String text) throws IllegalArgumentException {
        final var world = location.getWorld();
        final var coordinates = new Coordinates(location);
        final var hologram = new Hologram(name, List.of(text), world, coordinates);
        addHologram(hologram);
    }

    /**
     * Create a hologram in the world.
     *
     * @param world    The world to create the hologram in
     * @param location The location of the hologram
     * @param name     The name of the hologram
     * @param text     The text of the hologram
     */
    public static void createWorldHologram(@Nonnull World world, @Nonnull Location location, @Nonnull String name, @Nullable String text) {
        final var armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        final var armorStandData = new PersistentDataHandler(armorStand);
        armorStandData.set("hologram", true);
        armorStandData.set("hologram_name", name);

        armorStand.setCustomName(MessageUtils.color(text == null ? "&l" : text));
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setInvulnerable(true);
        armorStand.setCollidable(false);
        armorStand.setMarker(true);
        armorStand.setRemoveWhenFarAway(false);
    }

    public static void createWorldHologram(@Nonnull Player player, @Nonnull String name, @Nullable String text) {
        createWorldHologram(player.getWorld(), player.getLocation(), name, text);
    }


    /**
     * Create a hologram in the world.
     *
     * @param hologram The hologram to create
     * @return The hologram or null if it doesn't exist.
     */
    public static ArmorStand findWorldHologram(Hologram hologram) {
        final var world = hologram.getWorld();
        final var location = new Location(world, hologram.getCoordinates().getX(), hologram.getCoordinates().getY(), hologram.getCoordinates().getZ());
        return world.getNearbyEntities(location, 1, 1, 1).stream()
                .filter(entity -> entity instanceof ArmorStand)
                .map(entity -> (ArmorStand) entity)
                .filter(armorStand -> {
                    final var armorStandData = new PersistentDataHandler(armorStand);
                    return armorStandData.has("hologram_name") && armorStandData.getString("hologram_name").equalsIgnoreCase(hologram.getName());
                })
                .findFirst()
                .orElse(null);
    }

    /**
     * Create a hologram in the world.
     *
     * @param player The player creating the hologram
     * @param name   The name of the hologram
     */
    public static void createWorldHologram(Player player, String name) {
        createWorldHologram(player, name, null);
    }

    public static void removeHologram(String name) {
        holograms.removeIf(hologram -> hologram.getName().equalsIgnoreCase(name));
    }

    public static Hologram getHologram(String name) {
        return holograms.stream()
                .filter(hologram -> hologram.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static List<Hologram> getHolograms() {
        return holograms;
    }
}
