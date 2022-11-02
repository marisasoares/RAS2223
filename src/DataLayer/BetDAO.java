package DataLayer;

import Model.Bet;
import Model.Wallet;

import java.sql.*;

public class BetDAO {
    private static final String DELETE = "DELETE FROM Bet WHERE email=?";
    private static final String DELETE_ALL = "DELETE * FROM Bet WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Bet";
    private static final String REP_NUMBER = "SELECT * FROM Bet WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Bet WHERE email=?";
    private static final String INSERT = "INSERT INTO Bet(idBet,value,Email,Game_id) VALUES(?,?,?,?)";
    private static final String UPDATE = "UPDATE Bet SET Euros= ?, Dollars = ? WHERE Email=?";

    public static boolean store(Bet b) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, b.getBetId());
            stm.setFloat(2, b.getValue());
            stm.setString(3, b.getEmail());
            stm.setString(4, b.getGameId());
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

    public static Bet get(int id) {
        Bet b = null;

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {  // A chave existe na tabela
                b = new Bet(rs.getInt("idBet"),
                        rs.getString("Game_id"),
                        rs.getFloat("value"),
                        rs.getInt("BettedTeam"),
                        rs.getString("Email"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return b;
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

    /*
    public static void update(Bet bet){
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setFloat(1, bet.getEuros());
            stmt.setFloat(2, wallet.getDollars());
            stmt.setString(3, wallet.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
