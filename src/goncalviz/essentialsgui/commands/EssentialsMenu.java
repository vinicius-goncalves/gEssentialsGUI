package goncalviz.essentialsgui.commands;


import goncalviz.essentialsgui.database.UtilsSQL;
import goncalviz.essentialsgui.files.ConfigFile;
import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EssentialsMenu implements EssentialsMenuInterface {

    private Utils utils = new Utils();
    private UtilsSQL utilsSQL = new UtilsSQL();
    private ConfigFile configFile = new ConfigFile();
    private Inventory inventoryEssentials = Bukkit.createInventory(null, 3 * 9, utils.withColor("&8Essentials - Menu"));

    @Override
    public void openEssentialsMenu(Player player) {
        setItemsOnMenu(inventoryEssentials, player);
        player.openInventory(inventoryEssentials);

    }

    private void setItemsOnMenu(Inventory invExternal, Player player) {
        invExternal.setItem(10, utils.setItem(Material.ENDER_CHEST, 1, 0, "&eEnderchest &7(Clique)", new String[] {"§7Seu baú virtual"}));
        invExternal.setItem(11, utils.setItem(Material.WORKBENCH, 1, 0, "&eCraft Table &7(Clique)", new String[] {"§7Mesa de trabalho virtual"}));
        invExternal.setItem(12, utils.setSkull("Kill Player", "MHF_Zombie", new String[] { "..." }));
        if(configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
            invExternal.setItem(26, utils.setSkull("&a&l"+player.getName(), player.getName(), new String[] {"§7Você abriu esse inventário: §n" + utilsSQL.getClicks(player) + "§7 vezes"}));
        }
    }
}