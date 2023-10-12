package me.bombies.learningplugin.commands.misc.holograms;

import lombok.Getter;
import me.bombies.learningplugin.utils.PersistentDataHandler;
import me.bombies.learningplugin.utils.classes.Coordinates;
import me.bombies.learningplugin.utils.config.Config;
import me.bombies.learningplugin.utils.config.HologramConfig;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
        holograms.forEach(HologramService::updateWorldHologram);
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
        addHologram(name, location, text, null);
    }

    public static void addHologram(String name, Location location, String text, @Nullable Material itemMaterial) throws IllegalArgumentException {
        final var world = location.getWorld();
        final var coordinates = new Coordinates(location);
        final var hologram = new Hologram(name, new ArrayList<>(List.of(text)), itemMaterial, world, coordinates);
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

    public static void createWorldHologram(@Nonnull World world, @Nonnull Location location, @Nonnull String name, List<String> lines, @Nullable Material itemMaterial) {
        final var existingHologram = findWorldHologram(world, location, name, itemMaterial);
        if (!existingHologram.isEmpty())
            return;

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

        if (itemMaterial != null) {
            final var item = new ItemStack(itemMaterial);
            final var itemMeta = item.getItemMeta();
            final var itemData = new PersistentDataHandler(itemMeta);
            itemData.set("hologram_item", true);
            itemData.set("hologram", true);
            itemData.set("hologram_name", name.toLowerCase());
            item.setItemMeta(itemMeta);

            final var droppedItem = world.dropItem(
                    new Location(world, Math.floor(location.getX()) + 0.5, prevLocation.getY() + 1, Math.floor(location.getZ()) + 0.5),
                    item
            );

            droppedItem.setUnlimitedLifetime(true);
            droppedItem.setGravity(false);
            droppedItem.setPickupDelay(Integer.MAX_VALUE);
        }
    }

    public static void createWorldHologram(@Nonnull Player player, @Nonnull String name, @Nullable String text) {
        createWorldHologram(player.getWorld(), player.getLocation(), name, text);
    }

    public static void updateWorldHologram(@Nonnull Hologram hologram) {
        final var entities = findWorldHologram(hologram);
        final var currentLines = hologram.getColoredLines();
        final var armorStandCount = entities.stream().filter(e -> e.getType() == EntityType.ARMOR_STAND).count();
        final var floatingItem = entities.stream()
                .filter(e -> e instanceof Item)
                .map(e -> (Item) e)
                .findFirst()
                .orElse(null);

        if (armorStandCount != hologram.getLines().size()
                || !entities.stream().map(Entity::getCustomName).toList().equals(currentLines)
                || (hologram.getItemMaterial() != null && (floatingItem == null || floatingItem.getItemStack()
                        .getData()
                        .getItemType() != hologram.getItemMaterial()))
        ) {
            entities.forEach(armorStand -> {
                final var armorStandData = new PersistentDataHandler(armorStand);
                armorStandData.set("hologram_deleted", true);
                armorStand.remove();
            });

            if (floatingItem != null)
                floatingItem.remove();

            createWorldHologram(
                    hologram.getWorld(),
                    hologram.getLocation(),
                    hologram.getName(),
                    hologram.getLines(),
                    hologram.getItemMaterial()
            );
        }
    }

    public static void removeWorldHologram(Hologram hologram, @Nullable Consumer<List<Entity>> onRemove) {
        final var entities = findWorldHologram(hologram);
        if (entities.isEmpty())
            return;
        entities.forEach(armorStand -> {
            final var armorStandData = new PersistentDataHandler(armorStand);
            armorStandData.set("hologram_deleted", true);
            armorStand.remove();
        });

        if (onRemove != null)
            onRemove.accept(entities);
    }

    public static void removeWorldHologram(Hologram hologram) {
        removeWorldHologram(hologram, null);
    }


    /**
     * Create a hologram in the world.
     *
     * @param hologram The hologram to create
     * @return The hologram or null if it doesn't exist.
     */
    public static List<Entity> findWorldHologram(@Nonnull Hologram hologram) {
        final var world = hologram.getWorld();
        final var location = new Location(world, hologram.getCoordinates().getX(), hologram.getCoordinates().getY(), hologram.getCoordinates().getZ());
        return findWorldHologram(world, location, hologram.getName(), hologram.getItemMaterial());
    }

    public static List<Entity> findWorldHologram(World world, Location location, String name, @Nullable Material itemMaterial) {
        return world.getNearbyEntities(location, 2, 255, 2, e -> {
                    if (itemMaterial == null)
                        return e.getType() == EntityType.ARMOR_STAND;
                    else return e.getType() == EntityType.ARMOR_STAND || e.getType() == EntityType.DROPPED_ITEM;
                })
                .stream()
                .filter(entity -> {
                    final var entityData = new PersistentDataHandler(entity);
                    return entityData.getString("hologram_name").equalsIgnoreCase(name);
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
