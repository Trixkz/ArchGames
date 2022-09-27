package club.archdev.games.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import club.archdev.games.ArchGames;
import club.archdev.games.games.Game;
import club.archdev.games.menusystem.menu.DefuseMenu;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class DefuseTimerTask extends BukkitRunnable {

    private final ArchGames main = ArchGames.getInstance();

    private Game game;
    private int time;

    @Override
    public void run() {
        this.time--;

        if (this.time <= 0) {
            this.cancel();

            if (this.main.getEnteredPlayers().size() == 0) {
                this.game.cancelGame();

                return;
            }

            for (Player target : this.main.getEnteredPlayers()) {
                new DefuseMenu(this.main.getPlayerMenuUtil(target)).open();
            }

            return;
        }
    }
}
