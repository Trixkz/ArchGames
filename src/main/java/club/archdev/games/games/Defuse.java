package club.archdev.games.games;

import club.archdev.games.ArchGames;
import club.archdev.games.tasks.DefuseTimerTask;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import club.archdev.games.utils.Utils;

import java.util.List;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
@Getter
@Setter
public class Defuse extends Game {

    private ArchGames main = ArchGames.getInstance();

    private String message;
    private int time;
    private String winner;

    private DefuseTimerTask defuseTimerTask;

    public Defuse(String message, int time) {
        this.message = message;
        this.time = time;
        this.defuseTimerTask = new DefuseTimerTask(this, this.time);
    }

    @Override
    public void startGame() {
        this.main.getGames().put("defuse", this);
        this.defuseTimerTask.runTaskTimer(this.main, 0, 20);

        List<String> message = Lists.newArrayList();

        for (String string : Utils.DEFUSE_GAME_STARTED_MESSAGE) {
            message.add(string.replace("%message%", this.message));
        }

        for (String string : message) {
            Bukkit.broadcastMessage(Utils.translate(string));
        }
    }

    @Override
    public void stopGame() {
        this.main.getGames().remove("defuse");

        if (this.defuseTimerTask != null) {
            this.defuseTimerTask.cancel();
        }

        for (Player player : this.main.getEnteredPlayers()) {
            player.closeInventory();
        }

        this.main.getEnteredPlayers().clear();

        List<String> message = Lists.newArrayList();

        for (String string : Utils.DEFUSE_GAME_STOPPED_MESSAGE) {
            message.add(string.replace("%winner%", this.winner).replace("%message%", this.message));
        }

        for (String string : message) {
            Bukkit.broadcastMessage(Utils.translate(string));
        }
    }

    @Override
    public void cancelGame() {
        this.main.getGames().remove("defuse");

        for (Player player : this.main.getEnteredPlayers()) {
            player.closeInventory();
        }

        this.main.getEnteredPlayers().clear();

        for (String string : Utils.GAME_CANCELLED_MESSAGE) {
            Bukkit.broadcastMessage(Utils.translate(string));
        }
    }
}
