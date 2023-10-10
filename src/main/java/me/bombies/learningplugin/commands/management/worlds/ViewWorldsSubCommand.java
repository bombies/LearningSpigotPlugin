package me.bombies.learningplugin.commands.management.worlds;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerSubCommand;
import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class ViewWorldsSubCommand extends PlayerSubCommand {

    protected ViewWorldsSubCommand() {
        super("view");
    }

    @Override
    protected void handle(CommandContext context) {
        displayWorlds(context.getPlayer());
    }

    void displayWorlds(Player player) {
        if (!player.hasPermission(Permissions.VIEW_WORLDS)) {
            player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
            return;
        }

        final Server server = LearningPlugin.server;
        final List<World> worlds = server.getWorlds();

        final StringBuilder builder = new StringBuilder();
        builder.append(MessageUtils.color("&8&m-------------------------&r\n"));
        for (final World world : worlds)
            builder.append(MessageUtils.color("&7- &b" + world.getName() + " &e&l&o(" + world.getEnvironment().toString().replaceAll("_", " ") + ")&r\n"));
        builder.append(MessageUtils.color("&8&m-------------------------"));
        player.sendMessage(builder.toString());
    }
}
