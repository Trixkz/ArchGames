package club.archdev.games.commands.giveaway;

import club.archdev.games.ArchGames;
import club.archdev.games.commands.BaseCommand;
import club.archdev.games.giveaway.Giveaway;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import club.archdev.games.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public class StartCommand extends BaseCommand {

    private ArchGames main = ArchGames.getInstance();

    @Override
    public void executeAs(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (this.main.getUtils().isNumeric(args[1])) {
            if (this.main.getGiveaways().get("number-giveaway") != null) {
                player.sendMessage(Utils.translate("&cA number giveaway is currently already started"));

                return;
            }

            String message = this.main.getUtils().getMessage(args, 3);

            if (message == null) {
                return;
            }

            if (!this.main.getUtils().isNumeric(args[1])) {
                return;
            }

            int numberTo = Integer.parseInt(args[1]);

            if (this.main.getUtils().isNumeric(args[2])) {
                int winningNumber = Integer.parseInt(args[2]);

                if (winningNumber == 0) {
                    Giveaway numberGiveaway = new Giveaway(message, true, numberTo, null, 0, false, winningNumber);
                    numberGiveaway.startGiveaway();

                    player.sendMessage(Utils.translate("&bThe winning number is &3" + numberGiveaway.getWinningNumber()));

                    this.main.getGiveaways().put("number-giveaway", numberGiveaway);
                } else {
                    Giveaway numberGiveaway = new Giveaway(message, true, numberTo, null, 0, true, winningNumber);
                    numberGiveaway.startGiveaway();
                }
            }
        } else {
            if (this.main.getGiveaways().get("word-giveaway") != null) {
                player.sendMessage(Utils.translate("&cA word giveaway is currently already started"));

                return;
            }

            String message = this.main.getUtils().getMessage(args, 3);

            if (message == null) {
                return;
            }

            String word = args[1];

            if (word == null) {
                return;
            }

            if (!this.main.getUtils().isNumeric(args[2])) {
                return;
            }

            int time = Integer.parseInt(args[2]);

            Giveaway wordGiveaway = new Giveaway(message, false, 0, word, time, false, 0);
            wordGiveaway.startGiveaway();
        }
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> tabCompletions = new ArrayList<String>();

        return tabCompletions;
    }
}
