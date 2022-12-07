package repository;

import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FlightRepository {
    public static void getAllFlightsToDaLat() throws SQLException {
        Statement stmt = null;
        Connection connection = JdbcConnectionDBUtil.getConnection();

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CHUYENBAY f WHERE f.GADEN = 'DAD'");

            while(rs.next()){
                System.out.println(rs.getString(1) + "| " + rs.getString(2) + "| " + rs.getString(3) + "| " + rs.getInt(4)
                + "| " + rs.getTime(5) + "| " + rs.getTime(6) + "| " + rs.getInt(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }
}
