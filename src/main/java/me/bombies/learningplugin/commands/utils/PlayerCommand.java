package me.bombies.learningplugin.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public abstract class PlayerCommand implements CommandExecutor {

    private final String commandName;

    public PlayerCommand(String commandName) {
        this.commandName = commandName.toLowerCase();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players can execute this command!");
            return true;
        }

        handle(new CommandArgs((Player) sender, command, label, Arrays.asList(args)));
        return true;
    }

    public abstract void handle(CommandArgs commandArgs);

    public String getCommandName() {
        return commandName;
    }
}
