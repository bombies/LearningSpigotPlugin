package me.bombies.learningplugin;

import me.bombies.learningplugin.commands.misc.holograms.HologramService;
import me.bombies.learningplugin.commands.utils.CommandManager;
import me.bombies.learningplugin.events.EventManager;
import me.bombies.learningplugin.events.scoreboard.ScoreboardUtils;
import me.bombies.learningplugin.utils.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class LearningPlugin extends JavaPlugin {

    public static JavaPlugin core = null;
    public static Server server = null;
    public static Logger logger = null;

    static {
        Config.registerSerializers();
    }

    @Override
    public void onEnable() {
        core = this;

        saveDefaultConfig();
        server = getServer();
        logger = core.getLogger();
        Config.init(getConfig());

        initEagerCaches();
        EventManager.getInstance().registerEvents();
        CommandManager.getInstance().registerCommands();

        server.getWorlds().forEach(world -> {
            world.setTime(6000L);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        });

        initScoreboards();
    }

    private void initEagerCaches() {
        HologramService.loadHolograms();
    }

    private void initScoreboards() {
        Bukkit.getOnlinePlayers().forEach(player -> player.setScoreboard(new ScoreboardUtils(player).createSidebar()));
    }

    @Override
    public void onDisable() {
        // Saving logic
    }
}
