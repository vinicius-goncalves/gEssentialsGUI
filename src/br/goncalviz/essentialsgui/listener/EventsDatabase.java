package br.goncalviz.essentialsgui.listener;

import br.goncalviz.essentialsgui.database.UtilsSQL;
import br.goncalviz.essentialsgui.files.ConfigFile;
import br.goncalviz.essentialsgui.reflection.TitlesAPI;
import br.goncalviz.essentialsgui.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventsDatabase implements Listener {

    private Utils utils = new Utils();
    private UtilsSQL utilsSQL = new UtilsSQL();
    private ConfigFile configFile = new ConfigFile();
    private TitlesAPI titlesAPI = new TitlesAPI();

    @EventHandler
    public void openInventory(InventoryOpenEvent e) throws Exception {
        Player player = (Player) e.getPlayer();
        String nameInventory = utils.withColor("&8Essentials - Menu");
        if (e.getInventory().getTitle().equals(nameInventory)) {
            if (configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
                if (utilsSQL.containsPlayer(player)) {
                    utilsSQL.addClick(player, 1);
                    player.sendMessage("§aVocê abriu o inventário " + utilsSQL.getClicks(player) + " vezes.");
                }
            } else {
                if (configFile.getFileConfiguration().getBoolean("mensagensDeAlerta.bancoDeDadosDesativado")) {
                    utils.sendMessageToOpPlayer("&c[OP Message] Tenha em mente que o banco de dados não está ligado.");
                    titlesAPI.sendTitleToPlayer(player, "A", "B", 30, 40, 30);

                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        utilsSQL.verifiyIfDatabaseIsOn(player);

    }
}
