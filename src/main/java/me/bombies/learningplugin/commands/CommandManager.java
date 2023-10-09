package me.bombies.learningplugin.commands;

import me.bombies.learningplugin.commands.misc.login.LoginCommand;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {

    private static CommandManager instance;

    private final JavaPlugin plugin;
    private final ArrayList<AbstractCommand> commands = new ArrayList<>();

    private CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;

        addCommands(
                new LoginCommand()
        );
    }

    public static CommandManager getInstance(JavaPlugin plugin) {
        if (instance == null)
            instance = new CommandManager(plugin);
        return instance;
    }

    public void registerCommands() {
        for (AbstractCommand command : commands) {
            final PluginCommand pluginCommand = plugin.getCommand(command.getCommandName());
            if (pluginCommand == null) {
                System.out.println("The command " + command.getCommandName() + " does not exist in the plugin's plugin.yml!");
                continue;
            }

            pluginCommand.setExecutor(command);
        }

    }

    private void addCommands(AbstractCommand... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }
}
