package me.bombies.learningplugin.commands.misc.fly;

import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.entity.Player;

public class FlyService {

    public static boolean toggleFly(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            return false;
        } else {
            player.setAllowFlight(true);
            return true;
        }
    }
}
