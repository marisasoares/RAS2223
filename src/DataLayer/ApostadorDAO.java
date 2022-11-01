package DataLayer;

import Model.Apostador;

import java.sql.*;

public class ApostadorDAO {
    private static final String DELETE = "DELETE FROM Apostador WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Apostador WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Apostador";
    private static final String REP_NUMBER = "SELECT * FROM Apostador WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Apostador WHERE id=?";
    private static final String INSERT = "INSERT INTO Apostador(Email, Nome, PasswordHash,NIF) VALUES(?,?,?,?)";

    public static boolean store_Apostador(Apostador ap) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, ap.getMail());
            stm.setString(2, ap.getNome());
            stm.setString(3, String.valueOf(ap.getPasswordHash()));
            stm.setString(4,ap.getNif());

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

    public static Apostador get(int id) {
        Apostador ap = null;

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                ap = new Apostador(rs.getString("Nome"),
                        rs.getString("mail"),
                        rs.getInt("passwordHash"),
                        rs.getString("nif"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return ap;
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