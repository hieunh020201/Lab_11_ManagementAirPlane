import entity.Employee;
import entity.Flight;
import entity.Plane;
import service.EmployeeService;
import service.FlightService;
import service.PlaneService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        EmployeeService employeeService = new EmployeeService();
        PlaneService planeService = new PlaneService();
        FlightService flightService = new FlightService();
        while (true) {
            System.out.println("1. List of employee information");
            System.out.println("2. List of flight information");
            System.out.println("3. List of aircraft information");
            System.out.println("4. Exit");
            Scanner scanner = new Scanner(System.in);
            String selection = scanner.nextLine();
            if (selection.equals("1")) {
                boolean notQuitSelectionOne = true;
                while (notQuitSelectionOne) {
                    System.out.println("1. Tìm các nhân viên có lương nhỏ hơn 10,000");
                    System.out.println("2. Cho biết tổng số lương phải trả cho các nhân viên.");
                    System.out.println("3. Cho biết mã số của các phi công lái máy báy Boeing..");
                    System.out.println("4. Cho biết các nhân viên có thể lái máy bay có mã số 747.");
                    System.out.println("5. Cho biết tên của các phi công lái máy bay Boeing.");
                    System.out.println("6. Cho biết mã số của các phi công vừa lái được Boeing vừa lái được Airbus.");
                    System.out.println("7. Exit");
                    selection = scanner.nextLine();
                    switch (selection) {
                        case "1": {
                            List<Employee> employees = employeeService.getAllEmployeeHasSalaryHigher10000();
                            employeeService.displayListEmployee(employees);
                            break;
                        }
                        case "2": {
                            int totalSalary = employeeService.calculateTotalSalary();
                            System.out.println("Total Salary is: " + totalSalary);
                            break;
                        }
                        case "3": {
                            List<String> listEmployeeId = employeeService.getAllEmployeeIdFlyingBoeing();
                            System.out.println("List Employee Id");
                            listEmployeeId.stream().forEach(employeeId -> System.out.println(employeeId));
                            break;
                        }
                        case "4": {
                            List<Employee> employees = employeeService.getAllEmployeeFlyingPlane747();
                            employeeService.displayListEmployee(employees);
                            break;
                        }
                        case "5": {
                            List<String> employeeNames = employeeService.getAllEmployeeNameFlyingBoeing();
                            employeeNames.stream().forEach(employeeName -> System.out.println(employeeName));
                            break;
                        }
                        case "6": {
                            List<String> employeeIds = employeeService.getAllEmployeeIdFlyingBoeingAndAirBus();
                            employeeIds.stream().forEach(employeeId -> System.out.println(employeeId));
                            break;
                        }
                        case "7": {
                            notQuitSelectionOne = false;
                            break;
                        }
                        default: {
                            System.out.println("Enter selection again!!");
                        }
                    }
                }
            } else if (selection.equals("2")) {
                boolean notQuitSelectionTwo = true;
                while (notQuitSelectionTwo) {
                    System.out.println("1. Cho biết các chuyến bay đi Đà Lạt (DAD).");
                    System.out.println("2. Cho biết các chuyến bay có độ dài đường bay nhỏ hơn 10.000km và lớn hơn 8.000km");
                    System.out.println("3. Cho biết các chuyến bay xuất phát từ Sài Gòn (SGN) đi Ban Mê Thuộc (BMV).");
                    System.out.println("4. Có bao nhiêu chuyến bay xuất phát từ Sài Gòn (SGN)");
                    System.out.println("5. Cho biết các chuyến bay có thể ñược thực hiện bởi máy bay Airbus A320.");
                    System.out.println("6. Exit");
                    String selectionOne = scanner.nextLine();
                    switch (selectionOne) {
                        case "1": {
                            List<Flight> flights = flightService.getAllFlightsToDaLat();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case "2": {
                            List<Flight> flights = flightService.getAllFlightsHasLengthFrom8000To10000();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case "3": {
                            List<Flight> flights = flightService.getAllFlightsArriveFromSGNToBMV();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case "4": {
                            List<Flight> flights = flightService.getAllFlightsArriveFromSGN();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case "5": {
                            List<Flight> flights = flightService.getAllFlightsByMakingAirbusA320();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case "6": {
                            notQuitSelectionTwo = false;
                            break;
                        }
                        default: {
                            System.out.println("Enter selection again!!");
                        }
                    }
                }
            } else if (selection.equals("3")) {
                boolean notQuitSelectionThree = true;
                while (notQuitSelectionThree) {
                    System.out.println("1. Cho biết các loại máy bay có tầm bay lớn hơn 10,000km");
                    System.out.println("2. Có bao nhiêu loại máy báy Boeing.");
                    System.out.println("3. Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có thể lái.");
                    System.out.println("4. Cho biết các loại máy bay có thể thực hiện chuyến bay VN280.");
                    System.out.println("5. Exit");
                    selection = scanner.nextLine();
                    switch (selection) {
                        case "1": {
                            List<Plane> planes = planeService.getAllPlanesHasFlyingRangeHigher10000();
                            planeService.displayListPlane(planes);
                            break;
                        }
                        case "2": {
                            List<Plane> planes = planeService.getAllPlanesHasTypeIsBoeing();
                            planeService.displayListPlane(planes);
                            break;
                        }
                        case "3": {
                            System.out.println("List Plane Id of Pilot's name is Nguyen: ");
                            List<Integer> listPlaneId = planeService.getAllPlaneIdOfPilotHasNameIsNguyen();
                            listPlaneId.stream().forEach(planeId -> System.out.println(planeId));
                            break;
                        }
                        case "4": {
                            List<Plane> planes = planeService.getAllPlanesMakeFlightVN280();
                            planeService.displayListPlane(planes);
                            break;
                        }
                        case "5": {
                            notQuitSelectionThree = false;
                            break;
                        }
                        default: {
                            System.out.println("Enter selection again!!");
                        }
                    }
                }
            } else if (selection.equals("4")){
                break;
            } else {
                System.out.println("Enter selection again!!");
            }
        }
    }
}
