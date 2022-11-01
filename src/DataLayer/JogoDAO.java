package DataLayer;

import Model.Apostador;
import Model.Game;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JogoDAO {
    private static final String DELETE = "DELETE FROM Jogos WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Jogos WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Jogos";
    private static final String REP_NUMBER = "SELECT * FROM Jogos WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Jogos WHERE id=?";
    private static final String INSERT = "INSERT INTO Jogo(id , HomeTeam, AwayTeam, CommenceTime, Completed, ResultadoID) VALUES(?,?,?,?,?,?)";


    public static boolean store_Jogo(Game gm) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, gm.getId());
            stm.setString(2, gm.getHomeTeam());
            stm.setString(3, gm.getAwayTeam());
            stm.setString(4, gm.getCommenceTime().toString());
            stm.setInt(5, gm.getCompleted() ? 1:0);
            stm.setInt(6, gm.getResultado().getResultID());



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
        return r;
    }
    public static Game get(int id) {
        Game gm = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                LocalDateTime dateTime = LocalDateTime.parse(rs.getString("CommenceTime"), formatter);
                gm = new Game(rs.getInt("id"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        dateTime,
                        rs.getBoolean("Completed"),
                        rs.getString("Scores"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
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
