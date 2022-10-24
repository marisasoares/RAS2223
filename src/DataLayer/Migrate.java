package src.DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Migrate {
    public static void createTables() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Utilizador (" +
                    "id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "username varchar(32) DEFAULT 'n/d'," +
                    "email varchar(320) DEFAULT 'n/d' unique," +
                    "password varchar(32) DEFAULT 'n/d'," +
                    "permissao int(5) NOT NULL DEFAULT 0,";

            stm.executeUpdate(sql);

            String sqlJ = "CREATE TABLE IF NOT EXISTS Jogador (" +
                    "id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "nome varchar(64) DEFAULT 'n/d', " +
                    "FOREIGN KEY (Equipa_id) REFERENCES Equipa(id))";

            stm.executeUpdate(sqlJ);

            String sqlE = "CREATE TABLE IF NOT EXISTS Equipa (" +
                    "id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "nome varchar(64) NOT NULL," +
                    "FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id))";
            stm.executeUpdate(sqlE);

            String sqlPU = "CREATE TABLE IF NOT EXISTS Utilizador_PontoDeInteresse (" +
                    "id int(64) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "utilizador_id int NOT NULL," +
                    "ponto_de_interesse_id int NOT NULL," +
                    "FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id) ON UPDATE CASCADE," +
                    "FOREIGN KEY (ponto_de_interesse_id) REFERENCES PontoDeInteresse(id) ON UPDATE CASCADE)";
            stm.executeUpdate(sqlPU);

            String sqlRepRev = "CREATE TABLE IF NOT EXISTS ReportedReview (" +
                    "id int(64) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "utilizador_id int NOT NULL," +
                    "review_id int NOT NULL," +
                    "FOREIGN KEY (utilizador_id) REFERENCES Utilizador(id) ON UPDATE CASCADE," +
                    "FOREIGN KEY (review_id) REFERENCES Review(id) ON UPDATE CASCADE)";
            stm.executeUpdate(sqlRepRev);

            String sqlLikeRev = "CREATE TABLE IF NOT EXISTS LikedReviews (" +
                    "id int(64) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "utilizador_id int NOT NULL," +
                    "review_id int NOT NULL)";
            stm.executeUpdate(sqlLikeRev);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}