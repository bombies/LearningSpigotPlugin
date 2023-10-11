package me.bombies.learningplugin.commands.misc.holograms;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerSubCommand;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;

public class HologramCreateSubCommand extends PlayerSubCommand {
    protected HologramCreateSubCommand() {
        super("create", "c", "make", "place");
    }

    @Override
    protected void handle(CommandContext context) {
        final var player = context.getPlayer();
        final var args = context.getArgs();

        if (args.isEmpty()) {
            player.sendMessage(DefaultMessage.USAGE("hologram create <name>"));
            return;
        }

        final var hologramName = args.first();
        final var playerLocation = player.getLocation();
        String content = null;

        if (args.length() > 1)
            content = String.join(" ",args.all().subList(1, args.length()));

        try {
            HologramService.addHologram(hologramName, playerLocation, content);
            HologramService.createWorldHologram(player, hologramName, content);
            player.sendMessage(MessageUtils.color("&aHologram created!"));
        } catch (IllegalArgumentException e) {
            player.sendMessage(MessageUtils.color("&cThere is already a hologram with that name!"));
        }

    }
}
