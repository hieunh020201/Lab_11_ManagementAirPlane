package repository;

import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRepository {
    public static void getEmployee() throws SQLException {
        Statement stmt = null;
        Connection connection = JdbcConnectionDBUtil.getConnection();

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NHANVIEN");

            while(rs.next()){
                System.out.println(rs.getString(1) + "| " + rs.getString(2) + "| " + rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }
}
