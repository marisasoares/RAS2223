package com.rasbet.data;

import com.rasbet.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SportDAO {
    private static final String DELETE = "DELETE FROM Sport WHERE idSport=?";
    private static final String FIND_ALL = "SELECT * FROM Sport";
    private static final String FIND_BY_ID = "SELECT * FROM Sport WHERE idSport=?";
    private static final String INSERT = "INSERT INTO Sport(idSport , Name) VALUES(?,?)";

    public static boolean store(Sport sp) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, sp.getId());
            stm.setString(2, sp.getNome());
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

    public static Sport get(int id) {
        Sport sp = null;

        try { Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                sp = new Sport(id,rs.getString("Name"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return sp;
    }

    public static void delete(String nome) {
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Sport> getSportList() {
        List<Sport> sports = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sports.add(new Sport(rs.getInt("idSport"),rs.getString("Name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sports;
    }

}
