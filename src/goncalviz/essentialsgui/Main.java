package goncalviz.essentialsgui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(sendColor("&aPlugin iniciado."));

    }

    public String sendColor(String mensagem) {
        return ChatColor.translateAlternateColorCodes('&', mensagem);

    }
}
