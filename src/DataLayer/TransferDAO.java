package DataLayer;

import Model.Sport;
import Model.Transfer;

import java.sql.*;
import java.time.LocalDateTime;

public class TransferDAO {
    private static final String DELETE = "DELETE FROM Transfer WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Transfer WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Transfer";
    private static final String REP_NUMBER = "SELECT * FROM Transfer WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Transfer WHERE id=?";
    private static final String INSERT = "INSERT INTO Transfer(id,value,date,description) VALUES(?,?,?,?)";

    public static boolean store(Transfer ts) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, ts.getId());
            stm.setFloat(2, ts.getValue());
            stm.setString(3, ts.getDate().toString());
            stm.setString(4, ts.getDescription());

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
