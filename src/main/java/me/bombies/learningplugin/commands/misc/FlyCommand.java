package me.bombies.learningplugin.commands.misc;

import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.commands.utils.CommandArgs;
import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.entity.Player;

public class FlyCommand extends PlayerCommand {

    public FlyCommand() {
        super("fly");
    }

    @Override
    public void handle(CommandArgs args) {
        final Player player = args.getPlayer();
        if (!player.hasPermission(Permissions.FLY)) {
            player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
            return;
        }

        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(MessageUtils.color("&cYou have disabled fly"));
        } else {
            player.setAllowFlight(true);
            player.sendMessage(MessageUtils.color("&aYou have enabled fly"));
        }
    }
}
