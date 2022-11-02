package DataLayer;

import Model.Game;
import Model.Sport;

import java.sql.*;

public class SportGameDAO {
    private static final String DELETE = "DELETE FROM SportGame WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM SportGame WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM SportGame";
    private static final String REP_NUMBER = "SELECT * FROM SportGame WHERE id=?";
    private static final String FIND_BY_IDSport = "SELECT * FROM SportGame WHERE idSport=?";
    private static final String FIND_BY_IDGame = "SELECT * FROM SportGame WHERE idGame=?";
    private static final String INSERT = "INSERT INTO SportGame(idSport,idGame) VALUES(?,?)";


    public static boolean add(Sport sp, Game gm) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, sp.getId());
            stm.setString(2, gm.getId());
            stm.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException s) {
            // erro ao inserir user reptido
            r = false;
        } catch (SQLException e) {
            r = false;
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r ;
    }

    public static Integer get_GameId_by_SportId(int idSport) {
        int gameId  = -1;
        try {Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_IDSport);
            stm.setInt(1, idSport);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                gameId = rs.getInt("idGame");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return gameId;
    }

    public static int get_SportId_by_GameId(String idGame) {
        int sportId  = -1;
        try {Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_IDGame);
            stm.setString(1, idGame);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                sportId = rs.getInt("idSport");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return sportId;
    }



}
