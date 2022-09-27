package club.archdev.games.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.List;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public abstract class BaseCommand {

    public abstract void executeAs(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args);

    public abstract List<String> getTabCompletions(CommandSender sender, Command cmd, String label, String[] args);
}

