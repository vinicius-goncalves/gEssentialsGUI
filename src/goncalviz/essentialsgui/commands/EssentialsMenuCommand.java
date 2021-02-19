package goncalviz.essentialsgui.commands;

import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EssentialsMenuCommand implements CommandExecutor {

    private final Utils utils = new Utils();
    private final EssentialsMenu essentialsMenu = new EssentialsMenu();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(utils.withColor("&cApenas para jogadores in-game."));
            return true;

        }

        Player player = (Player) sender;
        essentialsMenu.openInventory(player);
        player.sendMessage(utils.withColor("&aVocê abriu o menu com sucesso"));
        player.playSound(player.getLocation(), Sound.BURP, 0.5F, 0.5F);
        return false;

    }

}
