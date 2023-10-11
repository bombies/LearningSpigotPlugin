package me.bombies.learningplugin.commands.teleportbow;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;

public class TeleportBowCommand extends PlayerCommand {

    public TeleportBowCommand() {
        super("teleportbow");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        final var args = commandContext.getArgs();
    }


}
