package br.goncalviz.essentialsgui.reflection;

import org.bukkit.entity.Player;

public interface TitlesAPI_Interface {

    void sendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) throws Exception;

}
