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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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

        final var player = event.getPlayer();
        if (player.getGameMode() != org.bukkit.GameMode.CREATIVE)
            event.getItemInHand().setAmount(itemInHand.getAmount() - 1);

        final var placedLocation = event.getBlockPlaced().getLocation();
        if (placedLocation.getWorld() == null)
            return;

        final int SECONDS_BEFORE_DETONATION = 10;
        final int BLAST_RADIUS = 100;

        final var spawnedEntity = (TNTPrimed) placedLocation.getWorld().spawnEntity(placedLocation, EntityType.PRIMED_TNT);
        spawnedEntity.setFuseTicks(20 * SECONDS_BEFORE_DETONATION);

        final var countDownRunnable = new BukkitRunnable() {
            final long detonationTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(SECONDS_BEFORE_DETONATION);
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
                    detonate(tntLocation, BLAST_RADIUS);
                    detonated = true;
                } else {
                    world.getNearbyEntities(tntLocation, BLAST_RADIUS, BLAST_RADIUS, BLAST_RADIUS, entity -> entity instanceof Player)
                            .forEach(entity -> {
                                final var player = (Player) entity;
                                player.sendTitle(MessageUtils.color("&e&l☣☣☣ &c&lNUKE DETONATION&e&l☣☣☣"), MessageUtils.color("&c&lIN " + TimeUnit.MILLISECONDS.toSeconds(detonationTime - System.currentTimeMillis()) + " SECONDS"), 0, 20, 0);
                            });
                }
            }
        };

        countDownRunnable.runTaskTimer(LearningPlugin.core, 0, 20 * SECONDS_BEFORE_DETONATION);
    }

    private void detonate(Location location, int blastRadius) {
        final var world = location.getWorld();
        if (world == null)
            return;

        world.createExplosion(location, Math.min(200, blastRadius), true, true);

        // If anyone's still alive
        world.getNearbyEntities(location, blastRadius, blastRadius, blastRadius, entity -> entity instanceof Player)
                .forEach(entity -> {
                    final var player = (Player) entity;
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 1));
                });
    }
}
