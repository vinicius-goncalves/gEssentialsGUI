package br.goncalviz.essentialsgui.commands;

import org.bukkit.entity.Player;

public interface EssentialsInventoryInterface {

    void openEssentialsMenuForPlayerNotOp(Player player);
    void openEssentialsMenuForPlayerOp(Player player);

}
