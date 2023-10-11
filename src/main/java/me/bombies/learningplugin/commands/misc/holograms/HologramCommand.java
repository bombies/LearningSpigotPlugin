package me.bombies.learningplugin.commands.misc.holograms;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import me.bombies.learningplugin.utils.Permissions;
import me.bombies.learningplugin.utils.messages.DefaultMessage;
import me.bombies.learningplugin.utils.messages.MessageUtils;

public class HologramCommand extends PlayerCommand {

    public HologramCommand() {
        super("holograms",
                new HologramCreateSubCommand(),
                new HologramEditSubCommand()
        );
    }

    @Override
    public boolean handle(CommandContext commandContext) {
        final var player = commandContext.getPlayer();
        if (!player.hasPermission(Permissions.HOLOGRAMS)) {
            player.sendMessage(DefaultMessage.INSUFFICIENT_PERMS);
            return true;
        }

        final var args = commandContext.getArgs();
        if (args.isEmpty()) {
            final var holograms = HologramService.getHolograms();
            if (holograms.isEmpty()) {
                player.sendMessage(MessageUtils.color("&cThere are no holograms!"));
                return true;
            }

            final var stringBuilder = new StringBuilder();
            stringBuilder.append("&8&m---------------------------------------------&r\n");
            for (int i = 0; i < holograms.size(); i++) {
                final var hologram = holograms.get(i);
                stringBuilder.append(MessageUtils.color("&7- &6&l" + (i + 1) + ". &e" + hologram.getName() + " &7(" + "%f, %f, %f,".formatted(hologram.getCoordinates().getX(), hologram.getCoordinates().getY(), hologram.getCoordinates().getZ()) + ") &r\n"));
            }
            stringBuilder.append("&8&m---------------------------------------------&r");

            player.sendMessage(MessageUtils.color(stringBuilder.toString()));
            return true;
        } else return false;
    }
}
