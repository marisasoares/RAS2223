package com.rasbet.data;

import com.rasbet.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportGameDAO {
    private static final String DELETE = "DELETE FROM SportGame WHERE idSport=?";
    private static final String FIND_ALL_GAMES_BY_IDSPORT = "SELECT * FROM SportGame WHERE idSport= ?";
    private static final String FIND_BY_IDGame = "SELECT * FROM SportGame WHERE idGame=?";
    private static final String INSERT = "INSERT INTO SportGame(idSport,idGame) VALUES(?,?)";


    public static boolean store(Sport sp, Game gm) {
        Connection conn = null;
        boolean r = true;
        try {
            conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, sp.getId());
            stm.setString(2, gm.getId());
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
        return r ;
    }


    /**
     * Dado um id de desporto devolve a lista de jogos associados
     * @param idSport O id de desporto
     * @return A lista de jogos
     * */
    public static List<Game> get_AllGames_by_SportID(int idSport) {
        Connection conn = null;
        List<Game> games = new ArrayList<>();
        try {conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_ALL_GAMES_BY_IDSPORT);
            stm.setInt(1, idSport);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                games.add(GameDAO.get(rs.getString("idGame")));
            }
        } catch (Exception e) {
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
        return games;
    }

    /**
     * Dado um id de jogo devolve o id do desporto associado
     * @param idGame O id do jogo
     * @return O id do desporto
     * */
    public static int get_SportId_by_GameId(String idGame) {
        Connection conn = null;
        int sportId  = -1;
        try {conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_IDGame);
            stm.setString(1, idGame);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                sportId = rs.getInt("idSport");
            }
        } catch (SQLException e) {
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
        return sportId;
    }
}
