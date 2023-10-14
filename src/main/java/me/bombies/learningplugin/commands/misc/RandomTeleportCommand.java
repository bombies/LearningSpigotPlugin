package me.bombies.learningplugin.commands.misc;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RandomTeleportCommand extends PlayerCommand {

    public RandomTeleportCommand() {
        super("randomteleport", 1, TimeUnit.MINUTES);
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        final var location = generateRandomLocation(player.getLocation());

        if (location != null) {
            player.teleport(location);
            player.sendMessage(MessageUtils.color("You have been teleported to a random location"));
        }

        return true;
    }

    @Nullable
    private Location generateRandomLocation(Location currentLocation) {
        final var world = currentLocation.getWorld();
        if (world == null) // Somehow
            return null;

        // The teleport will have an offset of 100000 blocks from the current location in the X and Z axis
        final var x = currentLocation.getX() + (Math.random() * 100000);
        final var z = currentLocation.getZ() + (Math.random() * 100000);
        final var y = world.getHighestBlockYAt((int) x, (int) z) + 1;
        final var blockAtLoc = world.getBlockAt((int) x, (int) y, (int) z);
        if (!isLocationSafe(blockAtLoc))
            generateRandomLocation(currentLocation);
        return new Location(world, x, y, z);
    }

    private boolean isLocationSafe(Block block) {
        final var location = block.getLocation();
        final var unsafeBlocks = List.of(Material.LAVA, Material.FIRE, Material.CACTUS, Material.MAGMA_BLOCK);

        final var blockAbove = location.clone().add(0, 1, 0).getBlock();
        final var blockBelow = location.clone().add(0, -1, 0).getBlock();

        return !unsafeBlocks.contains(block.getType())
                && !unsafeBlocks.contains(blockAbove.getType())
                && !unsafeBlocks.contains(blockBelow.getType())
                && !blockAbove.getType().isSolid();
    }
}
