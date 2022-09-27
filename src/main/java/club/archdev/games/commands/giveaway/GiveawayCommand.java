package club.archdev.games.commands.giveaway;

import club.archdev.games.ArchGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import club.archdev.games.utils.Utils;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public class GiveawayCommand implements CommandExecutor {

    private ArchGames main = ArchGames.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (!player.hasPermission(Utils.ADMIN_PERMISSION_NODE)) {
            player.sendMessage(Utils.translate(Utils.NO_PERMISSION_MESSAGE));

            return true;
        }

        if (args.length == 0) {
            player.sendMessage(Utils.translate("&cUsage:"));
            player.sendMessage(Utils.translate("  &c/giveaway start <number to> <0 | winning number> <message>"));
            player.sendMessage(Utils.translate("  &c/giveaway start <word> <time> <message>"));
            player.sendMessage(Utils.translate("  &c/giveaway cancel <number | word>"));
            player.sendMessage(Utils.translate(""));
            player.sendMessage(Utils.translate("&cNote: If you put '0' for the winning number, then this will make the winning number randomized"));
        } else {
            switch (args[0]) {
                case "start":
                    new StartCommand().executeAs(sender, cmd, label, args);

                    break;
                case "cancel":
                    new CancelCommand().executeAs(sender, cmd, label, args);

                    break;
            }
        }

        return true;
    }
}
