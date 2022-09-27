package club.archdev.games.commands;

import club.archdev.games.ArchGames;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import club.archdev.games.utils.Utils;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public class EnterCommand implements CommandExecutor {

    private ArchGames main = ArchGames.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (this.main.getGames().get("defuse") == null) {
            player.sendMessage(Utils.translate("&cThere is currently no game started"));

            return true;
        }

        if (this.main.getEnteredPlayers().contains(player)) {
            player.sendMessage(Utils.translate("&cYou have already been entered into the game"));

            return true;
        }

        this.main.getEnteredPlayers().add(player);

        player.sendMessage(Utils.translate("&aYou have been successfully entered into the game"));

        Bukkit.broadcastMessage(Utils.translate(""));
        Bukkit.broadcastMessage(Utils.translate("&3" + player.getName() + " &bhas entered into the game &7(&f#" + this.main.getEnteredPlayers().size() + "&7)"));
        Bukkit.broadcastMessage(Utils.translate(""));

        return true;
    }
}
