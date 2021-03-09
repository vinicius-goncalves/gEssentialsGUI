package br.goncalviz.essentialsgui.reflection;

import org.bukkit.entity.Player;

public interface ReflectionInterface {

    void sendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) throws Exception;
    void sendActionBarToPlayer(Player player, String message) throws Exception;

}
