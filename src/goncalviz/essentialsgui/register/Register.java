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
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class Register {

    private ConfigFile configFile = new ConfigFile();
    private PrincipalFiles principalFiles = new PrincipalFiles();
    private ConnectionSQL connectionSQL = new ConnectionSQL();
    private DatabaseFile databaseFile = new DatabaseFile();
    private MessagesFiles messagesFiles = new MessagesFiles();

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

        //Database
        if (configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
            connectionSQL.startConnectionWithDatabase();

        }else {
            if(!configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
                System.out.println("A conexao com o banco de dados foi desativada.");

            }
        }
    }

    public void forOnDisable() {

        //Database
        if (configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
            connectionSQL.disableConnectionWithDatabase();
        }else {
            if(!configFile.getFileConfiguration().getBoolean("bancoDeDados")) {
                System.out.println("A conexao com o banco de dados nao esta ativada.");

            }
        }
    }
}
