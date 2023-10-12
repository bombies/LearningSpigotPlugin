package me.bombies.learningplugin.commands.misc.holograms;

import me.bombies.learningplugin.commands.utils.CommandContext;
import me.bombies.learningplugin.commands.utils.PlayerSubCommand;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Material;
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
            return;
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
                if (args.length() < 3) {
                    player.sendMessage(MessageUtils.color("&cYou must provide a line of text to add to the hologram!"));
                    return;
                }

                final var line = String.join(" ", args.all().subList(2, args.length()));
                handleLineAdd(player, hologram, line);
            }

            case "editline", "el", "edit" -> {
                if (args.length() < 4) {
                    player.sendMessage(MessageUtils.color("&cYou must provide a line number and new content!"));
                    return;
                }

                try {
                    final var lineID = Integer.parseInt(args.get(2));
                    if (lineID < 0 || lineID > hologram.getLines().size()) {
                        player.sendMessage(MessageUtils.color("&cYou must provide a valid line number to edit!"));
                        return;
                    }

                    final var newLine = String.join(" ", args.all().subList(3, args.length()));
                    handleLineEdit(player, hologram, lineID, newLine);
                } catch (NumberFormatException e) {
                    player.sendMessage(MessageUtils.color("&cYou must provide a valid line number to edit!"));
                }
            }

            case "removeline", "remove", "delete", "rl", "dl" -> {
                if (args.length() < 3) {
                    player.sendMessage(MessageUtils.color("&cYou must provide a line number to remove from the hologram!"));
                    return;
                }

                try {
                    final var lineID = Integer.parseInt(args.get(2));
                    if (lineID < 0 || lineID > hologram.getLines().size()) {
                        player.sendMessage(MessageUtils.color("&cYou must provide a valid line number to remove from the hologram!"));
                        return;
                    }

                    handleLineRemove(player, hologram, lineID);
                } catch (NumberFormatException e) {
                    player.sendMessage(MessageUtils.color("&cYou must provide a valid line number to remove from the hologram!"));
                }
            }

            case "setitem" -> {
                if (args.length() < 3) {
                    player.sendMessage(MessageUtils.color("&cYou must provide an item to set!"));
                    return;
                }

                final var itemName = args.get(2);
                final var itemMaterial = Material.matchMaterial(itemName);
                if (itemMaterial == null) {
                    player.sendMessage(MessageUtils.color("&cThat is an invalid item!"));
                    return;
                }

                hologram.editItem(itemMaterial);
                HologramService.updateHologram(hologram);
                HologramService.updateWorldHologram(hologram);
                player.sendMessage(MessageUtils.color("&aSuccessfully set the item!"));
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

    private void handleLineRemove(Player player, Hologram hologram, int lineID) {
        final var armorStands = HologramService.findWorldHologram(hologram);
        if (armorStands.isEmpty()) {
            HologramService.removeHologram(hologram.getName());
            return;
        }

        hologram.removeLine(lineID);
        if (!hologram.getLines().isEmpty()) {
            HologramService.updateHologram(hologram);
            HologramService.updateWorldHologram(hologram);
        } else {
            HologramService.removeHologram(hologram.getName());
            HologramService.removeWorldHologram(hologram);
        }

        player.sendMessage(MessageUtils.color("&aSuccessfully removed that line!"));
    }

    private void handleLineEdit(Player player, Hologram hologram, int lineID, String newLine) {
        final var armorStands = HologramService.findWorldHologram(hologram);
        if (armorStands.isEmpty()) {
            HologramService.removeHologram(hologram.getName());
            return;
        }

        hologram.editLine(lineID, newLine);
        HologramService.updateHologram(hologram);
        HologramService.updateWorldHologram(hologram);
        player.sendMessage(MessageUtils.color("&aSuccessfully edited that line!"));
    }
}
