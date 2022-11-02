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
            String limpar = "DROP TABLE IF EXISTS User, " +
                            "Result," +
                            "Game," +
                            "Bet," +
                            "Wallet," +
                            "Sport," +
                            "SportGame;";

            stm.executeUpdate(limpar);
            String enableForeignChecks = "SET FOREIGN_KEY_CHECKS=1";
            stm.executeUpdate(enableForeignChecks);



            String sql = "CREATE TABLE IF NOT EXISTS User (" +
                        "Email VARCHAR(75) NOT NULL PRIMARY KEY," +
                        "Name VARCHAR(45) NOT NULL," +
                        "PasswordHash INT NOT NULL," +
                        "NIF VARCHAR(45) NOT NULL," +
                        "Type TINYINT NOT NULL)";

            stm.executeUpdate(sql);

            String sqlR = "CREATE TABLE IF NOT EXISTS Result (" +
                    "ResultID INT NOT NULL PRIMARY KEY," +
                    "OddHome FLOAT NOT NULL,"+
                    "OddDraw FLOAT NOT NULL," +
                    "OddAway FLOAT NOT NULL," +
                    "Score VARCHAR(45) NOT NULL," +
                    "WinningTeam VARCHAR(45) NOT NULL)";

            stm.executeUpdate(sqlR);

            String sqlJ = "CREATE TABLE IF NOT EXISTS Game (" +
                    "idGame VARCHAR(75) NOT NULL PRIMARY KEY ," +
                    "HomeTeam VARCHAR(45) NOT NULL," +
                    "AwayTeam VARCHAR(45) NOT NULL," +
                    "CommenceTime VARCHAR(45) NOT NULL," +
                    "Completed TINYINT NOT NULL," +
                    "Score VARCHAR(45)," +
                    "ResultId INT NOT NULL," +
                    "FOREIGN KEY (ResultId) REFERENCES Result(ResultID))";
            stm.executeUpdate(sqlJ);

            String sqlAP = "CREATE TABLE IF NOT EXISTS Bet (" +
                    "idBet INT NOT NULL PRIMARY KEY,"  +
                    "value FLOAT NOT NULL," +
                    "Email VARCHAR(75) NOT NULL ," +
                    "Game_id VARCHAR(45) NOT NULL ,"+
                    "BettedTeam VARCHAR(45) NOT NULL ,"+
                    "FOREIGN KEY (Email) REFERENCES User(Email)," +
                    "FOREIGN KEY (Game_id) REFERENCES Game(idGame))";
            stm.executeUpdate(sqlAP);

            String sqlCart = "CREATE TABLE IF NOT EXISTS Wallet (" +
                    "Email VARCHAR(75) NOT NULL PRIMARY KEY ," +
                    "Euros VARCHAR(45) NOT NULL," +
                    "Dollars VARCHAR(45) NOT NULL," +
                    "FOREIGN KEY (Email) REFERENCES User(Email))";
            stm.executeUpdate(sqlCart);

            String sqlT = "CREATE TABLE IF NOT EXISTS Transfer (" +
                        "idTransfer VARCHAR(45) NOT NULL PRIMARY KEY ," +
                        "Value FLOAT NOT NULL," +
                        "Date VARCHAR(45) NOT NULL," +
                        "Description VARCHAR(100) NOT NULL," +
                        "Email VARCHAR(75) NOT NULL," +
                        "FOREIGN KEY (Email) REFERENCES User(Email))";
            stm.executeUpdate(sqlT);

            String sqlD = "CREATE TABLE IF NOT EXISTS Sport (" +
                        "idSport INT NOT NULL PRIMARY KEY ," +
                        "Name VARCHAR(45) NOT NULL)";
            stm.executeUpdate(sqlD);

            String sqlDJ = "CREATE TABLE IF NOT EXISTS SportGame (" +
                        "idSport INT NOT NULL," +
                        "idGame VARCHAR(45) NOT NULL,"+
                        "FOREIGN KEY (idSport) References Sport(idSport)," +
                        "FOREIGN KEY (idGame) References Game(idGame))";
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