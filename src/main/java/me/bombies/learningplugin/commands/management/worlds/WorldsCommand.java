package me.bombies.learningplugin.commands.management.worlds;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import org.bukkit.entity.Player;

public class WorldsCommand extends PlayerCommand {

    public WorldsCommand() {
        super(
                "worlds",
                new ViewWorldsSubCommand(),
                new TeleportWorldSubCommand()
        );
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final Player player = commandContext.getPlayer();
        if (commandContext.getArgs().isEmpty()) {
            new ViewWorldsSubCommand().displayWorlds(player);
            return true;
        }
        return false;
    }
}
