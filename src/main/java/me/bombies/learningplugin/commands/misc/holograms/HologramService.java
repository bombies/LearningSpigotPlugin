package me.bombies.learningplugin.commands.misc.holograms;

import lombok.Getter;
import me.bombies.learningplugin.utils.PersistentDataHandler;
import me.bombies.learningplugin.utils.classes.Coordinates;
import me.bombies.learningplugin.utils.config.Config;
import me.bombies.learningplugin.utils.config.HologramConfig;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HologramService {

    @Getter
    private final static List<Hologram> holograms = new ArrayList<>();

    private static final HologramConfig config = Config.holograms;

    public static void loadHolograms() {
        final var configHolograms = config.getHolograms();
        if (configHolograms == null)
            return;

        holograms.addAll(configHolograms);

        // Validate holograms
        holograms.forEach(hologram -> {
            final var armorStands = findWorldHologram(hologram);
            if (armorStands.size() != hologram.getLines().size()
                    || armorStands.stream().
                            map(Entity::getCustomName)
                            .allMatch(name -> hologram.getColoredLines().contains(name))
            ) {
                armorStands.forEach(armorStand -> {
                    final var armorStandData = new PersistentDataHandler(armorStand);
                    armorStandData.set("hologram_deleted", true);
                    armorStand.remove();
                });

                createWorldHologram(
                        hologram.getWorld(),
                        hologram.getLocation(),
                        hologram.getName(),
                        hologram.getLines()
                );
            }
        });
    }

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
        config.createHologram(hologram);
    }

    public static void removeHologram(Hologram hologram) {
        if (!holograms.remove(hologram))
            throw new IllegalArgumentException("There is no hologram with that name!");
        config.removeHologram(hologram);
    }

    public static void updateHologram(@Nonnull Hologram hologram) {
        config.updateHologram(hologram);
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
        final var hologram = new Hologram(name, new ArrayList<>(List.of(text)), world, coordinates);
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
        armorStandData.set("hologram_name", name.toLowerCase());

        armorStand.setCustomName(MessageUtils.color(text == null ? "&l" : text));
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setInvulnerable(true);
        armorStand.setCollidable(false);
        armorStand.setMarker(true);
        armorStand.setRemoveWhenFarAway(false);
    }

    public static void deleteWorldHologram(List<ArmorStand> armorStands) {
        armorStands.forEach(Entity::remove);
    }

    public static void createWorldHologram(@Nonnull World world, @Nonnull Location location, @Nonnull String name, List<String> lines) {
        var prevLocation = location.clone();
        for (String line : lines) {
            final var newLocation = prevLocation.subtract(0, 0.30, 0);
            createWorldHologram(
                    world,
                    newLocation,
                    name,
                    MessageUtils.color(line)
            );
            prevLocation = newLocation;
        }
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
    public static List<ArmorStand> findWorldHologram(Hologram hologram) {
        final var world = hologram.getWorld();
        final var location = new Location(world, hologram.getCoordinates().getX(), hologram.getCoordinates().getY(), hologram.getCoordinates().getZ());
        return world.getNearbyEntities(location, 2, 255, 2, e -> e.getType() == EntityType.ARMOR_STAND)
                .stream()
                .map(entity -> (ArmorStand) entity)
                .filter(armorStand -> {
                    final var armorStandData = new PersistentDataHandler(armorStand);
                    return armorStandData.getString("hologram_name").equalsIgnoreCase(hologram.getName());
                })
                .toList();
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
        final var hologram = holograms.stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (hologram == null)
            return;
        holograms.remove(hologram);
        config.removeHologram(hologram);
    }

    public static Hologram getHologram(String name) {
        return holograms.stream()
                .filter(hologram -> hologram.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
