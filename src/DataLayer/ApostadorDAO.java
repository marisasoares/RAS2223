package DataLayer;

public class ApostadorDAO {
    private static final String DELETE = "DELETE FROM Apostadores WHERE id=?";
    private static final String DELETE_ALL = "DELETE * FROM Apostadores WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM Apostadores";
    private static final String REP_NUMBER = "SELECT * FROM Apostadores WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM Apostadores WHERE id=?";
    private static final String INSERT = "INSERT INTO Apostadores(username, password, email, apostas, carteira) VALUES(?, ?)";
}