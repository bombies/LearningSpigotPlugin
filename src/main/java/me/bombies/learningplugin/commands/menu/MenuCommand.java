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
        final var menu = new CustomMenu();
        player.openInventory(menu.getInventory());
        return true;
    }
}
