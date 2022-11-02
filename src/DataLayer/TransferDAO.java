package DataLayer;

import Model.*;

import java.sql.*;
import java.time.LocalDateTime;

public class TransferDAO {
    private static final String DELETE = "DELETE FROM Transfer WHERE idTransfer=?";
    private static final String DELETE_ALL = "DELETE * FROM Transfer WHERE idTransfer=?";
    private static final String FIND_ALL = "SELECT * FROM Transfer";
    private static final String REP_NUMBER = "SELECT * FROM Transfer WHERE idTransfer=?";
    private static final String FIND_BY_ID = "SELECT * FROM Transfer WHERE idTransfer=?";
    private static final String INSERT = "INSERT INTO Transfer(idTransfer,Value,Date,Description,Email) VALUES(?,?,?,?,?)";

    public static boolean store(Transfer ts) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, ts.getId());
            stm.setFloat(2, ts.getValue());
            stm.setString(3, ts.getDate().toString());
            stm.setString(4, ts.getDescription());
            stm.setString(5,ts.getEmail());
            stm.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException s) {
            // erro ao inserir user reptido
            s.printStackTrace();
            r = false;
        } catch (SQLException e) {
            r = false;
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
    public static Transfer get(String idTransfer) {
        Transfer trans = null;
        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setString(1, idTransfer);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                trans = new Transfer(rs.getInt("idTransfer"),
                        rs.getFloat("Value"),
                        LocalDateTime.parse(rs.getString("Date")),
                        rs.getString("Description"),
                        rs.getString("Email"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return trans;
    }

    public static void delete(String idTransfer) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, idTransfer);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
