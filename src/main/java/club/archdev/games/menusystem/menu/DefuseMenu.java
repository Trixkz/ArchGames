package club.archdev.games.menusystem.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import club.archdev.games.ArchGames;
import club.archdev.games.games.Defuse;
import club.archdev.games.menusystem.Menu;
import club.archdev.games.menusystem.PlayerMenuUtil;
import club.archdev.games.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public class DefuseMenu extends Menu {

    private ArchGames main = ArchGames.getInstance();

    private List<Material> items = new ArrayList<Material>();

    private Material itemToBeClicked;

    public DefuseMenu(PlayerMenuUtil playerMenuUtil) {
        super(playerMenuUtil);

        this.items.add(Material.APPLE);
        this.items.add(Material.BONE);
        this.items.add(Material.CAKE);
        this.items.add(Material.COBBLESTONE);
        this.items.add(Material.COOKIE);
        this.items.add(Material.GLOWSTONE);
        this.items.add(Material.ICE);
        this.items.add(Material.PACKED_ICE);
        this.items.add(Material.REDSTONE);
        this.items.add(Material.REDSTONE_COMPARATOR);
        this.items.add(Material.SAND);
        this.items.add(Material.SANDSTONE);
        this.items.add(Material.STONE);
        this.items.add(Material.SPONGE);
        this.itemToBeClicked = this.items.get(ThreadLocalRandom.current().nextInt(0, this.items.size()));
    }

    @Override
    public String getMenuName() {
        return Utils.translate("&bClick on the &3" + this.itemToBeClicked.name());
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        int count = 0;
        int itemToBeClickedAmount = 0;

        for (ItemStack itemStack : event.getClickedInventory().getContents()) {
            if (itemStack.isSimilar(new ItemStack(this.itemToBeClicked, 1))) {
                itemToBeClickedAmount++;
            }
        }

        if (event.getView().getTitle().equalsIgnoreCase(Utils.translate("&bClick on the &3" + this.itemToBeClicked.name()))) {
            if (event.getCurrentItem().getType() == this.itemToBeClicked) {
                event.getCurrentItem().setType(Material.WOOL);

                count++;

                player.updateInventory();
            }

            if (count == itemToBeClickedAmount) {
                for (Player target : this.main.getEnteredPlayers()) {
                    target.closeInventory();
                }

                Defuse defuse = (Defuse) this.main.getGames().get("defuse");
                defuse.setWinner(player.getName());
                defuse.stopGame();
            }
        }
    }

    @Override
    public void setMenuItems() {
        int amount = 0;

        for (int i = 0; i < this.getSlots(); i++) {
            this.inventory.setItem(amount++, new ItemStack(this.items.get(ThreadLocalRandom.current().nextInt(0, this.items.size())), 1));
        }
    }
}
