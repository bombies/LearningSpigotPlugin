package me.bombies.learningplugin.commands.misc;

import me.bombies.learningplugin.LearningPlugin;
import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.events.EventManager;
import me.bombies.learningplugin.events.custom.GameEndCustomEvent;
import org.bukkit.Bukkit;

import java.util.UUID;

public class GameEndCommand extends PlayerCommand {
    public GameEndCommand() {
        super("gameend");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        final var args = commandContext.getArgs();

        if (args.isEmpty()) {
            player.sendMessage("Provide args");
            return true;
        }

        final var playerUUID = UUID.fromString(args.first());
        final var targetPlayer = LearningPlugin.server.getOfflinePlayer(playerUUID);

        EventManager.call(new GameEndCustomEvent(player, targetPlayer.getPlayer(), 10));
        return true;
    }
}
