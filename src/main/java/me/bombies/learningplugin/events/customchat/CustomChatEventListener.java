package me.bombies.learningplugin.events.customchat;

import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CustomChatEventListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setFormat(MessageUtils.color("&e%s &8»&r %s"));
    }
}
