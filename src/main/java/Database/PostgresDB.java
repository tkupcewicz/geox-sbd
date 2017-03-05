package Database;

import java.sql.*;

/**
 * Created by Tymek on 03.03.2017.
 */
public class PostgresDB {
    private static PostgresDB instance = null;

    private final String url = "jdbc:postgresql://localhost/sklep";
    private final String user = "Tymek";
    private final String password = "haslo";

    private Connection connection;

    public PostgresDB(){
        this.connection = this.connect();
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

    public ResultSetStorage select(String query){
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return new ResultSetStorage(resultSet);
        } catch (SQLException e) {
            System.out.println("Couldn't process query: " + query);
        }
        return null;
    }

    public boolean insert(String query) throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute(this.connection.nativeSQL(query));
        statement.close();
        return true;
    }

}
