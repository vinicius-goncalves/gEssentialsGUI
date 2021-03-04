package goncalviz.essentialsgui.register;

import goncalviz.essentialsgui.Main;
import goncalviz.essentialsgui.commands.EssentialsMenuCommand;
import goncalviz.essentialsgui.database.ConnectionSQL;
import goncalviz.essentialsgui.files.ConfigFile;
import goncalviz.essentialsgui.files.DatabaseFile;
import goncalviz.essentialsgui.files.MessagesFiles;
import goncalviz.essentialsgui.files.PrincipalFiles;
import goncalviz.essentialsgui.listener.Events;
import goncalviz.essentialsgui.listener.EventsDatabase;
import goncalviz.essentialsgui.utils.Utils;
import goncalviz.essentialsgui.versionmanager.actionbar.VersionManagerActionBar;
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
    private VersionManagerActionBar versionManagerActionBar = new VersionManagerActionBar();

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
        setEvent(new EventsDatabase());

    }

    public void forOnEnable() {

        //Outros

        registerCommands();
        registerEvents();
        principalFiles.createNewFolder();
        configFile.createNewFileConfiguration();
        databaseFile.createNewDatabaseFile();
        messagesFiles.createNewMessagesFile();
        if(versionManagerActionBar.setupVersionManagerActionBar()) {
            Bukkit.getConsoleSender().sendMessage("Sucesso");
        }else{
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
