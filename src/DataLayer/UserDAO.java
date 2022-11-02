package DataLayer;
import Model.Administrator;
import Model.Better;
import Model.Specialist;
import Model.User;

import java.sql.*;

public class UserDAO {
    private static final String DELETE = "DELETE FROM User WHERE Email=?";
    private static final String DELETE_ALL = "DELETE * FROM User WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM User";
    private static final String REP_NUMBER = "SELECT * FROM User WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM User WHERE Email=?";
    private static final String INSERT = "INSERT INTO User(Email, Name, PasswordHash,NIF,Type) VALUES(?,?,?,?,?)";

    public static boolean store(User user) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, user.getMail());
            stm.setString(2, user.getName());
            stm.setString(3, String.valueOf(user.getPasswordHash()));
            if(user instanceof Better){
                stm.setString(4, ((Better) user).getNif());
                stm.setInt(5,0);
            }
            else if (user instanceof Specialist) {
                stm.setString(4, null);
                stm.setInt(5,1);
            }
            else {
                stm.setString(4, null);
                stm.setInt(5,2);
            }
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

    public static User get(String email) {
        User user = null;

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                switch (rs.getInt("Type")){
                    case 1:
                        user = new Specialist(rs.getString("Name"),
                                rs.getString("Email"),
                                rs.getInt("PasswordHash"));
                        break;
                    case 2:
                        user = new Administrator(rs.getString("Name"),
                                rs.getString("Email"),
                                rs.getInt("PasswordHash"));
                        break;
                    default:
                        user = new Better(rs.getString("Name"),
                                rs.getString("Email"),
                                rs.getInt("PasswordHash"),
                                rs.getString("NIF"));
                        break;
                }
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return user;
    }

    public static void delete(String email) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}