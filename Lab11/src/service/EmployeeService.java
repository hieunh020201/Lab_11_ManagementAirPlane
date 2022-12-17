package service;

import entity.Employee;
import repository.EmployeeRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class EmployeeService {
    EmployeeRepository employeeRepository = new EmployeeRepository();

    public void getTotalPlanesByFlyingPilot() throws SQLException {
        HashMap<String, Integer> totalPlanes = employeeRepository.getTotalPlanesByFlyingPilot();
        totalPlanes.forEach((employeeId, total) -> {
            System.out.println("Employee ID: " + employeeId + " has total planes: " + total);
        });
    }

    public void displayListEmployee(List<Employee> employees) {
        System.out.println("ID\tNAME\tSALARY:");
        for (Employee employee: employees) {
            System.out.println(" " + employee.getId() + "| " + employee.getName() + "| " + employee.getSalary());
        }
    }
}
