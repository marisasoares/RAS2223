package DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Migrate {
    public static void createTables() throws ClassNotFoundException {
            try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
                String disableForeignChecks = "SET FOREIGN_KEY_CHECKS=0";
                stm.executeUpdate(disableForeignChecks);
            String limpar = "DROP TABLE IF EXISTS Utilizador, " +
                            "Resultado," +
                            "Jogo," +
                            "Aposta," +
                            "Carteira," +
                            "Desporto," +
                            "DesportoJogo;";

            stm.executeUpdate(limpar);
            String enableForeignChecks = "SET FOREIGN_KEY_CHECKS=1";
            stm.executeUpdate(enableForeignChecks);



            String sql = "CREATE TABLE IF NOT EXISTS Utilizador (" +
                        "Email VARCHAR(75) NOT NULL PRIMARY KEY," +
                        "Nome VARCHAR(45) NOT NULL," +
                        "PasswordHash VARCHAR(45) NOT NULL," +
                        "NIF VARCHAR(45) NOT NULL)";

            stm.executeUpdate(sql);

            String sqlR = "CREATE TABLE IF NOT EXISTS Resultado (" +
                    "ResultadoID INT NOT NULL PRIMARY KEY," +
                    "OddCasa FLOAT NOT NULL,"+
                    "OddDraw FLOAT NOT NULL," +
                    "OddAway FLOAT NOT NULL," +
                    "Score VARCHAR(45) NOT NULL," +
                    "EquipaVencedora VARCHAR(45) NOT NULL)";

            stm.executeUpdate(sqlR);

            String sqlJ = "CREATE TABLE IF NOT EXISTS Jogo (" +
                    "idJogo INT NOT NULL PRIMARY KEY ," +
                    "HomeTeam VARCHAR(45) NOT NULL," +
                    "AwayTeam VARCHAR(45) NOT NULL," +
                    "CommenceTime VARCHAR(45) NOT NULL," +
                    "Completed TINYINT NOT NULL," +
                    "ResultadoId INT NOT NULL," +
                    "FOREIGN KEY (ResultadoId) REFERENCES Resultado(ResultadoID))";
            stm.executeUpdate(sqlJ);

            String sqlAP = "CREATE TABLE IF NOT EXISTS Aposta (" +
                    "idAposta INT NOT NULL PRIMARY KEY,"  +
                    "Value FLOAT NOT NULL," +
                    "Email VARCHAR(75) NOT NULL ," +
                    "Jogo_id INT NOT NULL ," +
                    "FOREIGN KEY (Email) REFERENCES Utilizador(Email)," +
                    "FOREIGN KEY (Jogo_id) REFERENCES Jogo(idJogo))";
            stm.executeUpdate(sqlAP);

            String sqlCart = "CREATE TABLE IF NOT EXISTS Carteira (" +
                    "Email VARCHAR(75) NOT NULL PRIMARY KEY ," +
                    "Euros VARCHAR(45) NOT NULL," +
                    "Dollars VARCHAR(45) NOT NULL," +
                    "FOREIGN KEY (Email) REFERENCES Utilizador(Email))";
            stm.executeUpdate(sqlCart);

            String sqlD = "CREATE TABLE IF NOT EXISTS Desporto (" +
                        "idDesporto INT NOT NULL PRIMARY KEY ," +
                        "Nome VARCHAR(45) NOT NULL)";
            stm.executeUpdate(sqlD);

            String sqlDJ = "CREATE TABLE IF NOT EXISTS DesportoJogo (" +
                        "idDesporto INT NOT NULL," +
                        "idJogo INT NOT NULL,"+
                        "FOREIGN KEY (idDesporto) References Desporto(idDesporto)," +
                        "FOREIGN KEY (idJogo) References Jogo(idJogo))";
            stm.executeUpdate(sqlDJ);


        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        createTables();
        System.out.println("Database Migrada");
    }
}