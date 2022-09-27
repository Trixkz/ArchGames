package club.archdev.games;

import club.archdev.games.menusystem.PlayerMenuUtil;
import club.archdev.games.utils.config.FileConfig;
import club.archdev.games.utils.config.file.Config;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import club.archdev.games.games.Game;
import club.archdev.games.giveaway.Giveaway;
import club.archdev.games.listeners.MenuListener;
import club.archdev.games.listeners.RandomListeners;
import club.archdev.games.managers.CommandManager;
import club.archdev.games.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
@Getter
@Setter
public class ArchGames extends JavaPlugin {

    @Getter private static ArchGames instance;

    private Config settingsConfig;
    private FileConfig messagesConfig;

    private Utils utils;

    private CommandManager commandManager;

    private HashMap<String, Game> games = new HashMap<String, Game>();
    private HashMap<String, Giveaway> giveaways = new HashMap<String, Giveaway>();

    private List<Player> words = new ArrayList<Player>();
    private List<Player> enteredPlayers = new ArrayList<Player>();

    private HashMap<Player, PlayerMenuUtil> playerMenuUtilMap = new HashMap<>();

    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        this.settingsConfig = new Config("config", this);
        this.messagesConfig = new FileConfig(this, "messages.yml");
        this.utils = new Utils();

        Bukkit.getConsoleSender().sendMessage("------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(Utils.translate("&dGames &7- &av" + this.getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Utils.translate("&7Made by &eTrixkz &7(&fDiscord: LoganM#3465&7)"));
        Bukkit.getConsoleSender().sendMessage("------------------------------------------------");

        this.loadManagers();
        this.loadListeners();
        this.loadRunnables();
    }

    public void onDisable() {
        instance = null;
    }

    private void loadManagers() {
        this.commandManager = new CommandManager();
    }

    private void loadListeners() {
        Arrays.asList(
                new RandomListeners(),
                new MenuListener()
        ).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));
    }

    private void loadRunnables() {
        // new Aether(this, new ScoreboardProvider());
    }

    public PlayerMenuUtil getPlayerMenuUtil(Player player) {
        PlayerMenuUtil playerMenuUtil;

        if (playerMenuUtilMap.containsKey(player)) {
            return playerMenuUtilMap.get(player);
        } else {
            playerMenuUtil = new PlayerMenuUtil(player);

            playerMenuUtilMap.put(player, playerMenuUtil);

            return playerMenuUtil;
        }
    }
}
