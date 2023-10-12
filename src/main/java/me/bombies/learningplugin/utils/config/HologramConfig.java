package me.bombies.learningplugin.utils.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.commands.misc.holograms.Hologram;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class HologramConfig extends AbstractConfig {

    private static HologramConfig instance = null;

    protected static HologramConfig getInstance(FileConfiguration config) {
        if (instance == null)
            instance = HologramConfigHolder.INSTANCE(config);
        return instance;
    }

    private HologramConfig(FileConfiguration config) {
        super(config);
    }

    public void createHologram(Hologram hologram) {
        final var holograms = new ArrayList<>(getHolograms());
        holograms.add(hologram);
        set("holograms.holograms", holograms);
        save();
    }

    /**
     * Update a hologram in the config.
     *
     * @param hologram Hologram to update
     * @throws IllegalArgumentException If hologram does not exist
     */
    public void updateHologram(Hologram hologram) throws IllegalArgumentException {
        final var holograms = new ArrayList<>(getHolograms());
        final var hologramIndex = holograms.indexOf(hologram);
        if (hologramIndex == -1)
            throw new IllegalArgumentException("Could not update hologram: " + hologram.getName());
        holograms.remove(hologram);
        holograms.add(hologramIndex, hologram);
        set("holograms.holograms", holograms);
        save();
    }

    public Hologram getHologram(String name) {
        return getHolograms()
                .stream()
                .filter(hologram -> hologram.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Hologram> getHolograms() {
        final List<Hologram> holograms = get("holograms.holograms");
        return holograms == null ? new ArrayList<>() : holograms;
    }

    public void removeHologram(Hologram hologram) {
        final var holograms = new ArrayList<>(getHolograms());
        holograms.remove(hologram);
        set("holograms.holograms", holograms);
    }


    // This type of singleton is useful for threaded environments.
    private static class HologramConfigHolder {
        private static HologramConfig INSTANCE(FileConfiguration config) {
            return new HologramConfig(config);
        }
    }
}
