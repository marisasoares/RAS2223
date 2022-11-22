package com.rasbet.data;

import com.rasbet.model.*;

import java.sql.*;
import java.time.LocalDateTime;

public class TransferDAO {
    private static final String DELETE = "DELETE FROM Transfer WHERE idTransfer=?";
    private static final String FIND_BY_ID = "SELECT * FROM Transfer WHERE idTransfer=?";
    private static final String INSERT = "INSERT INTO Transfer(idTransfer,Value,Date,Description,Email) VALUES(?,?,?,?,?)";

    public static boolean store(Transfer ts) {
        Connection conn = null;
        boolean r = true;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
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
    public static Transfer get(String idTransfer) {
        Connection conn = null;
        Transfer trans = null;
        try { conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
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
        } finally {
            if(conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return trans;
    }

    public static void delete(String idTransfer) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, idTransfer);
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
