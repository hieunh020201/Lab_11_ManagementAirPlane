import entity.Employee;
import entity.Flight;
import entity.Plane;
import repository.EmployeeRepository;
import repository.FlightRepository;
import repository.PlaneRepository;
import service.EmployeeService;
import service.FlightService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static util.Constants.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        PlaneRepository planeRepository = new PlaneRepository();
        FlightRepository flightRepository = new FlightRepository();

        EmployeeService employeeService = new EmployeeService();
        FlightService flightService = new FlightService();
        while (true) {
            System.out.println("1. List of employee information");
            System.out.println("2. List of flight information");
            System.out.println("3. List of aircraft information");
            System.out.println("4. Exit");
            Scanner scanner = new Scanner(System.in);
            String selection = scanner.nextLine();
            if (selection.equals(manage_employees)) {
                boolean notQuitSelectionOne = true;
                while (notQuitSelectionOne) {
                    System.out.println("1. Tìm các nhân viên có lương nhỏ hơn 10,000");
                    System.out.println("2. Cho biết tổng số lương phải trả cho các nhân viên.");
                    System.out.println("3. Cho biết mã số của các phi công lái máy báy Boeing.");
                    System.out.println("4. Cho biết các nhân viên có thể lái máy bay có mã số 747.");
                    System.out.println("5. Cho biết tên của các phi công lái máy bay Boeing.");
                    System.out.println("6. Cho biết mã số của các phi công vừa lái được Boeing vừa lái được Airbus.");
                    System.out.println("7. Cho biết mã số của các phi công chỉ lái được 3 loại máy bay.");
                    System.out.println("8. Với mỗi phi công có thể lái nhiều hơn 3 loại máy bay, cho biết mã số phi công và tầm bay lớn nhất của các loại máy bay.");
                    System.out.println("9. Với mỗi phi công cho biết mã số phi công và tổng số loại máy bay mà phi công đó có thể lái.");
                    System.out.println("10. Tìm các nhân viên không phải là phi công.");
                    System.out.println("11. Cho biết mã số của các nhân viên có lương cao nhất.");
                    System.out.println("12. Cho biết tổng số lương phải trả cho các phi công.");
                    System.out.println("13. Exit");
                    selection = scanner.nextLine();
                    switch (selection) {
                        case get_employees_salary_less_10000: {
                            List<Employee> employees = employeeRepository.getAllEmployeeHasSalaryHigher10000();
                            employeeService.displayListEmployee(employees);
                            break;
                        }
                        case total_salary_to_paid: {
                            int totalSalary = employeeRepository.calculateTotalSalary();
                            System.out.println("Total Salary is: " + totalSalary);
                            break;
                        }
                        case id_of_employees_flying_Boeing: {
                            List<String> listEmployeeId = employeeRepository.getAllEmployeeIdFlyingBoeing();
                            System.out.println("List Employee Id");
                            listEmployeeId.stream().forEach(employeeId -> System.out.println(employeeId));
                            break;
                        }
                        case employee_flying_plane_747: {
                            List<Employee> employees = employeeRepository.getAllEmployeeFlyingPlane747();
                            employeeService.displayListEmployee(employees);
                            break;
                        }
                        case name_of_employees_flying_Boeing: {
                            List<String> employeeNames = employeeRepository.getAllEmployeeNameFlyingBoeing();
                            employeeNames.stream().forEach(employeeName -> System.out.println(employeeName));
                            break;
                        }
                        case id_of_employees_flying_Boeing_and_Airbus: {
                            List<String> employeeIds = employeeRepository.getAllEmployeeIdFlyingBoeingAndAirBus();
                            employeeIds.stream().forEach(employeeId -> System.out.println(employeeId));
                            break;
                        }
                        case number_pilots_flying_three_types_plane: {
                            List<String> employeeIds = employeeRepository.getAllEmployeeIdFlyingThreePlanes();
                            employeeIds.stream().forEach(employeeId -> System.out.println(employeeId));
                            break;
                        }
                        case highest_flying_range_of_planes: {
                            List<String> employeeIds = employeeRepository.getAllEmployeeIdFlyingBoeingAndAirBus();
                            employeeIds.stream().forEach(employeeId -> System.out.println(employeeId));
                            break;
                        }
                        case total_planes_by_flying_pilots: {
                            employeeService.getTotalPlanesByFlyingPilot();
                            break;
                        }
                        case number_employees_are_not_pilot: {
                            List<Employee> employees = employeeRepository.getAllEmployeesAreNotPilots();
                            employeeService.displayListEmployee(employees);
                            break;
                        }
                        case id_of_employees_has_highest_salary: {
                            List<String> employeeIds = employeeRepository.get10EmployeeIdHasHighestSalary();
                            employeeIds.stream().forEach(employeeId -> System.out.println(employeeId));
                            break;
                        }
                        case total_salary_paying_pilots: {
                            int totalSalary = employeeRepository.calculateTotalSalaryOfPilots();
                            System.out.println("Total Salary is: " + totalSalary);
                            break;
                        }
                        case exit_options_manage_employees: {
                            notQuitSelectionOne = false;
                            break;
                        }
                        default: {
                            System.out.println("Enter selection again!!");
                        }
                    }
                }
            } else if (selection.equals(manage_flights)) {
                boolean notQuitSelectionTwo = true;
                while (notQuitSelectionTwo) {
                    System.out.println("1. Cho biết các chuyến bay đi Đà Lạt (DAD).");
                    System.out.println("2. Cho biết các chuyến bay có độ dài đường bay nhỏ hơn 10.000km và lớn hơn 8.000km");
                    System.out.println("3. Cho biết các chuyến bay xuất phát từ Sài Gòn (SGN) đi Ban Mê Thuộc (BMV).");
                    System.out.println("4. Có bao nhiêu chuyến bay xuất phát từ Sài Gòn (SGN)");
                    System.out.println("5. Cho biết các chuyến bay có thể được thực hiện bởi máy bay Airbus A320.");
                    System.out.println("6. các đường có thể bay thẳng từ ga A đến ga B rồi quay trở về ga A.");
                    System.out.println("7. Với mỗi ga có chuyến bay xuất phát từ đó cho biết có bao nhiêu chuyến bay khởi hành từ ga đó.");
                    System.out.println("8. Với mỗi ga có chuyến bay xuất phát từ đó cho biết tổng chi phí phải trả cho phi công lái các chuyến bay khởi hành từ ga đó.");
                    System.out.println("9. Cho biết danh sách các chuyến bay có thể khởi hành trước 12:00.");
                    System.out.println("10. Với mỗi địa điểm xuất phát cho biết có bao nhiêu chuyến bay có thể khởi hành trước 12:00..");
                    System.out.println("11. Tìm các chuyến bay có thể được thực hiện bởi tất cả các loại máy bay Boeing.");
                    System.out.println("12. Exit");
                    String selectionOne = scanner.nextLine();
                    switch (selectionOne) {
                        case flights_arrive_DaLat: {
                            List<Flight> flights = flightRepository.getAllFlightsToDaLat();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case flights_has_length_8000_10000: {
                            List<Flight> flights = flightRepository.getAllFlightsHasLengthFrom8000To10000();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case flights_from_SGN_to_BMV: {
                            List<Flight> flights = flightRepository.getAllFlightsArriveFromSGNToBMV();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case number_flights_from_SGN: {
                            int countFlights = flightRepository.countNumberFlightsArriveFromSGN();
                            System.out.println("Flights number Arrive From SaiGon: " + countFlights);
                            break;
                        }
                        case flights_by_making_AirbusA320: {
                            List<Flight> flights = flightRepository.getAllFlightsByMakingAirbusA320();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case round_trip_flights: {
                            List<String> flights = flightRepository.getAllRoundTripFlight();
                            flights.stream().forEach(flight -> System.out.println(flight));
                            break;
                        }
                        case number_flights_depart_from_airport: {
                            HashMap<String, Integer> flights = flightRepository.numberOfFlightsDepartingFromAirport();
                            flights.forEach((depart, numberFlights) -> {
                                System.out.println(depart + ": " + numberFlights);
                            });
                            break;
                        }
                        case total_salary_paying_pilots_of_airport: {
                            HashMap<String, Integer> flights = flightRepository.totalSalaryPayingFlightsFromAirport();
                            flights.forEach((depart, totalSalary) -> {
                                System.out.println(depart + ": " + totalSalary);
                            });
                            break;
                        }
                        case flights_depart_before_12AM: {
                            List<Flight> flights = flightRepository.getAllFlightsDepartBefore12AM();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case flights_depart_before_12AM_of_airport: {
                            HashMap<String, Integer> flights = flightRepository.numberFlightsDepartBefore12AM();
                            flights.forEach((depart, totalSalary) -> {
                                System.out.println(depart + ": " + totalSalary);
                            });
                            break;
                        }
                        case flights_by_making_plane_type_Boeing: {
                            List<Flight> flights = new ArrayList<>();
                            flightService.displayListFlight(flights);
                            break;
                        }
                        case exit_options_manage_flights: {
                            notQuitSelectionTwo = false;
                            break;
                        }
                        default: {
                            System.out.println("Enter selection again!!");
                        }
                    }
                }
            } else if (selection.equals(manage_planes)) {
                boolean notQuitSelectionThree = true;
                while (notQuitSelectionThree) {
                    System.out.println("1. Cho biết các loại máy bay có tầm bay lớn hơn 10,000km");
                    System.out.println("2. Có bao nhiêu loại máy báy Boeing.");
                    System.out.println("3. Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có thể lái.");
                    System.out.println("4. Cho biết các loại máy bay có thể thực hiện chuyến bay VN280.");
                    System.out.println("5. Mã số, loại máy báy và tổng số phi công có thể lái loại máy bay đó.");
                    System.out.println("6. Exit");
                    selection = scanner.nextLine();
                    switch (selection) {
                        case planes_flying_range_higher_10000: {
                            List<Plane> planes = planeRepository.getAllPlanesHasFlyingRangeHigher10000();
                            planeRepository.displayListPlane(planes);
                            break;
                        }
                        case number_planes_type_Boeing: {
                            int countPlanes = planeRepository.countNumberPlanesHasTypeIsBoeing();
                            System.out.println("Plane number is type Boeing: " + countPlanes);
                            break;
                        }
                        case id_planes_of_pilots_has_last_name_Nguyen: {
                            System.out.println("List Plane Id of Pilot's name is Nguyen: ");
                            List<Integer> listPlaneId = planeRepository.getAllPlaneIdOfPilotHasNameIsNguyen();
                            listPlaneId.stream().forEach(planeId -> System.out.println(planeId));
                            break;
                        }
                        case planes_make_flight_VN280: {
                            List<Plane> planes = planeRepository.getAllPlanesMakeFlightVN280();
                            planeRepository.displayListPlane(planes);
                            break;
                        }
                        case total_pilots_fly_planes: {
                            List<Plane> planes = planeRepository.getAllPlanesMakeFlightVN280();
                            planeRepository.displayListPlane(planes);
                            break;
                        }
                        case exit_options_manage_planes: {
                            notQuitSelectionThree = false;
                            break;
                        }
                        default: {
                            System.out.println("Enter selection again!!");
                        }
                    }
                }
            } else if (selection.equals(exit_manage)){
                break;
            } else {
                System.out.println("Enter selection again!!");
            }
        }
    }
}
