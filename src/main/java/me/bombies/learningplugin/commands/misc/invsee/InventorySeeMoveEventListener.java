package me.bombies.learningplugin.commands.misc.invsee;

import me.bombies.learningplugin.utils.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventorySeeMoveEventListener implements Listener {

    @EventHandler
    public void onItemMove(InventoryClickEvent e) {
        final var inventory = e.getClickedInventory();
        final var player = (Player) e.getWhoClicked();

        if (inventory == null)
            return;

        final var holder = (Player) inventory.getHolder();
        if (holder == null)
            return;

        if (player.getUniqueId() != holder.getUniqueId() && !player.hasPermission(Permissions.INV_SEE_MOVE))
            e.setCancelled(true);
    }
}
