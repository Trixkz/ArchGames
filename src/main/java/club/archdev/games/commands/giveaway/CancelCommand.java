package club.archdev.games.commands.giveaway;

import club.archdev.games.ArchGames;
import club.archdev.games.commands.BaseCommand;
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
            case "number":
                this.main.getGiveaways().get("number-giveaway").cancelGiveaway();

                break;
            case "word":
                this.main.getGiveaways().get("word-giveaway").cancelGiveaway();

                break;
        }
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> tabCompletions = new ArrayList<String>();

        return tabCompletions;
    }
}
