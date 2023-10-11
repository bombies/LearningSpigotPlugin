package me.bombies.learningplugin.commands.teleportbow;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.items.builders.ItemBuilder;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeleportBowCommand extends PlayerCommand {


    public TeleportBowCommand() {
        super("teleportbow");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        final var args = commandContext.getArgs();

        if (args.isEmpty()) {
            if (!player.hasPermission(Permissions.TELEPORT_BOW)) {
                player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
                return true;
            }

            giveBow(player);
            player.sendMessage(MessageUtils.color("&aYou have received a teleport bow!"));
        } else {
            if (!player.hasPermission(Permissions.TELEPORT_BOW_OTHERS)) {
                player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
                return true;
            }

            final var targetName = args.first();
            final var target = Bukkit.getPlayerExact(targetName);

            if (target == null) {
                player.sendMessage(DefaultMessage.PLAYER_NOT_FOUND(targetName));
                return true;
            }

            giveBow(target);
            target.sendMessage(MessageUtils.color("&aYou have received a teleport bow!"));
            player.sendMessage(MessageUtils.color("&aYou have given a teleport bow to " + targetName));
        }

        return true;
    }

    private void giveBow(Player player) {
        player.getInventory().addItem(createBow(player));
    }

    private ItemStack createBow(Player player) {
        return ItemBuilder.start()
                .material(Material.BOW)
                .name("&b&lTeleport Bow")
                .lore("&7Shoot to teleport to where the arrow lands")
                .customId(TeleportBowMetaData.ID)
                .unbreakable()
                .enchant(Enchantment.ARROW_DAMAGE, 1)
                .hideUnbreakable()
                .hideEnchants()
                .hideAttributes()
                .metaData(TeleportBowMetaData.OWNER, player.getUniqueId().toString())
                .build();
    }
}
