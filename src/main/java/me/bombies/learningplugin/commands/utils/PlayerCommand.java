package me.bombies.learningplugin.commands.utils;

import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PlayerCommand implements CommandExecutor {

    private final String commandName;
    private final List<PlayerSubCommand> subCommands = new ArrayList<>();

    public PlayerCommand(String commandName) {
        this.commandName = commandName.toLowerCase();
    }

    public PlayerCommand(String commandName, PlayerSubCommand... subCommands) {
        this.commandName = commandName.toLowerCase();
        this.subCommands.addAll(Arrays.asList(subCommands));
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players can execute this command!");
            return true;
        }

        final CommandContext context = new CommandContext((Player) sender, command, label, Arrays.asList(args));
        if (!handle(context))
            handleSubCommands(context);
        return true;
    }

    /**
     * How the command should be handled.
     *
     * @param commandContext The context of the command.
     * @return True if the command was successfully executed, false otherwise.
     */
    public abstract boolean handle(CommandContext commandContext);

    public void handleSubCommands(CommandContext commandContext) {
        final String commandName = commandContext.getArgs().first();
        if (commandName == null)
            return;

        final PlayerSubCommand subCommand = subCommands.stream()
                .filter(command -> command.getName().equalsIgnoreCase(commandName) || command.getAliases().contains(commandName.toLowerCase()))
                .findFirst()
                .orElse(null);

        if (subCommand == null) {
            commandContext.getPlayer().sendMessage(MessageUtils.color("&cUnknown command."));
            return;
        }

        subCommand.setParentCommand(this);
        subCommand.setCommandContext(commandContext);
        subCommand.execute();
    }

    public String getCommandName() {
        return commandName;
    }
}
