package me.bombies.learningplugin.commands.misc;

import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.entity.Player;

public class SuicideCommand extends PlayerCommand {
    public SuicideCommand() {
        super("suicide");
    }

    @Override
    public boolean handle(CommandContext args) {
        final Player player = args.getPlayer();
        player.setHealth(0D);
        player.sendMessage(MessageUtils.color("&bYou've committed suicide..."));
        return true;
    }
}
