package goncalviz.essentialsgui.register;

import goncalviz.essentialsgui.Main;
import goncalviz.essentialsgui.commands.EssentialsMenuCommand;
import goncalviz.essentialsgui.listener.Events;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class Register {

    private void setCommand(String command, CommandExecutor commandExecutor) {
        Bukkit.getPluginCommand(command).setExecutor(commandExecutor);

    }

    private void registerCommands() {
        setCommand("essentials", new EssentialsMenuCommand());

    }

    private void setEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Main.getPlugin(Main.class));

    }

    private void registerEvents() {
        setEvent(new Events());

    }

    public void forOnEnable() {
        registerCommands();
        registerEvents();

    }
}
