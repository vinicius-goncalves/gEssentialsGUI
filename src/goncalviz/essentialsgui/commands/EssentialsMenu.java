package goncalviz.essentialsgui.commands;

import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class EssentialsMenu {

    private final Utils utils = new Utils();

    public void openInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 3 * 9, utils.withColor("&8Essentials - Menu"));
        player.openInventory(inv);

        inv.setItem(11, utils.setItem(Material.ENDER_CHEST, 1, 0, "&eEnderchest &7(Clique)", new String[] {"Seu baú virtual"}));

    }

}
