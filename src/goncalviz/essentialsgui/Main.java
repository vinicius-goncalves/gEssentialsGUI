package goncalviz.essentialsgui;

import goncalviz.essentialsgui.register.Register;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static JavaPlugin instance;
    private Register register = new Register();

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage("Plugin iniciado.");
        register.forOnEnable();

    }

    @Override
    public void onDisable() {
        register.forOnDisable();

    }
}
