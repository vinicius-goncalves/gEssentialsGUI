package goncalviz.essentialsgui.commands;


import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EssentialsMenu implements EssentialsMenuInterface {

    private Utils utils = new Utils();
    private Inventory inventoryEssentials = Bukkit.createInventory(null, 3 * 9, utils.withColor("&8Essentials - Menu"));

    @Override
    public void openEssentialsMenu(Player player) {
        setItemsOnMenu(inventoryEssentials);
        player.openInventory(inventoryEssentials);

    }

    private void setItemsOnMenu(Inventory invExternal) {
        invExternal.setItem(10, utils.setItem(Material.ENDER_CHEST, 1, 0, "&eEnderchest &7(Clique)", new String[] {"§7Seu baú virtual"}));
        invExternal.setItem(11, utils.setItem(Material.WORKBENCH, 1, 0, "&eCraft Table &7(Clique)", new String[] {"§7Mesa de trabalho virtual"}));
        invExternal.setItem(12, utils.setSkull("Kill Player", "MHF_Zombie"));
    }
}