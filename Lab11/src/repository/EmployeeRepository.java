package repository;

import entity.Employee;
import entity.EmployeeIdTypePlane;
import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private Connection connection;

    public List<Employee> getAllEmployeeHasSalaryHigher10000() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Employee> employees = new ArrayList<>();
        PreparedStatement pStatement = null;
        Employee employee;
        try {

            String query = "SELECT MANV, TEN, LUONG FROM NHANVIEN WHERE LUONG < 10000";
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
            if (pStatement != null){
                pStatement.close();
            }
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
            if (pStatement != null){
                pStatement.close();
            }
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
                    "JOIN MAYBAY p ON c.MAMB = p.MAMB WHERE p.LOAI LIKE '%Boeing%'";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
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
                    "JOIN NHANVIEN e ON c.MANV = e.MANV WHERE c.MAMB = 747";
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
            if (pStatement != null){
                pStatement.close();
            }
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
                    "JOIN CHUNGNHAN c ON e.MANV = c.MANV join MAYBAY p on c.MAMB = p.MAMB " +
                    "WHERE p.LOAI LIKE '%Boeing%'";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return listId;
    }

    public List<String> getAllEmployeeIdFlyingBoeingAndAirBus() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        List<String> listEmployeeId = new ArrayList<>();
        List<String> result = new ArrayList<>();
        List<EmployeeIdTypePlane> employeeIdTypePlanes = new ArrayList<>();
        try {
            String queryEmployeeId = "SELECT MANV FROM NHANVIEN";
            pStatement = connection.prepareStatement(queryEmployeeId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                listEmployeeId.add(resultSet.getString(1));
            }

            String query = "SELECT c.MANV, p.LOAI FROM CHUNGNHAN c JOIN MAYBAY p ON c.MAMB = p.MAMB";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();
            EmployeeIdTypePlane employeeIdTypePlane;
            while (rs.next()) {
                employeeIdTypePlane = new EmployeeIdTypePlane();
                employeeIdTypePlane.setEmployeeId(rs.getString(1));
                employeeIdTypePlane.setTypePlane(rs.getString(2));
                employeeIdTypePlanes.add(employeeIdTypePlane);
            }

            listEmployeeId.stream().forEach(employeeId -> {
                boolean hasBoeing = false;
                boolean hasAirbus = false;
                for (EmployeeIdTypePlane employeeIdType: employeeIdTypePlanes) {
                    if (employeeId.equals(employeeIdType.getEmployeeId())) {
                        if (employeeIdType.getTypePlane().contains("Boeing")) {
                            hasBoeing = true;
                        } else if (employeeIdType.getTypePlane().contains("Airbus")) {
                            hasAirbus = true;
                        }
                    }
                }
                if (hasBoeing && hasAirbus) {
                    result.add(employeeId);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return result;
    }

    public void displayListEmployee(List<Employee> employees) {
        System.out.println("ID\tNAME\tSALARY:");
        for (Employee employee: employees) {
            System.out.println(" " + employee.getId() + "| " + employee.getName() + "| " + employee.getSalary());
        }
    }
}
