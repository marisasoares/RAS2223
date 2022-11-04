package DataLayer;

import Model.Bet;
import Model.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BetDAO {
    private static final String DELETE = "DELETE FROM Bet WHERE email=?";
    private static final String FIND_BY_ID = "SELECT * FROM Bet WHERE idBet=?";
    private static final String FIND_BY_Gameid = "SELECT * FROM Bet WHERE Game_id=?";
    private static final String FIND_BY_Email= "SELECT * FROM Bet WHERE Email=?";
    private static final String INSERT = "INSERT INTO Bet(idBet,value,Email,Game_id,BettedTeam,MultipleId,IsSuspended,BetState,Currency) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Bet SET value=?,Game_id=?,BettedTeam=?,MultipleId=?,IsSuspended=?,BetState=?,Currency=? WHERE idBet=?";

    public static boolean store(Bet b) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, b.getBetId());
            stm.setFloat(2, b.getValue());
            stm.setString(3, b.getEmail());
            stm.setString(4, b.getGameId());
            stm.setInt(5,b.getBettedTeam());
            stm.setInt(6,b.getMultipleId());
            stm.setBoolean(7,b.getisSuspended());
            stm.setInt(8,b.getBetState());
            stm.setString(9,b.getCurrency());
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

    public static Bet get(int betid) {
        Bet b = null;

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, betid);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {  // A chave existe na tabela
                b = new Bet(rs.getInt("idBet"),
                        rs.getString("Game_id"),
                        rs.getFloat("value"),
                        rs.getInt("BettedTeam"),
                        rs.getString("Email"),
                        rs.getInt("MultipleId"),
                        rs.getBoolean("IsSuspended"),
                        rs.getInt("BetState"),
                        rs.getString("Currency"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return b;
    }

    public static List<Bet> getBetsByEmail(String email) {
        List<Bet> bets = new ArrayList<>();

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                PreparedStatement stm = conn.prepareStatement(FIND_BY_Email);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {  // A chave existe na tabela
                bets.add( new Bet(rs.getInt("idBet"),
                        rs.getString("Game_id"),
                        rs.getFloat("value"),
                        rs.getInt("BettedTeam"),
                        rs.getString("Email"),
                        rs.getInt("MultipleId"),
                        rs.getBoolean("IsSuspended"),
                        rs.getInt("BetState"),
                        rs.getString("Currency")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return bets;
    }

    public static List<Bet> getBetsByGameId(String gameid) {
        List<Bet> bets = new ArrayList<>();

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_Gameid);
            stm.setString(1, gameid);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {  // A chave existe na tabela
                bets.add( new Bet(rs.getInt("idBet"),
                        rs.getString("Game_id"),
                        rs.getFloat("value"),
                        rs.getInt("BettedTeam"),
                        rs.getString("Email"),
                        rs.getInt("MultipleId"),
                        rs.getBoolean("IsSuspended"),
                        rs.getInt("BetState"),
                        rs.getString("Currency")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return bets;
    }


    public static void delete(int betId) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, betId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void update(Bet b){
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(UPDATE);
            stm.setFloat(1, b.getValue());
            stm.setString(2, b.getGameId());
            stm.setInt(3,b.getBettedTeam());
            stm.setInt(4,b.getMultipleId());
            stm.setBoolean(5,b.getisSuspended());
            stm.setInt(6,b.getBetState());
            stm.setString(7,b.getCurrency());
            stm.setInt(8,b.getBetId());

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
