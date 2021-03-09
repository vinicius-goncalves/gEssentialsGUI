package br.goncalviz.essentialsgui;

import br.goncalviz.essentialsgui.register.Register;
import br.goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static JavaPlugin instance;
    private Register register = new Register();
    private Utils utils = new Utils();
    private PluginDescriptionFile pluginDescriptionFile = getDescription();

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage(utils.withColor("&aO plugin '" + pluginDescriptionFile.getName() + "' foi iniciado."));
        register.forOnEnable();


    }

    @Override
    public void onDisable() {
        register.forOnDisable();

    }
}