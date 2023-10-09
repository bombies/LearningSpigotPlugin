package me.bombies.learningplugin.commands.misc;

import me.bombies.learningplugin.commands.utils.AbstractCommand;
import me.bombies.learningplugin.commands.utils.CommandArgs;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.entity.Player;

public class SuicideCommand extends AbstractCommand {
    public SuicideCommand() {
        super("suicide");
    }

    @Override
    public void handle(CommandArgs args) {
        final Player player = args.getPlayer();
        player.setHealth(0D);
        player.sendMessage(MessageUtils.color("&bYou've committed suicide..."));
    }
}
