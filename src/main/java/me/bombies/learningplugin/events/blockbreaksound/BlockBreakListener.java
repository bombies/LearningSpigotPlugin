package me.bombies.learningplugin.events.blockbreaksound;

import me.bombies.learningplugin.LearningPlugin;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        final Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1f);
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        final Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1.5f);
        spawnFireworks(player.getLocation(), 1);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getEntityType() != EntityType.PLAYER)
            return;

        final Entity damager = event.getDamager();
        if (damager instanceof Firework && damager.hasMetadata("block_break_firework"))
            event.setCancelled(true);
    }

    public static void spawnFireworks(Location location, int amount) {
        final World world = location.getWorld();
        if (world == null)
            return;

        Firework fw = (Firework) world.spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder()
                .withColor(Color.LIME)
                .flicker(true)
                .with(FireworkEffect.Type.BALL)
                .build()
        );

        fw.setFireworkMeta(fwm);

        // Pretty much setting a state value for the specific firework
        fw.setMetadata("block_break_firework", new FixedMetadataValue(LearningPlugin.core, "block_break_firework"));

        fw.detonate();

        for (int i = 0; i < amount; i++) {
            Firework fw2 = (Firework) world.spawnEntity(location, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }
}
