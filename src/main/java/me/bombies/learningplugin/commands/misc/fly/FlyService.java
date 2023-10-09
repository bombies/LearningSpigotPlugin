package me.bombies.learningplugin.commands.misc.fly;

import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.entity.Player;

public class FlyService {

    public static void toggleFly(Player player, boolean isSelf) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(MessageUtils.color("&cYou have disabled fly" + (!isSelf ? " for &e" + player.getName() : "")));
        } else {
            player.setAllowFlight(true);
            player.sendMessage(MessageUtils.color("&aYou have enabled fly" + (!isSelf ? " for &e" + player.getName() : "")));
        }
    }
}
