package me.bombies.learningplugin.commands.utils;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandContext {
    @Getter
    private final Player player;
    @Getter
    private final Command command;
    @Getter
    private final String label;
    private final List<String> args;

    CommandContext(Player player, Command command, String label, List<String> args) {
        this.player = player;
        this.command = command;
        this.label = label;
        this.args = args;
    }

    public CommandArgs getArgs() {
        return new CommandArgs(args);
    }
}
