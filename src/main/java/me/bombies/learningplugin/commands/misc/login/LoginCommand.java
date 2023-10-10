package me.bombies.learningplugin.commands.misc.login;

import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.entity.Player;

public class LoginCommand extends PlayerCommand {

    public LoginCommand() {
        super("login");
    }

    @Override
    public boolean handle(CommandContext args) {
        final Player player = args.getPlayer();
        if (LoginService.playerIsLoggedIn(player)) {
            player.sendMessage(MessageUtils.color("&4&lHold on! &cYou are already logged in!"));
            return true;
        }
        LoginService.login(player);
        player.sendMessage(MessageUtils.color("&aYou have logged in!"));
        return true;
    }
}
