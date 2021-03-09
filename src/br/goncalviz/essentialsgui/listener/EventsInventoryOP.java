package br.goncalviz.essentialsgui.listener;

import br.goncalviz.essentialsgui.reflection.UtilsForAPIs;
import br.goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class EventsInventoryOP implements Listener {

    private final Utils utils = new Utils();
    private UtilsForAPIs utilsForAPIs = new UtilsForAPIs();
    public static List<Player> playersUtils = new ArrayList<>();
    public static List<Player> playersFeedHunger = new ArrayList<>();

    @EventHandler
    public void cancelEvent(InventoryClickEvent event) throws Exception {
        String nameInventoryOp = utils.withColor("&8EssentialsMenu - &cOP");
        if (event.getInventory().getTitle().equals(nameInventoryOp)) {
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                event.setCancelled(true);

                if(event.getCurrentItem().getType() == Material.AIR || event.getCurrentItem() == null) return;

                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(utils.withColor("&eEliminar um jogador &7(Clique)"))) {
                    if (!playersUtils.contains(player)) {
                        playersUtils.add(player);
                        player.closeInventory();
                        utilsForAPIs.sendActionBar(player, "&eQuem você deseja eliminar? Digite no chat.");
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 0.5F, 0.5F);

                    }
                }

                if(event.getCurrentItem().getItemMeta().getDisplayName().equals(utils.withColor("&eSaciar Fome &7(Clique)"))) {
                    if(!playersFeedHunger.contains(player)) {
                        playersFeedHunger.add(player);
                        player.closeInventory();
                        utilsForAPIs.sendActionBar(player, "&eQuem você deseja saciar a fome?");
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 0.5F, 0.5F);

                    }
                }
            }
        }
    }

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent event) throws Exception {
        String playerToKill = event.getMessage().toLowerCase();
        Player player = event.getPlayer();
        if(playersUtils.contains(player)) {
            event.setCancelled(true);
            for(Player playerExactOnline : Bukkit.getOnlinePlayers()) {
                if(playerToKill.contains(playerExactOnline.getName().toLowerCase())) {
                    playersUtils.remove(player);
                    playerExactOnline.setHealth(0.0);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);
                    utilsForAPIs.sendActionBar(player, "&aVocê eliminou o jogador: &n" + playerToKill + "&a com sucesso.");

                }else {
                    if(!playerToKill.contains(playerExactOnline.getName().toLowerCase())) {
                        utilsForAPIs.sendActionBar(player, "&cEsse jogador &lnão&c está online. Digite um jogador existe.");
                        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 0.5F, 0.5F);

                    }
                 }
            }
        }
    }
}
