package me.bombies.learningplugin.commands.teleportbow;

import me.bombies.learningplugin.utils.PersistentDataHandler;
import me.bombies.learningplugin.utils.items.builders.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class TeleportBowEventListener implements Listener {
    private final static String TELEPORT_ARROW_KEY = "teleport_arrow";

    @EventHandler
    public void onArrowLand(ProjectileHitEvent event) {
        final var entity = event.getEntity();
        final var shooter = event.getEntity().getShooter();
        if (entity.getType() != EntityType.ARROW || !(shooter instanceof Player p))
            return;

        final var entityPersistentData = new PersistentDataHandler(entity);
        if (!entityPersistentData.has(TELEPORT_ARROW_KEY))
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
        if (entity.getType() != EntityType.ARROW || !(shooter instanceof Player player))
            return;

        final var itemInMainMainHand = player.getInventory().getItemInMainHand();
        final var mainHandItemMeta = itemInMainMainHand.getItemMeta();
        if (mainHandItemMeta == null)
            return;

        final var itemPersistentData = new PersistentDataHandler(mainHandItemMeta);
        final var customId = itemPersistentData.getString(ItemBuilder.CUSTOM_ITEM_KEY);

        if (customId == null || !customId.equals(TeleportBowMetaDataKey.ID))
            return;

        final var owner = itemPersistentData.getString(TeleportBowMetaDataKey.OWNER);
        if (owner == null || !owner.equals(player.getUniqueId().toString()))
            return;

        final var entityPersistentData = new PersistentDataHandler(entity);
        entityPersistentData.set(TELEPORT_ARROW_KEY, player.getUniqueId().toString());
    }
}
