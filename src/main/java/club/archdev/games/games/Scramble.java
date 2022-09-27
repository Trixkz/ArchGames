package club.archdev.games.games;

import club.archdev.games.ArchGames;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import club.archdev.games.utils.Utils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
@Getter
@Setter
public class Scramble extends Game {

    private ArchGames main = ArchGames.getInstance();

    private String message;
    private String word;
    private String scrambledWord;
    private String winner;

    public Scramble(String message, String word) {
        this.message = message;
        this.word = word;

        String[] scrambledWord = this.word.split("");

        List<String> letters = Arrays.asList(scrambledWord);

        Collections.shuffle(letters);

        StringBuilder stringBuilder = new StringBuilder(this.word.length());

        for (String string : letters) {
            stringBuilder.append(string);
        }

        this.scrambledWord = stringBuilder.toString();
    }

    @Override
    public void startGame() {
        this.main.getGames().put("scramble", this);

        List<String> message = Lists.newArrayList();

        for (String string : Utils.SCRAMBLE_GAME_STARTED_MESSAGE) {
            message.add(string.replace("%scrambled_word%", this.scrambledWord).replace("%message%", this.message));
        }

        for (String string : message) {
            Bukkit.broadcastMessage(Utils.translate(string));
        }
    }

    @Override
    public void stopGame() {
        this.main.getGames().remove("scramble");

        List<String> message = Lists.newArrayList();

        for (String string : Utils.SCRAMBLE_GAME_STOPPED_MESSAGE) {
            message.add(string.replace("%winner%", this.winner).replace("%message%", this.message));
        }

        for (String string : message) {
            Bukkit.broadcastMessage(Utils.translate(string));
        }
    }

    @Override
    public void cancelGame() {
        this.main.getGames().remove("scramble");

        for (String string : Utils.GAME_CANCELLED_MESSAGE) {
            Bukkit.broadcastMessage(Utils.translate(string));
        }
    }
}
