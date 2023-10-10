package me.bombies.learningplugin.commands.menu;

import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuEventListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equalsIgnoreCase(MessageUtils.color("&4&lCustom Menu")))
            return;
        e.setCancelled(true);

        // Handle specific item click
        final var player = (Player) e.getClickedInventory().getHolder();
        if (player == null)
            return;

        final var item = e.getCurrentItem();
        if (item == null)
            return;

        switch (item.getType()) {
            case DIAMOND_SWORD ->  {
                player.sendMessage(MessageUtils.color("&eI told you it does nothing..."));
                player.closeInventory();
            }
        }
    }
}
