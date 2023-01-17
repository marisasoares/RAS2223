package com.rasbet.data;

import com.rasbet.model.*;


import java.sql.*;

public class ResultDAO {
    private static final String DELETE = "DELETE FROM Result WHERE ResultId=?";
    private static final String FIND_BY_ID = "SELECT * FROM Result WHERE ResultId=?";
    private static final String INSERT = "INSERT INTO Result(ResultId,OddAway,OddHome,OddDraw,Score,WinningTeam) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Result SET Score= ?, OddAway=?, OddHome=?, OddDraw=?, WinningTeam=? WHERE ResultId=?";
    private static final String UPDATEScore = "UPDATE Result SET Score= ?, WinningTeam=? WHERE ResultId=?";

    public static boolean store(Result res) {
        boolean r = true;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, res.getResultID());
            stm.setFloat(2, res.getOddAwayTeam());
            stm.setFloat(3, res.getOddHomeTeam());
            stm.setFloat(4, res.getOddDraw());
            stm.setString(5, res.getScores());
            stm.setString(6, res.getwinningTeam());
            stm.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException s) {
            // erro ao inserir user reptido
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

    public static Result get(int id) {
        Result re = null;
        Connection conn = null;
        try { conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela

                re = new Result(rs.getInt("ResultId"),
                        rs.getFloat("oddAway"),
                        rs.getFloat("oddHome"),
                        rs.getFloat("oddDraw"),
                        rs.getString("winningTeam"),
                        rs.getString("score"));
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
        return re;
    }

    public static void delete(String ResultId) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, ResultId);
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


    public static boolean update(Result result) {
        boolean changed = false;
        Result antigo = ResultDAO.get(result.getResultID());
        if(!antigo.equals(result)) changed = true;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1,result.getScores());
            stmt.setFloat(2,result.getOddAwayTeam());
            stmt.setFloat(3,result.getOddHomeTeam());
            stmt.setFloat(4,result.getOddDraw());
            stmt.setString(5,result.getwinningTeam());
            stmt.setInt(6,result.getResultID());

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
        return changed;
    }
    public static void updateScore(Result result) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(UPDATEScore);

            stmt.setString(1,result.getScores());
            stmt.setString(2,result.getwinningTeam());
            stmt.setInt(3,result.getResultID());

            stmt.executeUpdate();
        } catch (Exception e) {
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
