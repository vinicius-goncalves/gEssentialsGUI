package goncalviz.essentialsgui.commands;

import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class EssentialsMenu {

    public static Utils utils = new Utils();
    public static Inventory inv = Bukkit.createInventory(null, 3 * 9, utils.withColor("&8Essentials - Menu"));

    public void openInventory(Player player) {
        setItems(inv);
        player.openInventory(inv);

    }

    private void setItems(Inventory inv) {
        inv.setItem(10, utils.setItem(Material.ENDER_CHEST, 1, 0, "&eEnderchest &7(Clique)", new String[] {"§7Seu baú virtual"}));
        inv.setItem(11, utils.setItem(Material.WORKBENCH, 1, 0, "&eCraft Table &7(Clique)", new String[] {"§7Mesa de trabalho virtual"}));
        inv.setItem(12, utils.setSkull("Kill Player", "MHF_Zombie"));
    }
}
