package service;

import entity.Employee;
import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private Connection connection;

    public EmployeeService() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
    }

    public List<Employee> getAllEmployeeHasSalaryHigher10000() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Employee> employees = new ArrayList<>();
        PreparedStatement pStatement = null;
        Employee employee;
        try {

            String query = "SELECT MANV, TEN, LUONG FROM NHANVIEN e WHERE e.LUONG < 10000";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                employee = new Employee();
                employee.setId(rs.getString(1));
                employee.setName(rs.getString(2));
                employee.setSalary(rs.getInt(3));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return employees;
    }

    public int calculateTotalSalary() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        int totalSalary = 0;
        try {

            String query = "SELECT SUM(LUONG) FROM NHANVIEN";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                totalSalary = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return totalSalary;
    }

    public List<String> getAllEmployeeIdFlyingBoeing() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        List<String> listId = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT c.MANV FROM CHUNGNHAN c " +
                    "join MAYBAY p on c.MAMB = p.MAMB WHERE p.LOAI LIKE '%Boeing%'";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return listId;
    }

    public List<Employee> getAllEmployeeFlyingPlane747() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        List<Employee> employees = new ArrayList<>();
        Employee employee;
        try {
            String query = "SELECT e.MANV, e.TEN, e.LUONG FROM CHUNGNHAN c " +
                    "join NHANVIEN e on c.MANV = e.MANV WHERE c.MAMB = 747";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            while(rs.next()){
                employee = new Employee();
                employee.setId(rs.getString(1));
                employee.setName(rs.getString(2));
                employee.setSalary(rs.getInt(3));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return employees;
    }

    public List<String> getAllEmployeeNameFlyingBoeing() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        List<String> listId = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT e.TEN FROM NHANVIEN e " +
                    "join CHUNGNHAN c on e.MANV = c.MANV join MAYBAY p on c.MAMB = p.MAMB " +
                    "WHERE p.LOAI LIKE '%Boeing%'";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return listId;
    }

    public void displayListEmployee(List<Employee> employees) {
        for (Employee employee: employees) {
            System.out.println(" " + employee.getId() + "| " + employee.getName() + "| " + employee.getSalary());
        }
    }
}
