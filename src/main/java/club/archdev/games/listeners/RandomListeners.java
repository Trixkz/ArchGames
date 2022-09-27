package club.archdev.games.listeners;

import club.archdev.games.ArchGames;
import club.archdev.games.giveaway.Giveaway;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import club.archdev.games.games.Scramble;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public class RandomListeners implements Listener {

    private ArchGames main = ArchGames.getInstance();

    @EventHandler
    public void onNumberGiveaway(AsyncPlayerChatEvent event) {
        if (this.main.getGiveaways().get("number-giveaway") == null) {
            return;
        }

        if (!this.main.getUtils().isNumeric(event.getMessage())) {
            return;
        }

        Player player = event.getPlayer();

        Giveaway numberGiveaway = this.main.getGiveaways().get("number-giveaway");

        if (numberGiveaway.isNumberGiveawayStarted()) {
            String message = event.getMessage();

            int number = Integer.parseInt(message);

            if (number == numberGiveaway.getWinningNumber()) {
                numberGiveaway.setWinner(player.getName());
                numberGiveaway.stopGiveaway();
            }
        }
    }

    @EventHandler
    public void onWordGiveaway(AsyncPlayerChatEvent event) {
        if (this.main.getGiveaways().get("word-giveaway") == null) {
            return;
        }

        Player player = event.getPlayer();

        if (this.main.getWords().contains(player)) {
            return;
        }

        Giveaway wordGiveaway = this.main.getGiveaways().get("word-giveaway");

        String message = event.getMessage();

        if (!message.equals(wordGiveaway.getWord())) {
            return;
        }

        if (wordGiveaway.isWordGiveawayStarted()) {
            if (wordGiveaway.getGiveawayTimerTask().getTime() <= 0) {
                return;
            }

            this.main.getWords().add(player);
        }
    }

    @EventHandler
    public void onScrambleGame(AsyncPlayerChatEvent event) {
        if (this.main.getGames().get("scramble") == null) {
            return;
        }

        Player player = event.getPlayer();

        Scramble scramble = (Scramble) this.main.getGames().get("scramble");

        String message = event.getMessage();

        if (scramble.getWord().equals(message)) {
            scramble.setWinner(player.getName());
            scramble.stopGame();
        }
    }
}
