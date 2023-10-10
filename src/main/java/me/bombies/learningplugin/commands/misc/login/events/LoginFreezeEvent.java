package me.bombies.learningplugin.commands.misc.login.events;

import me.bombies.learningplugin.commands.misc.login.LoginService;
import me.bombies.learningplugin.events.EventListenerAdapter;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class LoginFreezeEvent implements EventListenerAdapter {

    @Override
    public void onPlayerMove(PlayerMoveEvent e) {
        final Player player = e.getPlayer();
        if (LoginService.playerIsLoggedIn(player))
            return;

        e.setCancelled(true);
        player.sendMessage(MessageUtils.color("&4&lHold on! &cYou need to login first!"));
    }
}
