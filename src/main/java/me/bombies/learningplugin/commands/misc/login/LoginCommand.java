package me.bombies.learningplugin.commands.misc.login;

import me.bombies.learningplugin.commands.AbstractCommand;
import me.bombies.learningplugin.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class LoginCommand extends AbstractCommand {


    public LoginCommand() {
        super("login");
    }

    @Override
    public void handle(Player player, Command command, String label, String[] args) {
        if (LoginService.playerIsLoggedIn(player)) {
            player.sendMessage(MessageUtils.color("&4&lHold on! &cYou are already logged in!"));
            return;
        }
        LoginService.login(player);
        player.sendMessage(MessageUtils.color("&aYou have logged in!"));
    }
}
