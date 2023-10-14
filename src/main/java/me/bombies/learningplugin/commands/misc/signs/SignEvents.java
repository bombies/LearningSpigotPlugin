package me.bombies.learningplugin.commands.misc.signs;

import me.bombies.learningplugin.utils.PersistentDataHandler;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignEvents implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        final var sign = (Sign) e.getBlock().getState();
        final var signData = new PersistentDataHandler(sign);

        if (!signData.getBoolean("custom_sign"))
            return;

        final var signOwner = Bukkit.getPlayerExact(signData.getString("custom_sign_owner"));
        if (signOwner == null)
            return;

        signOwner.sendMessage(MessageUtils.color("&aYour sign at "+ sign.getLocation() +" has been updated!"));
    }
}
