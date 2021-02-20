package goncalviz.essentialsgui.database;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilsSQL extends ConnectionSQL {

    public boolean containsPlayer(Player player) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `jogadores` where `jogador` = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;

            }
            return false;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setPlayer(Player player) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("insert into `jogadores`(`jogador`, `vezesAberto`) values (?,?)");
            ps.setString(1, player.getName());
            ps.setInt(2, 0);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void setClick(Player player, int howManyClicks) {
        if (containsPlayer(player)) {
            PreparedStatement ps;
            try {
                ps = connection.prepareStatement("update set `vezesAberto` = ? where `jogador` = ?");
                ps.setInt(1, howManyClicks);
                ps.setString(2, player.getName());
                ps.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
                ;
            }
        }
    }

    public int getClicks(Player player) {
        if (containsPlayer(player)) {
            PreparedStatement ps;
            try {
                ps = connection.prepareStatement("select * from `jogadores` where = ?");
                ps.setString(1, player.getName());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt("vezesAberto");
                }
                return 0;
            } catch(SQLException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            setPlayer(player);
            return 0;
        }
    }
}
