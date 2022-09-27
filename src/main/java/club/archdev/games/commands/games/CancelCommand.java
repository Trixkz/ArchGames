package club.archdev.games.commands.games;

import club.archdev.games.ArchGames;
import club.archdev.games.commands.BaseCommand;
import club.archdev.games.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public class CancelCommand extends BaseCommand {

    private ArchGames main = ArchGames.getInstance();

    @Override
    public void executeAs(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        switch (args[1]) {
            case "defuse":
                if (this.main.getGames().get("defuse") == null) {
                    player.sendMessage(Utils.translate("&cA defuse game is not currently started"));

                    return;
                }

                this.main.getGames().get("defuse").cancelGame();

                break;
            case "scramble":
                if (this.main.getGames().get("scramble") == null) {
                    player.sendMessage(Utils.translate("&cA scramble game is not currently started"));

                    return;
                }

                this.main.getGames().get("scramble").cancelGame();

                break;
        }
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> tabCompletions = new ArrayList<String>();

        return tabCompletions;
    }
}
