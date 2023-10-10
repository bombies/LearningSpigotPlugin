package me.bombies.learningplugin.commands.menu;

import me.bombies.learningplugin.utils.menus.MenuItem;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

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

        final var itemMeta = item.getItemMeta();
        if (itemMeta == null)
            return;

        final var itemDataContainer = itemMeta.getPersistentDataContainer();
        if (!itemDataContainer.has(MenuItem.MENU_ITEM_KEY, PersistentDataType.STRING))
            return;

        final var itemID = itemDataContainer.get(MenuItem.MENU_ITEM_KEY, PersistentDataType.STRING);

        if (itemID == null)
            return;

        switch (itemID) {
            case "custom_item" -> {
                player.sendMessage(MessageUtils.color("&eI told you it does nothing..."));
                player.closeInventory();
            }
            case "custom_item_2" -> {
                final var playerInventory = player.getInventory();
                playerInventory.addItem(new ItemStack(Material.DIAMOND, 1));
                player.sendMessage(MessageUtils.color("&aI told you it does something!"));
                player.closeInventory();
            }
        }
    }
}
