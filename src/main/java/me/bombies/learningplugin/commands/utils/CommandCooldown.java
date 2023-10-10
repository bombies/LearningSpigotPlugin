package me.bombies.learningplugin.commands.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandCooldown {
    private final UUID playerUUID;
    private final String commandName;
    private final long timeExecuted;

    CommandCooldown(UUID playerUUID, String commandName, long timeExecuted) {
        this.playerUUID = playerUUID;
        this.commandName = commandName;
        this.timeExecuted = timeExecuted;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    public String getCommandName() {
        return commandName;
    }

    public PlayerCommand getCommand() {
        return CommandManager.getInstance().getCommand(commandName);
    }

    public long getTimeExecuted() {
        return timeExecuted;
    }
}
