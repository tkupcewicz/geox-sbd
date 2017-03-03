package Database;

import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Tymek on 03.03.2017.
 */
public class PostgresDB {
    private static PostgresDB instance = null;

    private final String url = "jdbc:postgresql://localhost/sklep";
    private final String user = "Tymek";
    private final String password = "haslo";

    public PostgresDB(){

    }

    public static PostgresDB getInstance(){
        if (instance == null){
            instance = new PostgresDB();
        }
        return instance;
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
