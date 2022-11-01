package DataLayer;

import Model.Apostador;
import Model.Game;

import java.sql.*;

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


}
