package me.bombies.learningplugin.commands.teleportbow;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.utils.items.builders.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataType;

public class TeleportBowEventListener implements Listener {
    private final static NamespacedKey TELEPORT_ARROW_KEY = new NamespacedKey(LearningPlugin.core, "teleport_arrow");

    @EventHandler
    public void onArrowLand(ProjectileHitEvent event) {
        final var entity = event.getEntity();
        final var shooter = event.getEntity().getShooter();
        if (entity.getType() != EntityType.ARROW || !(shooter instanceof Player p))
            return;

        final var entityPersistentDataContainer = entity.getPersistentDataContainer();
        if (!entityPersistentDataContainer.has(TELEPORT_ARROW_KEY, PersistentDataType.STRING))
            return;

        final var hitEntity = event.getHitEntity();
        if (hitEntity != null) {
            event.setCancelled(true);
            return;
        }

        final var hitBlock = event.getHitBlock();
        if (hitBlock == null)
            return;

        final var blockLocation = hitBlock.getLocation();
        final var location = new Location(
                p.getWorld(),
                blockLocation.getX(),
                blockLocation.getY() + 1,
                blockLocation.getZ(),
                p.getLocation().getYaw(),
                p.getLocation().getPitch()
        );

        p.teleport(location);
        entity.remove();
    }

    @EventHandler
    public void onArrowShoot(ProjectileLaunchEvent event) {
        final var entity = event.getEntity();
        final var shooter = entity.getShooter();
        if (entity.getType() != EntityType.ARROW && !(shooter instanceof Player))
            return;

        final var player = (Player) shooter;
        assert player != null;

        final var itemInMainMainHand = player.getInventory().getItemInMainHand();
        final var mainHandItemMeta = itemInMainMainHand.getItemMeta();
        if (mainHandItemMeta == null)
            return;

        final var mainHandItemDataContainer = mainHandItemMeta.getPersistentDataContainer();
        final var customId = mainHandItemDataContainer.get(ItemBuilder.CUSTOM_ITEM_KEY, PersistentDataType.STRING);

        if (customId == null || !customId.equals(TeleportBowMetaData.ID))
            return;

        final var owner = mainHandItemDataContainer.get(new NamespacedKey(LearningPlugin.core, TeleportBowMetaData.OWNER), PersistentDataType.STRING);
        if (owner == null || !owner.equals(player.getUniqueId().toString()))
            return;

        final var entityPersistentDataContainer = entity.getPersistentDataContainer();
        entityPersistentDataContainer.set(TELEPORT_ARROW_KEY, PersistentDataType.STRING, player.getUniqueId().toString());
    }
}
