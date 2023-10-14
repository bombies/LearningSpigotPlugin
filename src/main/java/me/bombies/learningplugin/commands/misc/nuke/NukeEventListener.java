package me.bombies.learningplugin.commands.misc.nuke;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.utils.PersistentDataHandler;
import me.bombies.learningplugin.utils.items.builders.ItemBuilder;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public class NukeEventListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        final var itemInHand = event.getItemInHand();
        final var itemInHandMeta = itemInHand.getItemMeta();
        if (itemInHandMeta == null)
            return;

        final var itemData = new PersistentDataHandler(itemInHandMeta);
        final var itemDataCustomId = itemData.getString(ItemBuilder.CUSTOM_ITEM_KEY);
        if (itemDataCustomId == null || !itemDataCustomId.equals("nuke"))
            return;

        event.setCancelled(true);

        final var placedLocation = event.getBlockPlaced().getLocation();
        if (placedLocation.getWorld() == null)
            return;

        final var spawnedEntity = (TNTPrimed) placedLocation.getWorld().spawnEntity(placedLocation, EntityType.PRIMED_TNT);
        spawnedEntity.setFuseTicks(20 * 11);

        final var countDownRunnable = new BukkitRunnable() {
            final long detonationTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10);
            private boolean detonated = false;

            @Override
            public void run() {
                if (detonated) {
                    this.cancel();
                    return;
                }

                final var world = placedLocation.getWorld();
                final var tntLocation = spawnedEntity.getLocation();

                if (System.currentTimeMillis() >= detonationTime) {
                    spawnedEntity.remove();
                    world.createExplosion(tntLocation, 100, true, true);
                    detonated = true;
                } else {
                    world.getNearbyEntities(tntLocation, 100, 100, 100, entity -> entity instanceof Player)
                            .forEach(entity -> {
                                final var player = (Player) entity;
                                player.sendTitle(MessageUtils.color("&e&l☣☣☣ &c&lNUKE DETONATION&e&l☣☣☣"), MessageUtils.color("&c&lIN " + TimeUnit.MILLISECONDS.toSeconds(detonationTime - System.currentTimeMillis()) + " SECONDS"), 0, 20, 0);
                            });
                }
            }
        };

        countDownRunnable.runTaskTimer(LearningPlugin.core, 0, 20 * 1);
    }

    @EventHandler
    public void onExplosionDamage(EntityDamageByEntityEvent e) {

    }
}
