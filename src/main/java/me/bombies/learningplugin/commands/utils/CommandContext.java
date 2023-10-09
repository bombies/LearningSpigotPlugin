package me.bombies.learningplugin.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandContext {
    private final Player player;
    private final Command command;
    private final String label;
    private final List<String> args;

    protected CommandContext(Player player, Command command, String label, List<String> args) {
        this.player = player;
        this.command = command;
        this.label = label;
        this.args = args;
    }

    public Player getPlayer() {
        return player;
    }

    public Command getCommand() {
        return command;
    }

    public String getLabel() {
        return label;
    }

    public CommandArgs getArgs() {
        return new CommandArgs(args);
    }
}
