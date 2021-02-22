package goncalviz.essentialsgui.database;

import goncalviz.essentialsgui.files.DatabaseFile;
import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionSQL {

    public static Connection connection;
    private ConsoleCommandSender cs = Bukkit.getConsoleSender();
    private Utils utils = new Utils();
    private FileConfiguration databaseFile = new DatabaseFile().getDatabaseFile();

    private String getHost = databaseFile.getString("host");
    private String getPort = databaseFile.getString("port");
    private String getDatabase = databaseFile.getString("database");
    private String getUser = databaseFile.getString("user");
    private String getPassword = databaseFile.getString("password");

    public void startConnectionWithDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"
                    + getHost + ":"
                    + getPort + "/"
                    + getDatabase, getUser, getPassword);
            this.createTableInDatabase();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void disableConnectionWithDatabase() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();

        }
    }

    public void createTableInDatabase() {
        try {
            PreparedStatement ps;
            ps = connection.prepareStatement("create table if not exists `jogadores`(`id` int not null auto_increment, `jogador` varchar(30), `vezesAberto` int null, primary key (id))");
            ps.executeUpdate();
            cs.sendMessage(utils.withColor("&aA tabela 'jogadores' foi criada (ou) carregada com sucesso no banco de dados."));
        }catch(SQLException e) {
            cs.sendMessage(utils.withColor("&cOcorreu um erro ao criar (ou) carregar a tabela 'jogadores', tente novamente."));
            e.printStackTrace();
        }
    }
}
