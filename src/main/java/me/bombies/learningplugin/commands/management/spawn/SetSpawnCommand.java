package me.bombies.learningplugin.commands.management.spawn;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.config.Config;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends PlayerCommand {

    public SetSpawnCommand() {
        super("setspawn");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final Player player = commandContext.getPlayer();
        if (!player.hasPermission(Permissions.SET_SPAWN)) {
            player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
            return true;
        }

        final Location currentLocation = player.getLocation();
        Config.spawnLocation.setSpawnLocation(currentLocation);
        currentLocation.getWorld().setSpawnLocation(currentLocation);

        player.sendMessage(MessageUtils.color("&aYou have successfully set the spawn location!"));
        return true;
    }
}
