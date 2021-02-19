package goncalviz.essentialsgui;

import goncalviz.essentialsgui.register.Register;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static JavaPlugin instance;
    private Register register = new Register();

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage(sendColor("&aPlugin iniciado."));
        register.forOnEnable();

    }

    public String sendColor(String mensagem) {
        return ChatColor.translateAlternateColorCodes('&', mensagem);

    }
}
