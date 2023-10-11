package me.bombies.learningplugin.utils.menus;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.utils.PersistentDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class AbstractMenu implements Listener {

    private Inventory inventory;
    private Menu menu;

    protected AbstractMenu() {
        Bukkit.getPluginManager().registerEvents(this, LearningPlugin.core);
    }

    private void build(MenuBuilder menuBuilder) {
        menu = menuBuilder.build();
        inventory = menu.toInventory();
    }

    private boolean isBuilt() {
        return menu != null && inventory != null;
    }

    protected abstract MenuBuilder create(MenuBuilder menuBuilder);

    public Inventory getInventory() {
        if (!isBuilt())
            build(create(MenuBuilder.start()));
        return inventory;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if (!isBuilt())
            build(create(MenuBuilder.start()));

        final var clickedInventory = e.getClickedInventory();
        if (clickedInventory == null)
            return;

        if (!e.getClickedInventory().equals(inventory))
            return;

        final var player = (Player) e.getWhoClicked();
        final var item = e.getCurrentItem();
        if (item == null) {
            e.setCancelled(true);
            return;
        }

        final var itemMeta = item.getItemMeta();
        final var itemDataContainer = new PersistentDataHandler(itemMeta);
        final var itemCustomId = itemDataContainer.getString(MenuItem.MENU_ITEM_KEY);
        final var menuItem = menu.getItems()
                .stream()
                .filter(mi -> mi.getCustomId().equals(itemCustomId))
                .findFirst()
                .orElse(null);

        if (menuItem == null) {
            e.setCancelled(true);
            return;
        }

        onItemClick(menuItem, player);
        e.setCancelled(true);
    }

    protected void onItemClick(MenuItem item, Player player) {}
}
