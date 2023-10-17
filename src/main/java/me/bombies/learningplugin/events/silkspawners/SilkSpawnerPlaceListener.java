package me.bombies.learningplugin.events.silkspawners;

import me.bombies.learningplugin.commands.misc.spawner.SpawnerCommand;
import me.bombies.learningplugin.utils.MinecraftTimeUnit;
import me.bombies.learningplugin.utils.PersistentDataHandler;
import me.bombies.learningplugin.utils.classes.Coordinates;
import me.bombies.learningplugin.utils.items.ItemUtils;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.permissions.ServerOperator;

public class SilkSpawnerPlaceListener implements Listener {

    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        final var block = event.getBlock();
        final var player = event.getPlayer();

        if (block.getType() != Material.SPAWNER)
            return;

        final var itemInHand = player.getInventory().getItemInMainHand();
        final var itemInHandMeta = itemInHand.getItemMeta();
        final var itemData = new PersistentDataHandler(itemInHandMeta);

        final var customId = ItemUtils.getCustomId(itemInHand);
        if (customId != null && !customId.equals(SpawnerCommand.CUSTOM_SPAWNER_ID))
            return;

        final var spawnerEntityType = EntityType.valueOf(itemData.getString(SpawnerCommand.CUSTOM_SPAWNER_ENTITY_TYPE));
        final var spawnerState = (CreatureSpawner) block.getState();
        final var spawnerStateData = new PersistentDataHandler(spawnerState);
        spawnerStateData.set(SpawnerCommand.CUSTOM_SPAWNER_ID, "custom_spawner");
        spawnerStateData.set("customer_spawner_owner", player.getUniqueId().toString());
        spawnerStateData.set(SpawnerCommand.CUSTOM_SPAWNER_ENTITY_TYPE, spawnerEntityType.toString());

        spawnerState.setSpawnedType(spawnerEntityType);
        spawnerState.setSpawnCount(4);
        spawnerState.setMinSpawnDelay((int) MinecraftTimeUnit.TICKS.fromSeconds(2));
        spawnerState.setMaxSpawnDelay((int) MinecraftTimeUnit.TICKS.fromSeconds(10));
        spawnerState.update();

        for (final var operator : Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).toList())
            operator.sendMessage(MessageUtils.color("&c&lSpawners &8» &c" + player.getName() + " &7placed a &c&l" + spawnerEntityType + "&7 spawner at &c" + new Coordinates(block.getLocation()) + "&7!"));
    }

    @EventHandler
    public void onSpawnerTypeChange(PlayerInteractEvent event) {
        final var clickedBlock = event.getClickedBlock();
        final var item = event.getItem();
        if (clickedBlock == null || !SilkSpawnerUtils.isSilkSpawner(clickedBlock) || item == null || event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        event.setCancelled(true);

    }


}
