package com.rasbet.data;
import com.rasbet.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String DELETE = "DELETE FROM User WHERE Email=?";
    private static final String FIND_ALL_TRANFERS = "SELECT * FROM Transfer WHERE Email=?";
    private static final String FIND_ALL_USER_BY_TYPE= "SELECT * FROM User WHERE Type=?";
    private static final String FIND_BY_ID = "SELECT * FROM User WHERE Email=?";
    private static final String INSERT = "INSERT INTO User(Email, Name, PasswordHash,NIF,Type) VALUES(?,?,?,?,?)";
    private static final String UPDATE = "UPDATE User SET Name= ?, PasswordHash = ? WHERE Email=?";
    private static final String COUNT = "SELECT count(*) FROM User";

    public static boolean store(User user) {
        boolean r = true;
        try{
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
                stm.setString(4, "NaN");
                stm.setInt(5,1);
            }
            else {

                stm.setString(4, "NaN");
                stm.setInt(5,2);
            }
            stm.executeUpdate();
            if(user instanceof Better) WalletDAO.store(((Better) user).getWallet());
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
                        Wallet wallet = WalletDAO.get(((Better) user).getWallet().getEmail());
                        ((Better) user).setWallet(wallet);
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

    public static void update(User user){
        try {
            if(user instanceof Better) WalletDAO.update(((Better) user).getWallet());
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getPasswordHash());
            stmt.setString(3, user.getMail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Transfer> getTransHistory(String email) {
        List<Transfer> transfers = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(FIND_ALL_TRANFERS);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {  // A chave existe na tabela
                transfers.add(new Transfer(rs.getInt("idTransfer"),
                        rs.getFloat("Value"),
                        LocalDateTime.parse(rs.getString("Date")),
                        rs.getString("Description"),
                        rs.getString("Email")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transfers;
    }

    public static List<User> getUsersByType(int type) {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(FIND_ALL_USER_BY_TYPE);
            stmt.setInt(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {  // A chave existe na tabela
                users.add(new User(rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getInt("PasswordHash")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static int countUsers(){
        int i = 0;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(COUNT);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) i = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}