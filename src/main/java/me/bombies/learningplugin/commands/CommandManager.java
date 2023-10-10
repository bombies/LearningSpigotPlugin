package me.bombies.learningplugin.commands;

import me.bombies.learningplugin.commands.management.spawn.SetSpawnCommand;
import me.bombies.learningplugin.commands.management.spawn.SpawnCommand;
import me.bombies.learningplugin.commands.management.worlds.WorldsCommand;
import me.bombies.learningplugin.commands.misc.WorkBenchCommand;
import me.bombies.learningplugin.commands.misc.fly.FlyCommand;
import me.bombies.learningplugin.commands.misc.login.LoginCommand;
import me.bombies.learningplugin.commands.misc.SuicideCommand;
import me.bombies.learningplugin.commands.misc.nick.NickCommand;
import me.bombies.learningplugin.commands.utils.PlayerCommand;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {

    private static CommandManager instance;

    private final JavaPlugin plugin;
    private final ArrayList<PlayerCommand> commands = new ArrayList<>();

    private CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;

        addCommands(
                new LoginCommand(),
                new SuicideCommand(),
                new FlyCommand(),
                new WorkBenchCommand(),
                new NickCommand(),
                new SetSpawnCommand(),
                new SpawnCommand(),
                new WorldsCommand()
        );
    }

    public static CommandManager getInstance(JavaPlugin plugin) {
        if (instance == null)
            instance = new CommandManager(plugin);
        return instance;
    }

    public void registerCommands() {
        for (PlayerCommand command : commands) {
            final PluginCommand pluginCommand = plugin.getCommand(command.getCommandName());
            if (pluginCommand == null) {
                System.out.println("The command " + command.getCommandName() + " does not exist in the plugin's plugin.yml!");
                continue;
            }

            pluginCommand.setExecutor(command);
        }

    }

    private void addCommands(PlayerCommand... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }
}
