package me.bombies.learningplugin.commands.misc.holograms;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerSubCommand;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.entity.Player;

public class HologramEditSubCommand extends PlayerSubCommand {

    protected HologramEditSubCommand() {
        super("edit", "e", "alter");
    }

    @Override
    protected void handle(CommandContext context) {
        final var player = context.getPlayer();
        final var args = context.getArgs();
        final var usages = MessageUtils.color("""
                &cUsages:
                /holograms edit <name> addline <line>
                /holograms edit <name> editline <lineID> <line>
                /holograms edit <name> removeline <lineID>
                """
        );


        if (args.length() <= 2) {
            player.sendMessage(usages);
        }

        final var hologramName = args.first();
        final var subCommand = args.get(1);

        final var hologram = HologramService.getHologram(hologramName);
        if (hologram == null) {
            player.sendMessage(MessageUtils.color("&cThere is no hologram with that name!"));
            return;
        }

        switch (subCommand.toLowerCase()) {
            case "addline", "add", "al" -> {
                if (args.length() <= 3) {
                    player.sendMessage(MessageUtils.color("&cYou must provide a line of text to add to the hologram!"));
                    return;
                }

                final var line = String.join(" ", args.all().subList(2, args.length()));
                handleLineAdd(player, hologram, line);
            }
            case "editline", "el", "edit" -> {

            }
            case "removeline", "remove", "delete", "rl", "dl" -> {

            }
            default -> player.sendMessage(usages);
        }
    }

    private void handleLineAdd(Player player, Hologram hologram, String line) {
        final var armorStands = HologramService.findWorldHologram(hologram);
        if (armorStands.isEmpty()) {
            HologramService.removeHologram(hologram.getName());
            return;
        }


        HologramService.createWorldHologram(
                hologram.getWorld(),
                hologram.getLocation().add(0, (-0.30) * hologram.getLines().size(), 0),
                hologram.getName(),
                MessageUtils.color(line)
        );

        hologram.addLine(line);
        HologramService.updateHologram(hologram);
        player.sendMessage(MessageUtils.color("&aSuccessfully added that line!"));
    }
}
