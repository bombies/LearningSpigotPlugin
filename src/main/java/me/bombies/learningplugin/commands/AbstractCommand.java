package me.bombies.learningplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand implements CommandExecutor {

    private String commandName;

    public AbstractCommand(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players can execute this command!");
            return true;
        }

        handle((Player) sender, command, label, args);
        return true;
    }

    public abstract void handle(Player player, Command command, String label, String[] args);

    public String getCommandName() {
        return commandName;
    }
}
