package me.bombies.learningplugin.commands.misc;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.messages.MessageUtils;

public class PingCommand extends PlayerCommand {
    public PingCommand() {
        super("ping");
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        player.sendMessage(MessageUtils.color("&aYour ping is: %player_ping% ms".replaceAll("%player_ping%", String.valueOf(player.getPing()))));
        return true;
    }
}
