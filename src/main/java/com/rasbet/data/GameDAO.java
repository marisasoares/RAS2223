package com.rasbet.data;

import com.rasbet.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {
    private static final String DELETE = "DELETE FROM Game WHERE idGame=?";
    private static final String FIND_BY_ID = "SELECT * FROM Game WHERE idGame=?";
    private static final String INSERT = "INSERT INTO Game(idGame, HomeTeam, AwayTeam, CommenceTime, Completed, ResultID) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Game SET Completed= ? WHERE idGame=?";
    private static final String FIND_ALL = "SELECT * FROM Game";

    public static boolean store(Game game) {
        boolean r = true;
        Connection conn = null;
        try {
            ResultDAO.store(game.getResult());
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, game.getId());
            stm.setString(2, game.getHomeTeam());
            stm.setString(3, game.getAwayTeam());
            stm.setString(4, game.getCommenceTime());
            stm.setInt(5, game.getCompleted() ? 1:0);
            stm.setInt(6, game.getResult().getResultID());
            stm.executeUpdate();
            SportGameDAO.store(SportDAO.get(game.getSportId()),game);
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
    public static Game get(String id) {
        Game game = null;
        Connection conn = null;
        try { conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
           PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {  // A chave existe na tabela
                int sportId = SportGameDAO.get_SportId_by_GameId(rs.getString("idGame"));
                Result result = ResultDAO.get(rs.getInt("ResultID"));
                game = new Game(rs.getString("idGame"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("CommenceTime"),
                        rs.getBoolean("Completed"),
                        result,
                        sportId);
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
        return game;
    }

    public static List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        Connection conn = null;
        try { conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(FIND_ALL);

            while (rs.next()) {  // A chave existe na tabela
                int sportId = SportGameDAO.get_SportId_by_GameId(rs.getString("idGame"));
                Result result = ResultDAO.get(rs.getInt("ResultID"));
                games.add(new Game(rs.getString("idGame"),
                        rs.getString("HomeTeam"),
                        rs.getString("AwayTeam"),
                        rs.getString("CommenceTime"),
                        rs.getBoolean("Completed"),
                        result,
                        sportId));
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
        return games;
    }

    public static void delete(int id) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);
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

    public static void updateStatusGame(Game game){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setInt(1, game.getCompleted() ? 1:0);
            stmt.setString(2, game.getId());
            stmt.executeUpdate();
            Result result = game.getResult();
            ResultDAO.updateScore(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void update(Game game) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setInt(1, game.getCompleted() ? 1:0);
            stmt.setString(2, game.getId());
            stmt.executeUpdate();
            Result result = game.getResult();
            ResultDAO.update(result);
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
