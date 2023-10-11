package me.bombies.learningplugin.commands.menu;

import me.bombies.learningplugin.utils.menus.AbstractMenu;
import me.bombies.learningplugin.utils.menus.MenuBuilder;
import me.bombies.learningplugin.utils.menus.MenuItem;
import me.bombies.learningplugin.utils.menus.MenuSize;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomMenu extends AbstractMenu {
    @Override
    protected MenuBuilder create(MenuBuilder menuBuilder) {
        return menuBuilder
                .title("&4&lCustom Menu")
                .size(MenuSize.FORTY_FIVE)
                .placeHolders(true)
                .placeHolderMaterial(Material.GREEN_STAINED_GLASS_PANE)
                .addItem(builder -> builder.name("&a&lMy Custom Item")
                        .customId("custom_item")
                        .lore(
                                "&8&m-------------------------------------------------",
                                "&aThis does nothing, but it was created using",
                                "&a a really cool custom menu system!",
                                "&8&m-------------------------------------------------"
                        )
                        .material(Material.DIAMOND)
                        .slot(3)
                        .isEnchanted(true)
                        .amount(5)
                )

                .addItem(builder -> builder.name("&b&lMy Custom Item 2")
                        .customId("custom_item_2")
                        .lore(
                                "&8&m-------------------------------------------------",
                                "&bNow this does something!",
                                "&8&m-------------------------------------------------"
                        )
                        .material(Material.DIAMOND)
                        .slot(5)
                        .isEnchanted(true)
                        .amount(5)
                );
    }

    @Override
    protected void onItemClick(MenuItem item, Player player) {
        switch (item.getCustomId()) {
            case "custom_item" -> {
                player.sendMessage("&eI told you it does nothing...");
                player.closeInventory();
            }
            case "custom_item_2" -> {
                final var playerInventory = player.getInventory();
                playerInventory.addItem(new ItemStack(Material.DIAMOND, 5));
                player.sendMessage("&aI told you it does something!");
                player.closeInventory();
            }
        }
    }
}
