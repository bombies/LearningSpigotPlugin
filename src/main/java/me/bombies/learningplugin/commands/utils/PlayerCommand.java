package me.bombies.learningplugin.commands.utils;

import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class PlayerCommand implements CommandExecutor {

    private final static HashMap<UUID, CommandCooldown> coolDowns = new HashMap<>();


    private final String commandName;
    private final List<PlayerSubCommand> subCommands = new ArrayList<>();

    /**
     * The cool down of the command in seconds.
     */
    private long coolDown = -1;

    public PlayerCommand(String commandName) {
        this.commandName = commandName.toLowerCase();
    }

    public PlayerCommand(String commandName, long coolDown) {
        this.commandName = commandName.toLowerCase();
        this.coolDown = coolDown;
    }

    public PlayerCommand(String commandName, long coolDown, TimeUnit unit) {
        this.commandName = commandName.toLowerCase();
        this.coolDown = unit.toSeconds(coolDown);
    }

    public PlayerCommand(String commandName, PlayerSubCommand... subCommands) {
        this.commandName = commandName.toLowerCase();
        this.subCommands.addAll(Arrays.asList(subCommands));
    }

    public PlayerCommand(String commandName, long coolDown, TimeUnit unit, PlayerSubCommand... subCommands) {
        this.commandName = commandName.toLowerCase();
        this.coolDown = unit.toSeconds(coolDown);
        this.subCommands.addAll(Arrays.asList(subCommands));
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        if (!(sender instanceof Player player)) {
            System.out.println("Only players can execute this command!");
            return true;
        }

        final CommandContext context = new CommandContext(player, command, label, Arrays.asList(args));

        if (coolDown != -1 && isOnCoolDown(player)) {
            player.sendMessage(MessageUtils.color("&cThis command is on cooldown for &e" + getCoolDownTimeRemaining(context.getPlayer()) + " &cseconds."));
            return true;
        }

        if (!handle(context))
            handleSubCommands(context);

        if (coolDown != -1)
            addCooldown(player);
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

    private void addCooldown(Player player) {
        coolDowns.put(player.getUniqueId(), new CommandCooldown(player.getUniqueId(), commandName, System.currentTimeMillis()));
    }

    private boolean isOnCoolDown(Player player) {
        final var coolDown = coolDowns.get(player.getUniqueId());
        if (coolDown == null)
            return false;
        return coolDown.getTimeExecuted() + this.coolDown * 1000 > System.currentTimeMillis();
    }

    /**
     * Gets the time remaining for the current cooldown, if there is any, in seconds.
     *
     * @param player
     * @return
     */
    private long getCoolDownTimeRemaining(Player player) {
        final var coolDown = coolDowns.get(player.getUniqueId());
        if (coolDown == null)
            return 0;
        final long time = (coolDown.getTimeExecuted() + this.coolDown * 1000 - System.currentTimeMillis()) / 1000;
        return time < 0 ? 0 : time;
    }

    public String getCommandName() {
        return commandName;
    }
}
