package br.goncalviz.essentialsgui.commands;

import br.goncalviz.essentialsgui.files.ConfigFile;
import br.goncalviz.essentialsgui.utils.Utils;
import br.goncalviz.essentialsgui.versionmanager.actionbar.VersionManagerActionBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class EssentialsMenuPlayer {

    private final Utils utils = new Utils();
    private ConfigFile configFile = new ConfigFile();
    private static HashMap<Player, Long> delayHashMap = new HashMap<>();
    private EssentialsInventory essentialsMenu = new EssentialsInventory();
    private long getDuration = configFile.getFileConfiguration().getLong("ativarDelay.duracao");
    private VersionManagerActionBar versionManagerActionBar = new VersionManagerActionBar();

    public void openInventoryToPlayerThatIsNotOp(Player player) {
        long now = System.currentTimeMillis();
        if(configFile.getFileConfiguration().getBoolean("ativarDelay.delay")) {
            if(delayHashMap.containsKey(player) && delayHashMap.get(player) >= now) {
                long time = TimeUnit.MILLISECONDS.toSeconds(delayHashMap.get(player) - now);
                versionManagerActionBar.getVersionManagerActionBarInterface().sendActionBarToTarget(player,
                        utils.withColor("&cAguarde " + time + "s para &cpara digitar o comando novamente."));

            }else {

                delayHashMap.put(player, System.currentTimeMillis() + getDuration * 1000);
                essentialsMenu.openEssentialsMenuForPlayerNotOp(player);
            }

        }else{

            if(!configFile.getFileConfiguration().getBoolean("ativarDelay.delay")) {
                essentialsMenu.openEssentialsMenuForPlayerNotOp(player);

            }
        }
    }

    public void openInventoryToPlayerThatIsOp(Player player) {
        essentialsMenu.openEssentialsMenuForPlayerOp(player);

    }
}
