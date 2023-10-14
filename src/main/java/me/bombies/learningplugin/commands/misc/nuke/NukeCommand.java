package me.bombies.learningplugin.commands.misc.nuke;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.items.builders.ItemBuilder;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class NukeCommand extends PlayerCommand {

    public NukeCommand() {
        super("nuke");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        final var args = commandContext.getArgs();
        final int amount;
        try {
           amount = args.isEmpty() ? 1 : Integer.parseInt(args.get(0));
        } catch (NumberFormatException e) {
            player.sendMessage(MessageUtils.color("&cInvalid amount!"));
            return false;
        }

        final var nukeItem = ItemBuilder.start()
                .customId("nuke")
                .material(Material.TNT)
                .name("&e&l☣☣☣ &a&lNuke &e&l☣☣☣")
                .amount(amount)
                .lore("&7Feeling radioactive?")
                .hideAttributes()
                .hideEnchants()
                .hideUnbreakable()
                .enchant(Enchantment.CHANNELING, 1)
                .build();

        player.getInventory().addItem(nukeItem);
        player.sendMessage(MessageUtils.color("&e&l☣☣☣ &6You have been given a &2&lNuke&6! &e&l☣☣☣"));
        return true;
    }
}
