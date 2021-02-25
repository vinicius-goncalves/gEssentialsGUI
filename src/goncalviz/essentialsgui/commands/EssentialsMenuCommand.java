package goncalviz.essentialsgui.commands;

import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class EssentialsMenuCommand implements CommandExecutor {

    private final Utils utils = new Utils();
    private static HashMap<Player, Long> delayHashMap = new HashMap<>();
    private EssentialsMenu essentialsMenu = new EssentialsMenu();
    private boolean delayBoolean;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(utils.withColor("&cApenas para jogadores in-game."));
            return true;

        }

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("essentials")) {
            if(delayHashMap.containsKey(player) && delayHashMap.get(player) >= System.currentTimeMillis()) {
                utils.sendActionbar(player, "&cVocê está em &ldelay&c. Aguarde para continuar.");
                return true;

            }else {

                delayHashMap.put(player, System.currentTimeMillis() + 3 * 1000);
                essentialsMenu.openEssentialsMenu(player);

            }

        }
        return false;
    }
}
