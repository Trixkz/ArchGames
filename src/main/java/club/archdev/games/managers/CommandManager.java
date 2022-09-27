package club.archdev.games.managers;

import club.archdev.games.ArchGames;
import club.archdev.games.commands.EnterCommand;
import club.archdev.games.commands.games.GamesCommand;
import club.archdev.games.commands.giveaway.GiveawayCommand;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public class CommandManager {

    private ArchGames main = ArchGames.getInstance();

    public CommandManager() {
        this.registerCommands();
    }

    private void registerCommands() {
        this.main.getCommand("games").setExecutor(new GamesCommand());
        this.main.getCommand("giveaway").setExecutor(new GiveawayCommand());
        this.main.getCommand("enter").setExecutor(new EnterCommand());
    }
}
