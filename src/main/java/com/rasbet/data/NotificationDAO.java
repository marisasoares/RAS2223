package com.rasbet.data;

import com.rasbet.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    private static final String DELETE = "DELETE FROM Notification WHERE IdNot=?";
    private static final String FIND_BY_ID = "SELECT * FROM Notification WHERE IdNot=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM Notification WHERE Email=?";
    private static final String INSERT = "INSERT INTO Notification(IdNot,Email,Content,IsRead,Date) VALUES(?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Notification SET Content=?, IsRead=? WHERE IdNot=?";


    public static boolean store(Notification not) {
        boolean r = true;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1,not.getId());
            stm.setString(2, not.getEmail());
            stm.setString(3, not.getContent());
            stm.setBoolean(4, not.getIsRead());
            stm.setString(5,not.getDate().toString());
            stm.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException s) {
            r = false;
        } catch (SQLException e) {
            r = false;
            // Database error!
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

    public static Notification get(int id) {
        Notification not = null;
        Connection conn = null;
        try { conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                not = new Notification(rs.getInt("IdNot"),
                        rs.getString("Email"),
                        rs.getString("Content"),
                        rs.getBoolean("IsRead"),
                        LocalDateTime.parse(rs.getString("Date")));
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

    public static List<Notification> get(String email) {
        List<Notification> notifications = new ArrayList<>();
        Notification not = null;
        Connection conn = null;
        try { conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_EMAIL);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {  // A chave existe na tabela
                not = new Notification(rs.getInt("IdNot"),
                        rs.getString("Email"),
                        rs.getString("Content"),
                        rs.getBoolean("IsRead"),
                        LocalDateTime.parse(rs.getString("Date")));
                notifications.add(not);
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
        return notifications;
    }

    public static void delete(String notID) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, notID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void update(Notification not) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1,not.getContent());
            stmt.setBoolean(2,not.getIsRead());
            stmt.setInt(3,not.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}