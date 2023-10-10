package me.bombies.learningplugin.commands.menu;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.menus.MenuBuilder;
import me.bombies.learningplugin.utils.menus.MenuSize;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MenuCommand extends PlayerCommand {

    public MenuCommand() {
        super("menu", 10);
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        final var menu = MenuBuilder.start()
                .owner(player)
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
                )
                .build();

        player.openInventory(menu.toInventory());
        return true;
    }
}
