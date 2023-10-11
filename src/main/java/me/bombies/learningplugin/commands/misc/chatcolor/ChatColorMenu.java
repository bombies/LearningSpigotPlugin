package me.bombies.learningplugin.commands.misc.chatcolor;

import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.menus.*;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChatColorMenu extends AbstractMenu {

    final Player player;

    public ChatColorMenu(Player player) {
        this.player = player;
    }

    @Override
    protected MenuBuilder create(MenuBuilder menuBuilder) {
        final var builder = menuBuilder.title("Select a chat color")
                .size(MenuSize.THIRTY_SIX)
                .placeHolders(true);

        if (player.hasPermission(Permissions.CHAT_COLOR_RED))
            builder.addItem(itemBuilder -> itemBuilder.name("&c&lRed")
                    .slot(9)
                    .customId("chatcolor_red")
                    .material(Material.PINK_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_DARK_RED))
            builder.addItem(itemBuilder -> itemBuilder.name("&4&lRed")
                    .slot(10)
                    .customId("chatcolor_darkred")
                    .material(Material.RED_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_GOLD))
            builder.addItem(itemBuilder -> itemBuilder.name("&6&lGold")
                    .slot(11)
                    .customId("chatcolor_gold")
                    .material(Material.ORANGE_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_YELLOW))
            builder.addItem(itemBuilder -> itemBuilder.name("&e&lYellow")
                    .slot(12)
                    .customId("chatcolor_yellow")
                    .material(Material.YELLOW_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_DARK_GREEN))
            builder.addItem(itemBuilder -> itemBuilder.name("&2&lDark Green")
                    .slot(13)
                    .customId("chatcolor_darkgreen")
                    .material(Material.GREEN_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_GREEN))
            builder.addItem(itemBuilder -> itemBuilder.name("&a&lGreen")
                    .slot(14)
                    .customId("chatcolor_green")
                    .material(Material.LIME_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_AQUA))
            builder.addItem(itemBuilder -> itemBuilder.name("&b&lAqua")
                    .slot(15)
                    .customId("chatcolor_aqua")
                    .material(Material.LIGHT_BLUE_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_DARK_AQUA))
            builder.addItem(itemBuilder -> itemBuilder.name("&3&lDark Aqua")
                    .slot(16)
                    .customId("chatcolor_darkaqua")
                    .material(Material.CYAN_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_DARK_BLUE))
            builder.addItem(itemBuilder -> itemBuilder.name("&1&lDark Blue")
                    .slot(17)
                    .customId("chatcolor_darkblue")
                    .material(Material.BLUE_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_BLUE))
            builder.addItem(itemBuilder -> itemBuilder.name("&9&lBlue")
                    .slot(18)
                    .customId("chatcolor_blue")
                    .material(Material.LIGHT_BLUE_CONCRETE)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_DARK_PURPLE))
            builder.addItem(itemBuilder -> itemBuilder.name("&5&lDark Purple")
                    .slot(19)
                    .customId("chatcolor_darkpurple")
                    .material(Material.PURPLE_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_PURPLE))
            builder.addItem(itemBuilder -> itemBuilder.name("&d&lLight Purple")
                    .slot(20)
                    .customId("chatcolor_purple")
                    .material(Material.MAGENTA_WOOL)
                    .isEnchanted(true)
            );

        if (player.hasPermission(Permissions.CHAT_COLOR_BLACK))
            builder.addItem(itemBuilder -> itemBuilder.name("&0&lBlack")
                    .slot(21)
                    .customId("chatcolor_black")
                    .material(Material.BLACK_WOOL)
                    .isEnchanted(true)
            );

        builder.addItem(itemBuilder -> itemBuilder.name("&c&lReset")
                .slot(35)
                .customId("chatcolor_reset")
                .material(Material.RED_DYE)
                .isEnchanted(true)
        );

        return builder;
    }

    @Override
    protected void onItemClick(MenuItem item, Player player) {
        final var customId = item.getCustomId();
        if (!customId.startsWith("chatcolor_"))
            return;

        ChatColor selectedColor;
        switch (customId.replaceAll("chatcolor_", "")) {
            case "red" -> {
                selectedColor = ChatColor.RED;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &c&lRED"));
            }
            case "darkred" -> {
                selectedColor = ChatColor.DARK_RED;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &4&lDARK RED"));
            }
            case "gold" -> {
                selectedColor = ChatColor.GOLD;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &6&lGOLD"));
            }
            case "yellow" -> {
                selectedColor = ChatColor.YELLOW;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &e&lYELLOW"));
            }
            case "darkgreen" -> {
                selectedColor = ChatColor.DARK_GREEN;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &2&lDARK GREEN"));
            }
            case "green" -> {
                selectedColor = ChatColor.GREEN;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &a&lGREEN"));
            }
            case "aqua" -> {
                selectedColor = ChatColor.AQUA;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &b&lAQUA"));
            }
            case "darkaqua" -> {
                selectedColor = ChatColor.DARK_AQUA;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &3&lDARK AQUA"));
            }
            case "darkblue" -> {
                selectedColor = ChatColor.DARK_BLUE;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &1&lDARK BLUE"));
            }
            case "blue" -> {
                selectedColor = ChatColor.BLUE;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &9&lBLUE"));
            }
            case "purple" -> {
                selectedColor = ChatColor.LIGHT_PURPLE;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &d&lLIGHT PURPLE"));
            }
            case "darkpurple" -> {
                selectedColor = ChatColor.DARK_PURPLE;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &5&lDARK PURPLE"));
            }
            case "black" -> {
                selectedColor = ChatColor.BLACK;
                player.sendMessage(MessageUtils.color("&aYou have updated your chat color to &0&lBLACK"));
            }
            case "reset" -> {
                ChatColorService.removePlayerColor(player);
                player.sendMessage(MessageUtils.color("&aYou have reset your chat color!"));
                player.closeInventory();
                return;
            }
            default -> selectedColor = ChatColor.RESET;
        }

        ChatColorService.setPlayerColor(player, selectedColor);
        player.closeInventory();
    }
}
