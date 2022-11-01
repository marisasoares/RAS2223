package DataLayer;

import Model.Apostador;
import Model.Sport;

import java.sql.*;

public class sportDAO {
    private static final String DELETE = "DELETE FROM Desporto WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Desporto WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Desporto";
    private static final String REP_NUMBER = "SELECT * FROM Desporto WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Desporto WHERE id=?";
    private static final String INSERT = "INSERT INTO Desporto(id , Nome) VALUES(?,?)";

    public static boolean store_Desporto(Sport sp) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, sp.getId());
            stm.setString(2, sp.getNome());

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
