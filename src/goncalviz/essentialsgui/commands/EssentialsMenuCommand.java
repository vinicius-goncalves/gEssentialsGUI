package goncalviz.essentialsgui.commands;

import goncalviz.essentialsgui.files.ConfigFile;
import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class EssentialsMenuCommand implements CommandExecutor {

    private final Utils utils = new Utils();
    private ConfigFile configFile = new ConfigFile();
    private static HashMap<Player, Long> delayHashMap = new HashMap<>();
    private EssentialsMenu essentialsMenu = new EssentialsMenu();
    private long getDuration = configFile.getFileConfiguration().getLong("ativarDelay.duracao");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(utils.withColor("&cApenas para jogadores in-game."));
            return true;

        }

        Player player = (Player) sender;
        long now = System.currentTimeMillis();
        if (command.getName().equalsIgnoreCase("essentials")) {
            if(configFile.getFileConfiguration().getBoolean("ativarDelay.delay")) {
                if(delayHashMap.containsKey(player) && delayHashMap.get(player) >= now) {
                    long time = TimeUnit.MILLISECONDS.toSeconds(delayHashMap.get(player) - now);
                    utils.sendActionbarToTarget(player, "&cAguarde " + time + "s para &cpara digitar o comando novamente.");
                    return true;

                }else {

                    delayHashMap.put(player, System.currentTimeMillis() + getDuration * 1000);
                    essentialsMenu.openEssentialsMenu(player);
                    return true;
                }

            }else{

                if(!configFile.getFileConfiguration().getBoolean("ativarDelay.delay")) {
                    essentialsMenu.openEssentialsMenu(player);
                    return true;

                }
            }

        }
        return false;
    }

}
