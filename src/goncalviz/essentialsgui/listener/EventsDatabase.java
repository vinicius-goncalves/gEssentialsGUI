package goncalviz.essentialsgui.listener;

import goncalviz.essentialsgui.database.UtilsSQL;
import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventsDatabase implements Listener {

    private Utils utils = new Utils();
    private UtilsSQL utilsSQL = new UtilsSQL();

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        String nameInventory = utils.withColor("&8Essentials - Menu");
        if(e.getInventory().getTitle().equals(nameInventory)) {
            if(utilsSQL.containsPlayer(player)) {
               utilsSQL.addClick(player, 1);

            }else if(!utilsSQL.containsPlayer(player)) {

                Bukkit.broadcastMessage("O jogador " + player.getName() + " foi adicionado ao banco de dados.");
                utilsSQL.setPlayer(player);

            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();
        if(!utilsSQL.containsPlayer(player)) {
            utilsSQL.setPlayer(player);
            player.sendMessage("aaa" +
                    "");

        }
    }
}
