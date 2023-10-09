package me.bombies.learningplugin.commands.misc.login;

import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.commands.utils.CommandArgs;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.entity.Player;

public class LoginCommand extends PlayerCommand {

    public LoginCommand() {
        super("login");
    }

    @Override
    public void handle(CommandArgs args) {
        final Player player = args.getPlayer();
        if (LoginService.playerIsLoggedIn(player)) {
            player.sendMessage(MessageUtils.color("&4&lHold on! &cYou are already logged in!"));
            return;
        }
        LoginService.login(player);
        player.sendMessage(MessageUtils.color("&aYou have logged in!"));
    }
}
