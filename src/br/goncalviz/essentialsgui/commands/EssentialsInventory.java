package br.goncalviz.essentialsgui.commands;


import br.goncalviz.essentialsgui.database.UtilsSQL;
import br.goncalviz.essentialsgui.files.ConfigFile;
import br.goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EssentialsInventory implements EssentialsInventoryInterface {

    private Utils utils = new Utils();
    private UtilsSQL utilsSQL = new UtilsSQL();
    private ConfigFile configFile = new ConfigFile();
    private Inventory inventoryEssentialsForPlayerNotOp = Bukkit.createInventory(null, 3 * 9, utils.withColor("&8Essentials - Menu"));
    private Inventory invetoryEssentialsForPlayerOp = Bukkit.createInventory(null, 3 * 9, utils.withColor("&8EssentialsMenu - &cOP"));

    @Override
    public void openEssentialsMenuForPlayerNotOp(Player player) {
        setItemsOnMenuForPlayerNotOp(inventoryEssentialsForPlayerNotOp, player);
        player.openInventory(inventoryEssentialsForPlayerNotOp);

    }

    private void setItemsOnMenuForPlayerNotOp(Inventory invExternal, Player player) {
        invExternal.setItem(10, utils.setItem(Material.ENDER_CHEST, 1, 0, "&eEnderchest &7(Clique)", new String[] {"§7Seu baú virtual"}));
        invExternal.setItem(11, utils.setItem(Material.WORKBENCH, 1, 0, "&eCraft Table &7(Clique)", new String[] {"§7Mesa de trabalho virtual"}));
        if(configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
            invExternal.setItem(26, utils.setSkull("&a&l"+player.getName(), player.getName(), new String[] {"§7Você abriu esse inventário: §n" + utilsSQL.getClicks(player) + "§7 vezes"}));
        }
    }

    @Override
    public void openEssentialsMenuForPlayerOp(Player player) {
        setItemsOnMenuForPlayerOp(invetoryEssentialsForPlayerOp, player);
        player.openInventory(invetoryEssentialsForPlayerOp);

    }

    private void setItemsOnMenuForPlayerOp (Inventory invOp, Player player) {
        invOp.setItem(10, utils.setSkull(utils.withColor("&eEliminar um jogador &7(Clique)"), utils.getRandomPlayer().getName(), new String[] { "§7Clique para eliminar um jogador." }));
        invOp.setItem(11, utils.setItem(Material.COOKED_BEEF, 1, 0, "&eSaciar Fome &7(Clique)", new String[] { "§7Clique para saciar a fome de um jogador."}));
    }
}