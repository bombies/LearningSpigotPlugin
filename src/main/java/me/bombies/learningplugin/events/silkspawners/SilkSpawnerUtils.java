package me.bombies.learningplugin.events.silkspawners;

import me.bombies.learningplugin.commands.misc.spawner.SpawnerCommand;
import me.bombies.learningplugin.utils.PersistentDataHandler;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;

import javax.annotation.Nonnull;

public class SilkSpawnerUtils {

    public static boolean isSilkSpawner(@Nonnull Block block) {
        if (block.getType() != Material.SPAWNER)
            return false;
        final var blockState = (CreatureSpawner) block.getState();
        final var blockStateData = new PersistentDataHandler(blockState);
        return blockStateData.getString(SpawnerCommand.CUSTOM_SPAWNER_ID) != null;
    }
}
