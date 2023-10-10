package me.bombies.learningplugin.commands.misc.fly;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.commands.utils.CommandArgs;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FlyCommand extends PlayerCommand {

    public FlyCommand() {
        super("fly");
    }

    @Override
    public boolean handle(CommandContext context) {
        final Player player = context.getPlayer();
        final CommandArgs args = context.getArgs();

        if (args.isEmpty()) {
            if (!player.hasPermission(Permissions.FLY)) {
                player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
                return true;
            }

            if (FlyService.toggleFly(player))
                player.sendMessage(MessageUtils.color("&aFly mode has been enabled for you!"));
            else player.sendMessage(MessageUtils.color("&cFly mode has been disabled for you!"));
        } else {
            if (!player.hasPermission(Permissions.FLY_OTHERS)) {
                player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
                return true;
            }

            final Player target = Bukkit.getPlayerExact(args.first());
            if (target == null) {
                player.sendMessage(MessageUtils.color("&c There was no player found with the name" + args.first()));
                return true;
            }

            if (FlyService.toggleFly(target)) {
                player.sendMessage(MessageUtils.color("&aYou have enabled fly for &e" + target.getName()));
                target.sendMessage(MessageUtils.color("&aFly mode has been enabled for you!"));
            } else {
                player.sendMessage(MessageUtils.color("&cYou have disabled fly for &e" + target.getName()));
                target.sendMessage(MessageUtils.color("&cFly mode has been disabled for you!"));
            }
        }

        return true;
    }
}
