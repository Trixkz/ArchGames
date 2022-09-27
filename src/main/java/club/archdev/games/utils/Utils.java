package club.archdev.games.utils;

import club.archdev.games.ArchGames;
import net.md_5.bungee.api.ChatColor;

import java.util.List;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public class Utils {

    private ArchGames main = ArchGames.getInstance();

    public static String scoreboardBar = org.bukkit.ChatColor.GRAY.toString() + org.bukkit.ChatColor.STRIKETHROUGH + "----------------------";
    public static String chatBar = org.bukkit.ChatColor.GRAY.toString() + org.bukkit.ChatColor.STRIKETHROUGH + "--------------------------------------------";

    public static boolean DEBUG_MESSAGE;
    public static String ADMIN_PERMISSION_NODE;
    public static String NO_PERMISSION_MESSAGE;
    public static List<String> NUMBER_GIVEAWAY_STARTED_MESSAGE;
    public static List<String> NUMBER_GIVEAWAY_STOPPED_MESSAGE;
    public static List<String> GIVEAWAY_CANCELLED_MESSAGE;
    public static List<String> WORD_GIVEAWAY_STARTED_MESSAGE;
    public static List<String> WORD_GIVEAWAY_STOPPED_MESSAGE;
    public static List<String> SCRAMBLE_GAME_STARTED_MESSAGE;
    public static List<String> SCRAMBLE_GAME_STOPPED_MESSAGE;
    public static List<String> GAME_CANCELLED_MESSAGE;
    public static List<String> DEFUSE_GAME_STARTED_MESSAGE;
    public static List<String> DEFUSE_GAME_STOPPED_MESSAGE;

    public Utils() {
        DEBUG_MESSAGE = this.main.getSettingsConfig().getConfig().getBoolean("debug");
        ADMIN_PERMISSION_NODE = this.main.getSettingsConfig().getConfig().getString("admin-permission-node");
        NO_PERMISSION_MESSAGE = this.main.getSettingsConfig().getConfig().getString("no-permission-message");
        NUMBER_GIVEAWAY_STARTED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("number-giveaway-started-message");
        NUMBER_GIVEAWAY_STOPPED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("number-giveaway-stopped-message");
        GIVEAWAY_CANCELLED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("giveaway-cancelled-message");
        WORD_GIVEAWAY_STARTED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("word-giveaway-started-message");
        WORD_GIVEAWAY_STOPPED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("word-giveaway-stopped-message");
        SCRAMBLE_GAME_STARTED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("scramble-game-started-message");
        SCRAMBLE_GAME_STOPPED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("scramble-game-stopped-message");
        GAME_CANCELLED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("game-cancelled-message");
        DEFUSE_GAME_STARTED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("defuse-game-started-message");
        DEFUSE_GAME_STOPPED_MESSAGE = this.main.getMessagesConfig().getConfig().getStringList("defuse-game-stopped-message");
    }

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void debug(String message) {
        if (DEBUG_MESSAGE) {
            System.out.println(message);
        }
    }

    public String getMessage(String[] args, int number) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = number; i < args.length; i++) {
            stringBuilder.append(args[i]).append(number >= args.length - 1 ? "" : " ");
        }

        return stringBuilder.toString();
    }

    public boolean isNumeric(String string) {
        return regexNumeric(string).length() == 0;
    }

    public String regexNumeric(String string) {
        return string.replaceAll("[0-9]", "").replaceFirst("\\.", "");
    }
}