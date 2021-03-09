package br.goncalviz.essentialsgui.register;

import br.goncalviz.essentialsgui.Main;
import br.goncalviz.essentialsgui.commands.EssentialsInventoryCommand;
import br.goncalviz.essentialsgui.database.ConnectionSQL;
import br.goncalviz.essentialsgui.files.ConfigFile;
import br.goncalviz.essentialsgui.files.DatabaseFile;
import br.goncalviz.essentialsgui.listener.EventsDatabase;
import br.goncalviz.essentialsgui.listener.EventsInventoryOP;
import br.goncalviz.essentialsgui.utils.Utils;
import br.goncalviz.essentialsgui.files.MessagesFiles;
import br.goncalviz.essentialsgui.files.PrincipalFiles;
import br.goncalviz.essentialsgui.listener.Events;
import br.goncalviz.essentialsgui.listener.EventsInventory;
import br.goncalviz.essentialsgui.versionmanager.actionbar.VersionManagerActionBar;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class Register {

    private Utils utils = new Utils();
    private ConfigFile configFile = new ConfigFile();
    private PrincipalFiles principalFiles = new PrincipalFiles();
    private ConnectionSQL connectionSQL = new ConnectionSQL();
    private DatabaseFile databaseFile = new DatabaseFile();
    private MessagesFiles messagesFiles = new MessagesFiles();

    private void setCommand(String command, CommandExecutor commandExecutor) {
        Bukkit.getPluginCommand(command).setExecutor(commandExecutor);

    }

    private void registerCommands() {
        setCommand("essentials", new EssentialsInventoryCommand());

    }

    private void setEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Main.getPlugin(Main.class));

    }

    private void registerEvents() {
        setEvent(new Events());
        setEvent(new EventsDatabase());
        setEvent(new EventsInventory());
        setEvent(new EventsInventoryOP());


    }

    public void forOnEnable() {

        //Outros

        registerCommands();
        registerEvents();
        principalFiles.createNewFolder();
        configFile.createNewFileConfiguration();
        databaseFile.createNewDatabaseFile();
        messagesFiles.createNewMessagesFile();
        if(VersionManagerActionBar.setupVersionManagerActionBar()) {
            Bukkit.getConsoleSender().sendMessage(utils.withColor("&aVersionManagerActionBar foi carregado com sucesso! Valido para: 1.8_R3 e 1.9_R1"));
        }else{
            Bukkit.getConsoleSender().sendMessage(utils.withColor("&cErro ao carregar o VersionManagerActionBar. O plugin foi desligado."));
            Bukkit.getPluginManager().disablePlugin(Main.getPlugin(Main.class));
        }

        //Database
        if (configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
            connectionSQL.startConnectionWithDatabase();

        } else {
            if (!configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
                Bukkit.getConsoleSender().sendMessage(utils.getPrefix() + utils.withColor("&cA conexao com o banco de dados se encontra desativada."));

            }
        }

    }

    public void forOnDisable() {

        //Database
        if (configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
            connectionSQL.disableConnectionWithDatabase();
        }
    }

}
