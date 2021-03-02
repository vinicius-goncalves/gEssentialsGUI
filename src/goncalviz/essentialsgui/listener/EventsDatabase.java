package goncalviz.essentialsgui.listener;

import goncalviz.essentialsgui.database.UtilsSQL;
import goncalviz.essentialsgui.files.ConfigFile;
import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventsDatabase implements Listener {

    private Utils utils = new Utils();
    private UtilsSQL utilsSQL = new UtilsSQL();
    private ConfigFile configFile = new ConfigFile();

    @EventHandler
    public void openInventory(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        String nameInventory = utils.withColor("&8Essentials - Menu");
        if (e.getInventory().getTitle().equals(nameInventory)) {
            if (configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
                if (utilsSQL.containsPlayer(player)) {
                    utilsSQL.addClick(player, 1);
                    player.sendMessage("§aVocê abriu o inventário " + utilsSQL.getClicks(player) + " vezes.");
                }
            }else{
                if(utils.checkBoolean(configFile.getFileConfiguration().getBoolean("mensagensDeAlerta.bancoDeDadosDesativado"))) {
                    utils.sendMessageToOpPlayer("&c[OP Message] Tenha em mente que o banco de dados não está ligado.");
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (utils.checkBoolean(configFile.getFileConfiguration().getBoolean("bancoDeDados"))) {
            if (!utilsSQL.containsPlayer(player)) {
                utilsSQL.setPlayer(player);
            }
        }else{
            if(!utils.checkBoolean(configFile.getFileConfiguration().getBoolean("bancoDeDados"))) {
                player.sendMessage("desativado.");

            }
        }
    }
}
