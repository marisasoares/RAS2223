package DataLayer;

import java.sql.*;

public class JogoDAO {
    private static final String DELETE = "DELETE FROM Jogos WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Jogos WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Jogos";
    private static final String REP_NUMBER = "SELECT * FROM Jogos WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Jogos WHERE id=?";
    private static final String INSERT = "INSERT INTO Jogos(id, date, desporto, equipaDaCasa, equipaOponente, pontuacao ) VALUES(?, ?)";

    
}
