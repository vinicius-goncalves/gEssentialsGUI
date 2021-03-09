package br.goncalviz.essentialsgui.reflection;

import br.goncalviz.essentialsgui.utils.Utils;
import org.bukkit.entity.Player;

public class UtilsForAPIs {

    private ActionBarAPI actionBarAPI = new ActionBarAPI();
    private TitlesAPI titlesAPI = new TitlesAPI();
    private Utils utils = new Utils();

    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) throws Exception {
        titlesAPI.sendTitleToPlayer(player, utils.withColor(title), utils.withColor(subtitle), fadeIn, stay, fadeOut);

    }

    public void sendActionBar(Player player, String message) throws Exception {
        actionBarAPI.sendActionBarToPlayer(player, utils.withColor(message));

    }
}
