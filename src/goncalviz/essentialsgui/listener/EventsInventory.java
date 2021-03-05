package goncalviz.essentialsgui.listener;

import goncalviz.essentialsgui.utils.Utils;
import goncalviz.essentialsgui.versionmanager.actionbar.VersionManagerActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class EventsInventory implements Listener {

    private Utils utils = new Utils();
    private ArrayList<Player> players = new ArrayList<>();
    private VersionManagerActionBar versionManagerActionBar = new VersionManagerActionBar();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        String nameInventory = utils.withColor("&8Essentials - Menu");
        if (event.getInventory().getTitle().equals(utils.withColor("&8Essentials - Menu"))) {
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();

                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(utils.withColor("&eEnderchest &7(Clique)"))) {
                    player.openInventory(player.getEnderChest());

                }

                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(utils.withColor("&eCraft Table &7(Clique)"))) {
                    player.openWorkbench(player.getLocation(), true);


                }

                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(utils.withColor("&eEliminar um jogador"))) {
                    if (!players.contains(player)) {
                        players.add(player);
                        player.closeInventory();

                        versionManagerActionBar.getVersionManagerActionBarInterface().sendActionBarToTarget(player, utils.withColor("&eDigite o player que você deseja eliminar..."));

                    }
                }
            }
        }
    }

    @EventHandler
    public void playerName(AsyncPlayerChatEvent e) {
        if (players.contains(e.getPlayer())) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (e.getMessage().contains(player.getName())) {
                    player.setHealth(0);

                    player.sendMessage("Voce matou " + player.getName());

                }

                if (!e.getMessage().contains(player.getName())) {

                    player.sendMessage("O jogador nao existe.");
                }
            }
        }
    }
}
