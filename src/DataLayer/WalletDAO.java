package DataLayer;

import Model.Sport;
import Model.Wallet;

import java.sql.*;

public class WalletDAO {
    private static final String DELETE = "DELETE FROM Desporto WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Desporto WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Desporto";
    private static final String REP_NUMBER = "SELECT * FROM Desporto WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Desporto WHERE id=?";
    private static final String INSERT = "INSERT INTO Wallet(id,euros,dollars) VALUES(?,?,?)";

    public static boolean store(Wallet w) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, w.getId());
            stm.setFloat(2, w.getEuros());
            stm.setFloat(3, w.getDollars());
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

    public static void delete(String id) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
