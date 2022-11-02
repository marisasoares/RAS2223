package DataLayer;

import Model.Game;
import Model.Sport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportGameDAO {
    private static final String DELETE = "DELETE FROM SportGame WHERE idSport=?";
    private static final String DELETE_ALL = "DELETE * FROM SportGame WHERE idSport=?";
    private static final String FIND_ALL_GAMES_BY_IDSPORT = "SELECT * FROM SportGame WHERE idSport= ?";
    private static final String REP_NUMBER = "SELECT * FROM SportGame WHERE idSport=?";
    private static final String FIND_BY_IDSport = "SELECT * FROM SportGame WHERE idSport=?";
    private static final String FIND_BY_IDGame = "SELECT * FROM SportGame WHERE idGame=?";
    private static final String INSERT = "INSERT INTO SportGame(idSport,idGame) VALUES(?,?)";


    public static boolean store(Sport sp, Game gm) {
        boolean r = true;
        try {
            Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
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
        }
        return r ;
    }


    /**
     * Dado um id de desporto devolve a lista de jogos associados
     * @param idSport O id de desporto
     * @return A lista de jogos
     * */
    public static List<Game> get_AllGames_by_SportID(int idSport) {
        List<Game> games = new ArrayList<>();
        try {Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_ALL_GAMES_BY_IDSPORT);
            stm.setInt(1, idSport);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                games.add(GameDAO.get(rs.getString("idGame")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return games;
    }

    /**
     * Dado um id de jogo devolve o id do desporto associado
     * @param idGame O id do jogo
     * @return O id do desporto
     * */
    public static int get_SportId_by_GameId(String idGame) {
        int sportId  = -1;
        try {Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            PreparedStatement stm = conn.prepareStatement(FIND_BY_IDGame);
            stm.setString(1, idGame);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {  // A chave existe na tabela
                sportId = rs.getInt("idSport");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return sportId;
    }





}
