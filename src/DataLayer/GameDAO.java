package DataLayer;

import Model.Better;
import Model.Game;

import java.sql.*;

public class GameDAO {
    private static final String DELETE = "DELETE FROM Game WHERE idGame=?";
    private static final String DELETE_ALL = "DELETE * FROM Game WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Game";
    private static final String REP_NUMBER = "SELECT * FROM Game WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Game WHERE idGame=?";
    private static final String INSERT = "INSERT INTO Game(idGame, HomeTeam, AwayTeam, CommenceTime, Completed, ResultID) VALUES(?,?,?,?,?,?)";


    public static boolean store(Game gm) {
        boolean r = true;
        try {
            ResultDAO.store(gm.getResult());
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, gm.getId());
            stm.setString(2, gm.getHomeTeam());
            stm.setString(3, gm.getAwayTeam());
            stm.setString(4, gm.getCommenceTime());
            stm.setInt(5, gm.getCompleted() ? 1:0);
            stm.setInt(6, gm.getResult().getResultID());
            stm.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException s) {
            r = false;
        } catch (SQLException e) {
            r = false;
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
    public static Game get(String id) {
        Game gm = null;

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
           PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {  // A chave existe na tabela
                int sportId = SportGameDAO.get_SportId_by_GameId(rs.getString("idGame"));
                gm = new Game(rs.getString("idGame"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("CommenceTime"),
                        rs.getBoolean("Completed"),
                        rs.getString("Score"),sportId);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return gm;
    }

    public static void delete(int id) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
