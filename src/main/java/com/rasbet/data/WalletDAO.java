package com.rasbet.data;

import com.rasbet.model.*;


import java.sql.*;

public class WalletDAO {
    private static final String DELETE = "DELETE FROM Wallet WHERE Email=?";
    private static final String FIND_BY_ID = "SELECT * FROM Wallet WHERE Email=?";
    private static final String INSERT = "INSERT INTO Wallet(Email,Euros,Dollars) VALUES(?,?,?)";
    private static final String UPDATE = "UPDATE Wallet SET Euros= ?, Dollars = ? WHERE Email=?";

    public static boolean store(Wallet w) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, w.getEmail());
            stm.setFloat(2, w.getEuros());
            stm.setFloat(3, w.getDollars());
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

    public static Wallet get(String email) {
        Wallet w = null;

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {  // A chave existe na tabela
                w = new Wallet(rs.getString("Email"),
                        rs.getFloat("Euros"),
                        rs.getFloat("Dollars"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return w;
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

    public static void update(Wallet wallet){
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setFloat(1, wallet.getEuros());
            stmt.setFloat(2, wallet.getDollars());
            stmt.setString(3, wallet.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
