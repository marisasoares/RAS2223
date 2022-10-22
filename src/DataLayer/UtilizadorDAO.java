package src.DataLayer;

public class UtilizadorDAO {
    private static final String DELETE = "DELETE FROM Utilizadores WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Utilizadores WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Utilizadores";
    private static final String REP_NUMBER = "SELECT * FROM Utilizadores WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Utilizadores WHERE id=?";
    private static final String INSERT = "INSERT INTO Utilizadores(username, password, email) VALUES(?, ?)";
}
