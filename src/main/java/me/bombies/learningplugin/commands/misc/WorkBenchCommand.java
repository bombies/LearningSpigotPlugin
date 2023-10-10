package me.bombies.learningplugin.commands.misc;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import org.bukkit.entity.Player;

public class WorkBenchCommand extends PlayerCommand {

    public WorkBenchCommand() {
        super("workbench");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final Player player = commandContext.getPlayer();
        player.openWorkbench(null, true);
        return true;
    }
}
