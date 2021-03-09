package br.goncalviz.essentialsgui.commands;

import br.goncalviz.essentialsgui.listener.EventsInventoryOP;
import br.goncalviz.essentialsgui.reflection.UtilsForAPIs;
import br.goncalviz.essentialsgui.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EssentialsInventoryCommand implements CommandExecutor {

    private final Utils utils = new Utils();
    private EssentialsMenuPlayer essentialsMenuPlayer = new EssentialsMenuPlayer();
    private UtilsForAPIs utilsForAPIs = new UtilsForAPIs();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(utils.withColor("&cApenas para jogadores in-game."));
            return true;

        }

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("essentials")) {
            if (!player.isOp()) {
                essentialsMenuPlayer.openInventoryToPlayerThatIsNotOp(player);
            }else {
               if(player.isOp() && EventsInventoryOP.playersUtils.contains(player)) {
                   try {
                       utilsForAPIs.sendActionBar(player, "&cVocê não pode abrir o menu enquanto está tentando matar alguém,");
                   } catch(Exception e) {
                       e.printStackTrace();
                   }
               }else {
                   essentialsMenuPlayer.openInventoryToPlayerThatIsOp(player);
               }

            }
        }
        return false;
    }

}
