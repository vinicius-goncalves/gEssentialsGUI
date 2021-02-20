package goncalviz.essentialsgui.database;

import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionSQL {

    public static Connection connection;
    private ConsoleCommandSender cs = Bukkit.getConsoleSender();
    private Utils utils = new Utils();

    public void startConnectionWithDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gEssentials", "root", "");
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
            ps = connection.prepareStatement("create table if not exists `jogadores`(`jogador` varchar(30), `vezesAberto` int null)");
            ps.executeUpdate();
            cs.sendMessage(utils.withColor("&aA tabela 'jogadores' foi criada com sucesso no banco de dados."));
        }catch(SQLException e) {
            cs.sendMessage(utils.withColor("&cOcorreu um erro ao criar a tabela 'jogadores', tente novamente."));
            e.printStackTrace();
        }
    }
}
