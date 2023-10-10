package me.bombies.learningplugin.commands.misc.nick;

import me.bombies.learningplugin.commands.utils.CommandArgs;
import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NickCommand extends PlayerCommand {

    public NickCommand() {
        super("nick");
    }

    @Override
    public void handle(CommandContext commandContext) {
        final Player player = commandContext.getPlayer();
        final CommandArgs args = commandContext.getArgs();

        if (args.isEmpty()) {
            if (!player.hasPermission(Permissions.NICK)) {
                player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
                return;
            }

            player.sendMessage(DefaultMessage.USAGE("nick " + (player.hasPermission(Permissions.NICK_OTHERS) ? "[target] " : "") + "<nickname>"));
        } else if (args.length() == 1) {
            if (!player.hasPermission(Permissions.NICK)) {
                player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
                return;
            }

            if (args.first().equalsIgnoreCase("clear"))
                handleSelfNickClear(player);
            else handleSelfNickName(player, args.first());
        } else {
            if (!player.hasPermission(Permissions.NICK_OTHERS)) {
                player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
                return;
            }

            handleOthersNick(player, args.first(), args.get(1));
        }
    }

    private void handleSelfNickName(Player player, String newNickName) {
        if (setNickName(player, newNickName))
            player.sendMessage(MessageUtils.color("&a Your nickname has been set to: &r" + MessageUtils.color(newNickName)));
    }

    private void handleSelfNickClear(Player player) {
        clearNickName(player);
        player.sendMessage(MessageUtils.color("&a Your nickname has been cleared."));
    }

    private void handleOthersNick(Player sender, String targetName, String newNickName) {
        final Player target = Bukkit.getPlayerExact(targetName);
        if (target == null) {
            sender.sendMessage(DefaultMessage.PLAYER_NOT_FOUND(targetName));
            return;
        }

        if (newNickName.equalsIgnoreCase("clear")) {
            clearNickName(target);
            sender.sendMessage(MessageUtils.color("&a " + target.getName() + "'s nickname has been cleared."));
            return;
        }

        final String coloredNickName = MessageUtils.color(newNickName);
        if (setNickName(target, coloredNickName))
            sender.sendMessage(MessageUtils.color("&a " + target.getName() + "'s nickname has been set to: &r" + coloredNickName));
    }

    /**
     * Sets the nickname of the player and saves it to the cache.
     *
     * @param player   The player to update
     * @param nickName The new nickname for the player. Can include colour codes.
     * @return True if the nickname was set successfully, false otherwise.
     */
    private boolean setNickName(Player player, String nickName) {
        final String strippedNickname = MessageUtils.stripColor(nickName);
        if (strippedNickname.length() < 3 || strippedNickname.length() > 16) {
            player.sendMessage(MessageUtils.color("&cNickname must be between 3 and 16 characters long!"));
            return false;
        }

        final String coloredNickName = MessageUtils.color(nickName);
        player.setDisplayName(coloredNickName);
        NickService.setNickname(player.getUniqueId(), coloredNickName);
        return true;
    }

    private void clearNickName(Player player) {
        player.setDisplayName(player.getName());
        NickService.removeNickname(player.getUniqueId());
    }
}
