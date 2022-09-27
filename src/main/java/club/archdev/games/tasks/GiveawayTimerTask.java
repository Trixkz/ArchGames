package club.archdev.games.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.scheduler.BukkitRunnable;
import club.archdev.games.ArchGames;
import club.archdev.games.giveaway.Giveaway;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class GiveawayTimerTask extends BukkitRunnable {

    private final ArchGames main = ArchGames.getInstance();

    private Giveaway giveaway;
    private int time;

    @Override
    public void run() {
        this.time--;

        if (this.time <= 0) {
            this.cancel();

            int random = ThreadLocalRandom.current().nextInt(ArchGames.getInstance().getWords().size());

            this.giveaway.setWinner(this.main.getWords().get(random).getName());
            this.giveaway.stopGiveaway();

            return;
        }
    }
}
