package club.archdev.games.commands.games;

import club.archdev.games.ArchGames;
import club.archdev.games.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public class GamesCommand implements CommandExecutor {

    private ArchGames main = ArchGames.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (!player.hasPermission(Utils.ADMIN_PERMISSION_NODE)) {
            player.sendMessage(Utils.translate(Utils.NO_PERMISSION_MESSAGE));

            return true;
        }

        if (args.length == 0) {
            player.sendMessage(Utils.translate("&cUsage:"));
            player.sendMessage(Utils.translate("  &c/games start scramble <word> <message>"));
            player.sendMessage(Utils.translate("  &c/games start defuse <time> <message>"));
            player.sendMessage(Utils.translate("  &c/games cancel <game>"));
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
