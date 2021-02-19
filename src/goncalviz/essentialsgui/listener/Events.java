package goncalviz.essentialsgui.listener;

import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Events implements Listener {

    private Utils utils = new Utils();

    @EventHandler
    public void cancelEvent(InventoryClickEvent e) {
        String nameInventory = utils.withColor("&8Essentials - Menu");
        if (e.getInventory().getTitle().equals(nameInventory)) {
            if (e.getWhoClicked() instanceof Player) {
                Player player = (Player) e.getWhoClicked();
                e.setCancelled(true);

                player.sendMessage(utils.withColor("&cEvento cancelado."));

                if (e.getClick() == ClickType.NUMBER_KEY && e.getInventory().getTitle().equals(nameInventory)) {
                    player.sendMessage(utils.withColor("&cIsso &lnão&c é permitido."));
                    player.closeInventory();

                }
            }

            e.setCancelled(true);
        }
    }
}
