package br.goncalviz.essentialsgui.listener;

import br.goncalviz.essentialsgui.utils.Utils;
import br.goncalviz.essentialsgui.versionmanager.actionbar.VersionManagerActionBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class EventsInventory implements Listener {

    private Utils utils = new Utils();
    private ArrayList<Player> playersListToKill = new ArrayList<>();
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

                }
            }
        }
    }
}
