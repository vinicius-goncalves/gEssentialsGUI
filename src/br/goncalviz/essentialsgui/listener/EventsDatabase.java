package br.goncalviz.essentialsgui.listener;

import br.goncalviz.essentialsgui.database.UtilsSQL;
import br.goncalviz.essentialsgui.files.ConfigFile;
import br.goncalviz.essentialsgui.reflection.ActionBarAPI;
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
    private ActionBarAPI actionBarAPI = new ActionBarAPI();

    @EventHandler
    public void openInventory(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        String nameInventory = utils.withColor("&8Essentials - Menu");
        String nameInventoryOp = utils.withColor("&8EssentialsMenu - &cOP");
        if (e.getInventory().getTitle().equals(nameInventory) || e.getInventory().getTitle().equals(nameInventoryOp)) {
            if (configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
                if (utilsSQL.containsPlayer(player)) {
                    utilsSQL.addClick(player, 1);
                    player.sendMessage("§aVocê abriu o inventário " + utilsSQL.getClicks(player) + " vezes.");
                }
            } else {
                if (configFile.getFileConfiguration().getBoolean("mensagensDeAlerta.bancoDeDadosDesativado")) {
                    utils.sendMessageToOpPlayer("&c[OP Message] Tenha em mente que o banco de dados não está ligado.");

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
