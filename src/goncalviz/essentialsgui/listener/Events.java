package goncalviz.essentialsgui.listener;

import goncalviz.essentialsgui.utils.Utils;
import goncalviz.essentialsgui.versionmanager.actionbar.VersionManagerActionBar;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Events implements Listener {

    private Utils utils = new Utils();
    private VersionManagerActionBar versionManagerActionBar = new VersionManagerActionBar();

    @EventHandler
    public void cancelEvent(InventoryClickEvent event) {
        String nameInventory = utils.withColor("&8Essentials - Menu");
        if (event.getInventory().getTitle().equals(nameInventory)) {
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                event.setCancelled(true);

                if (event.getClick() == ClickType.NUMBER_KEY && event.getInventory().getTitle().equals(nameInventory)) {
                    player.sendMessage(utils.withColor("&cIsso &lnão&c é permitido."));
                    player.closeInventory();

                }

                if(event.getCurrentItem().getType() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
                    return;

                }
            }
        }

    }
}
