package com.rasbet.data;

import com.rasbet.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationAlertDAO {
    private static final String DELETE = "DELETE FROM NotificationAlert WHERE IdNot=?";
    private static final String FIND_BY_GAME = "SELECT * FROM NotificationAlert WHERE idGame=?";
    private static final String FIND_BY_EMAIL_GAME = "SELECT * FROM NotificationAlert WHERE Email=? and idGame=?";
    private static final String INSERT = "INSERT INTO NotificationAlert(idNotAlert,Email,idGame,isInterested) VALUES(?,?,?,?) ON DUPLICATE KEY UPDATE isInterested=?";
    private static final String UPDATE = "UPDATE NotificationAlert SET isInterested=? WHERE IdNot=? and idGame=?";

    public static boolean store(String email, String gameID,boolean isInterested) {
        int id = Utils.geraIdentificadorUnicoInteger(Utils.notifications);
        boolean r = true;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1,id);
            stm.setString(2,email);
            stm.setString(3,gameID);
            stm.setBoolean(4, isInterested);
            stm.setBoolean(5, isInterested);
            stm.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException s) {
            r = false;
        } catch (SQLException e) {
            r = false;
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        } finally {
            if(conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return r;
    }

    public static boolean store(String email, String gameID,boolean isInterested,int idNot) {
        boolean r = true;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1,idNot);
            stm.setString(2,email);
            stm.setString(3,gameID);
            stm.setBoolean(4, isInterested);
            stm.setBoolean(5, isInterested);
            stm.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException s) {
            r = false;
        } catch (SQLException e) {
            r = false;
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        } finally {
            if(conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return r;
    }

    public static NotAlert get(String email,String gameId) {
        NotAlert not = null;
        Connection conn = null;
        try { conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_EMAIL_GAME);
            stm.setString(1, email);
            stm.setString(2, gameId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                not = new NotAlert(rs.getString("Email"),
                        rs.getString("idGame"),
                        rs.getBoolean("isInterested"),
                        rs.getInt("idNotAlert"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return not;
    }

    public static void update(String email, String gameID) {
        NotAlert not = NotificationAlertDAO.get(email,gameID);
        if(not == null){
            NotificationAlertDAO.store(email,gameID,true);
        } else {
            NotificationAlertDAO.store(not.email, not.gameId, !not.isInterested, not.idNot);
        }
    }

    public static List<String> getAllInterestedUsers(String gameID) {
        List<String> users = new ArrayList<>();
        Connection conn = null;
        try { conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_GAME);
            stm.setString(1, gameID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {  // A chave existe na tabela
                if(rs.getBoolean("isInterested")){
                    users.add(rs.getString("Email"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return users;
    }

    public static class NotAlert{
        public String email;
        public String gameId;
        public boolean isInterested;
        public int idNot;

        public NotAlert(String email,String gameId,boolean isInterested,int idNot){
            this.email = email;
            this.gameId = gameId;
            this.isInterested = isInterested;
            this.idNot = idNot;
        }
    }
}