package club.archdev.games.giveaway;

import club.archdev.games.ArchGames;
import club.archdev.games.tasks.GiveawayTimerTask;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import club.archdev.games.utils.Utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
@Getter
@Setter
public class Giveaway {

    private ArchGames main = ArchGames.getInstance();

    private String message;
    private boolean isNumber;
    private int numberTo;
    private String word;
    private int time;
    private boolean hasWinningNumber;
    private int winningNumber;

    private boolean numberGiveawayStarted = false;
    private boolean wordGiveawayStarted = false;

    private String winner;

    private GiveawayTimerTask giveawayTimerTask;

    public Giveaway(String message, boolean isNumber, int numberTo, String word, int time, boolean hasWinningNumber, int winningNumber) {
        this.message = message;
        this.isNumber = isNumber;

        if (this.isNumber) {
            this.numberTo = numberTo;
            this.hasWinningNumber = hasWinningNumber;

            if (this.hasWinningNumber) {
                this.winningNumber = winningNumber;
            } else {
                this.winningNumber = ThreadLocalRandom.current().nextInt(this.numberTo);
            }
        } else {
            this.word = word;
            this.time = time;
            this.giveawayTimerTask = new GiveawayTimerTask(this, this.time);
        }
    }

    public void startGiveaway() {
        if (this.isNumber) {
            this.numberGiveawayStarted = true;

            this.main.getGiveaways().put("number-giveaway", this);

            List<String> message = Lists.newArrayList();

            for (String string : Utils.NUMBER_GIVEAWAY_STARTED_MESSAGE) {
                message.add(string.replace("%number_to%", String.valueOf(this.numberTo)).replace("%message%", this.message));
            }

            for (String string : message) {
                Bukkit.broadcastMessage(Utils.translate(string));
            }
        } else {
            this.wordGiveawayStarted = true;

            this.main.getGiveaways().put("word-giveaway", this);

            this.giveawayTimerTask.runTaskTimer(ArchGames.getInstance(), 0, 20);

            List<String> message = Lists.newArrayList();

            for (String string : Utils.WORD_GIVEAWAY_STARTED_MESSAGE) {
                message.add(string.replace("%word%", this.word).replace("%message%", this.message));
            }

            for (String string : message) {
                Bukkit.broadcastMessage(Utils.translate(string));
            }
        }
    }

    public void stopGiveaway() {
        if (this.isNumber) {
            this.numberGiveawayStarted = false;

            this.main.getGiveaways().remove("number-giveaway");

            List<String> message = Lists.newArrayList();

            for (String string : Utils.NUMBER_GIVEAWAY_STOPPED_MESSAGE) {
                message.add(string.replace("%winner%", this.winner).replace("%message%", this.message).replace("%winning_number%", String.valueOf(this.winningNumber)));
            }

            for (String string : message) {
                Bukkit.broadcastMessage(Utils.translate(string));
            }
        } else {
            this.wordGiveawayStarted = false;

            this.main.getGiveaways().remove("word-giveaway");
            this.main.getWords().clear();

            if (this.giveawayTimerTask != null) {
                this.giveawayTimerTask.cancel();
            }

            List<String> message = Lists.newArrayList();

            for (String string : Utils.WORD_GIVEAWAY_STOPPED_MESSAGE) {
                message.add(string.replace("%winner%", this.winner).replace("%message%", this.message));
            }

            for (String string : message) {
                Bukkit.broadcastMessage(Utils.translate(string));
            }
        }
    }

    public void cancelGiveaway() {
        if (this.isNumber) {
            this.numberGiveawayStarted = false;

            this.main.getGiveaways().remove("number-giveaway");

            for (String string : Utils.GIVEAWAY_CANCELLED_MESSAGE) {
                Bukkit.broadcastMessage(Utils.translate(string));
            }
        } else {
            this.wordGiveawayStarted = false;

            if (this.giveawayTimerTask != null) {
                this.giveawayTimerTask.cancel();
            }

            this.main.getGiveaways().remove("word-giveaway");
            this.main.getWords().clear();

            for (String string : Utils.GIVEAWAY_CANCELLED_MESSAGE) {
                Bukkit.broadcastMessage(Utils.translate(string));
            }
        }
    }
}
