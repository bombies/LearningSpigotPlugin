package me.bombies.learningplugin.commands.menu;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
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

        // Creating the inventory
        final var menuInventory = Bukkit.createInventory(player, 54, MessageUtils.color("&4&lCustom Menu"));


        // Creating the item
        final var item = new ItemStack(Material.DIAMOND_SWORD);
        final var itemMeta = item.getItemMeta();
        assert itemMeta != null;

        itemMeta.setDisplayName(MessageUtils.color("&6&lThis is a button"));
        itemMeta.setLore(List.of(
                MessageUtils.color("&8&m-------------------"),
                MessageUtils.color("&eThis item literally does nothing"),
                MessageUtils.color("&8&m-------------------")
        ));

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.setUnbreakable(true);
        itemMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        item.setItemMeta(itemMeta);


        // Could use Inventory#addItem as well.
        menuInventory.setItem(4, item);
        player.openInventory(menuInventory);
        return true;
    }
}
