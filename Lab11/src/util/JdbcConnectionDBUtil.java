package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionDBUtil {
    public static Connection getConnection() throws SQLException{
        Connection connection = null;
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=HANGKHONG";
            connection = DriverManager.getConnection(connectionUrl, "hieu1","123456789");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
