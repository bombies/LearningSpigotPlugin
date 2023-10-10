package me.bombies.learningplugin.commands.management.spawn;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.config.Config;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnCommand extends PlayerCommand {

    public SpawnCommand() {
        super("spawn");
    }

    @Override
    public void handle(CommandContext commandContext) {
        final Player player = commandContext.getPlayer();
        final Location spawnLocation = Config.spawnLocation.getSpawnLocation();
        if (spawnLocation != null)
            player.teleport(spawnLocation);
        else player.teleport(player.getWorld().getSpawnLocation());
        player.sendMessage(MessageUtils.color("&aTeleported to spawn!"));
    }
}
