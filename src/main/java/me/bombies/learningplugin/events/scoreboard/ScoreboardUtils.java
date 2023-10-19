package me.bombies.learningplugin.events.scoreboard;

import lombok.AllArgsConstructor;
import me.bombies.learningplugin.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

@AllArgsConstructor
public class ScoreboardUtils {

    private final Player player;
    private final ScoreboardManager manager = Bukkit.getScoreboardManager();


    public Scoreboard createSidebar() {
        final var scoreboard = manager.getNewScoreboard();
        final var objective = scoreboard.registerNewObjective("title", Criteria.DUMMY, MessageUtils.color("&2&lDevelopment Server"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        final var onlinePlayersTitle = objective.getScore(MessageUtils.color("&aOnline Players"));
        final var onlinePlayersDescription = objective.getScore(String.valueOf(Bukkit.getOnlinePlayers().size()));

        final var blankLine = objective.getScore("");
        blankLine.setScore(3);
        onlinePlayersTitle.setScore(2);
        onlinePlayersDescription.setScore(1);
        return scoreboard;
    }

    public Scoreboard createTabList() {
        final var tabList = manager.getNewScoreboard();
        final var objective = tabList.registerNewObjective("title", Criteria.DUMMY, MessageUtils.color("&2&lDevelopment Server"));
        objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        return tabList;
    }
}
