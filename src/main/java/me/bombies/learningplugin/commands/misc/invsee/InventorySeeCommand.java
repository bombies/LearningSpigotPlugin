package me.bombies.learningplugin.commands.misc.invsee;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import org.bukkit.Bukkit;

public class InventorySeeCommand extends PlayerCommand {

    public InventorySeeCommand() {
        super("invsee");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        final var args = commandContext.getArgs();

        if (!player.hasPermission(Permissions.INV_SEE)) {
            player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
            return true;
        }

        if (args.isEmpty()) {
            player.sendMessage(DefaultMessage.USAGE("invsee <player>"));
            return true;
        }

        final var target = Bukkit.getPlayerExact(args.first());
        if (target == null) {
            player.sendMessage(DefaultMessage.PLAYER_NOT_FOUND(args.first()));
            return true;
        }

        player.openInventory(target.getInventory());
        return true;
    }
}
