package me.bombies.learningplugin.commands.misc.holograms;

import me.bombies.learningplugin.utils.PersistentDataHandler;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class HologramEventListener implements Listener {

    @EventHandler
    public void onHologramHit(EntityDamageEvent e) {
        final var entity = e.getEntity();
        if (isHologram(entity))
            e.setCancelled(true);
    }

    @EventHandler
    public void onHologramDeath(EntityDeathEvent e) {
        final var entity = e.getEntity();
        if (!isHologram(entity))
            return;

        final var hologramData = new PersistentDataHandler(entity);
        final var deleted = hologramData.getBoolean("hologram_deleted");
        if (deleted)
            return;

        final var hologramName = hologramData.getString("hologram_name");
        final var hologram = HologramService.getHologram(hologramName);
        if (hologram == null)
            return;

        HologramService.createWorldHologram(
                hologram.getWorld(),
                hologram.getLocation(),
                hologram.getName(),
                hologram.getLines(),
                hologram.getItemMaterial()
        );
    }

    private boolean isHologram(Entity entity) {
        final var entityData = new PersistentDataHandler(entity);
        return entityData.getBoolean("hologram");
    }
}
