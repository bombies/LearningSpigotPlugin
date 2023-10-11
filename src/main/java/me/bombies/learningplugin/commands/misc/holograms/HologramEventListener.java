package me.bombies.learningplugin.commands.misc.holograms;

import me.bombies.learningplugin.utils.PersistentDataHandler;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class HologramEventListener implements Listener {

    @EventHandler
    public void onHologramHit(EntityDamageEvent e) {
        final var entity = e.getEntity();
        if (!(entity instanceof ArmorStand armorStand))
            return;

        final var armorStandData = new PersistentDataHandler(armorStand);
        if (armorStandData.has("hologram"))
            e.setCancelled(true);
    }
}
