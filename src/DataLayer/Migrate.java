package DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Migrate {
    public static void createTables() throws ClassNotFoundException {
            try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Utilizador (" +
                        "idUtilizador INT NOT NULL PRIMARY KEY,"  +
                        "Nome VARCHAR(45) NOT NULL," +
                        "Email VARCHAR(75) NOT NULL," +
                        "PasswordHash INT NOT NULL," +
                        "Tipo TINYINT(2) NOT NULL," +
                        "NIF VARCHAR(45) NOT NULL";

            stm.executeUpdate(sql);

            String sqlR = "CREATE TABLE IF NOT EXISTS Resultado (" +
                    "ResultadoID VARCHAR(45) NOT NULL PRIMARY KEY," +
                    "OddCasa FLOAT NOT NULL,"+
                    "OddDraw FLOAT NOT NULL," +
                    "OddAway FLOAT NOT NULL," +
                    "Score VARCHAR(45) NOT NULL," +
                    "EquipaVencedora VARCHAR(45) NOT NULL";

            stm.executeUpdate(sqlR);

            String sqlJ = "CREATE TABLE IF NOT EXISTS Jogo (" +
                    "idJogo INT NOT NULL PRIMARY KEY ," +
                    "HomeTeam VARCHAR(45) NOT NULL," +
                    "AwayTeam VARCHAR(45) NOT NULL," +
                    "CommenceTime VARCHAR(45) NOT NULL," +
                    "Completed VARCHAR(45) NOT NULL," +
                    "ResultadoId VARCHAR(45) NOT NULL PRIMARY KEY ," +
                    "FOREIGN KEY (ResultadoId)" +
                    "    REFERENCES Resultado(ResultadoID))";
            stm.executeUpdate(sqlJ);

            String sqlAP = "CREATE TABLE IF NOT EXISTS Aposta (" +
                    "idAposta INT NOT NULL PRIMARY KEY,"  +
                    "Value FLOAT NOT NULL," +
                    "Utilizador_id INT NOT NULL PRIMARY KEY ," +
                    "Jogo_id INT NOT NULL PRIMARY KEY ," +
                    "FOREIGN KEY (Utilizador_id)" +
                            "REFERENCES Utilizador(idUtilizador)," +
                    " FOREIGN KEY (Jogo_id)" +
                            "REFERENCES Jogo(idJogo))";
            stm.executeUpdate(sqlAP);

            String sqlCart = "CREATE TABLE IF NOT EXISTS Carteira (" +
                    "idUtilizador INT NOT NULL PRIMARY KEY ," +
                    "Euros VARCHAR(45) NOT NULL," +
                    "Dollars VARCHAR(45) NOT NULL," +
                    "FOREIGN KEY (idUtilizador)" +
                    "    REFERENCES Utilizador(idUtilizador))";
            stm.executeUpdate(sqlCart);


        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        createTables();
    }
}