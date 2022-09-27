package club.archdev.games.commands.games;

import club.archdev.games.ArchGames;
import club.archdev.games.commands.BaseCommand;
import club.archdev.games.games.Defuse;
import club.archdev.games.games.Scramble;
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
public class StartCommand extends BaseCommand {

    private ArchGames main = ArchGames.getInstance();

    @Override
    public void executeAs(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (this.main.getGames().size() != 0) {
            player.sendMessage(Utils.translate("&cThere is already currently a game started"));

            return;
        }

        switch (args[1]) {
            case "scramble":
                String word = args[2];

                if (word == null) {
                    return;
                }

                String scrambleMessage = this.main.getUtils().getMessage(args, 3);

                if (scrambleMessage == null) {
                    return;
                }

                Scramble scramble = new Scramble(scrambleMessage, word);
                scramble.startGame();

                break;
            case "defuse":
                if (!this.main.getUtils().isNumeric(args[2])) {
                    return;
                }

                int time = Integer.parseInt(args[2]);

                String defuseMessage = this.main.getUtils().getMessage(args, 3);

                if (defuseMessage == null) {
                    return;
                }

                Defuse defuse = new Defuse(defuseMessage, time);
                defuse.startGame();

                break;
        }
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> tabCompletions = new ArrayList<String>();

        return tabCompletions;
    }
}
