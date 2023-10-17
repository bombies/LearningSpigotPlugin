package me.bombies.learningplugin.events.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public class GameEndCustomEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    final Player winner;
    final Player loser;
    final int winnerScore;

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
