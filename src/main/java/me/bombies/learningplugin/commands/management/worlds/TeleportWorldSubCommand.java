package me.bombies.learningplugin.commands.management.worlds;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerSubCommand;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class TeleportWorldSubCommand extends PlayerSubCommand {

    protected TeleportWorldSubCommand() {
        super("teleport", "tp", "tele");
    }

    @Override
    protected void handle(CommandContext context) {
        final Player player = context.getPlayer();
        final String worldName = context.getArgs().first();
        if (worldName == null) {
            player.sendMessage(DefaultMessage.USAGE("worlds teleport <world>"));
            return;
        }

        final World world = Bukkit.getWorld(worldName.toLowerCase());

        if (world == null) {
            player.sendMessage(MessageUtils.color("&cThat world doesn't exist."));
            return;
        }

        player.teleport(world.getSpawnLocation());
        player.sendMessage(MessageUtils.color("&aTeleported to " + worldName + "!"));
    }
}
