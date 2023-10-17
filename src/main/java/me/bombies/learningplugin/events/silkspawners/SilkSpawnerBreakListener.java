package me.bombies.learningplugin.events.silkspawners;

import me.bombies.learningplugin.commands.misc.spawner.SpawnerCommand;
import me.bombies.learningplugin.utils.GeneralUtils;
import me.bombies.learningplugin.utils.PersistentDataHandler;
import me.bombies.learningplugin.utils.items.builders.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class SilkSpawnerBreakListener implements Listener {

    @EventHandler
    public void onSilkSpawnerBreak(BlockBreakEvent event) {
        final var block = event.getBlock();
        final var player = event.getPlayer();
        if (!SilkSpawnerUtils.isSilkSpawner(block) || player.getGameMode() != GameMode.SURVIVAL)
            return;

        final var itemInHand = player.getInventory().getItemInMainHand();
        if (!isPickaxe(itemInHand.getType()) || !itemInHand.containsEnchantment(Enchantment.SILK_TOUCH))
            return;

        final var blockState = (CreatureSpawner) block.getState();
        final var entityType = blockState.getSpawnedType();
        if (entityType == null)
            return;

        event.setDropItems(false);
        final var world = block.getWorld();

        // Build spawner to drop
        final var spawner = SpawnerCommand.buildSpawner(entityType, 1);
        world.dropItemNaturally(block.getLocation(), spawner);
    }

    private boolean isPickaxe(Material material) {
        switch (material) {
            case WOODEN_PICKAXE, STONE_PICKAXE, IRON_PICKAXE,
                    GOLDEN_PICKAXE, DIAMOND_PICKAXE, NETHERITE_PICKAXE -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
