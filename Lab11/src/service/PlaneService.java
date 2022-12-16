package service;

import entity.Certification;
import entity.Plane;
import entity.TotalPilotOfPlane;
import repository.CertificationRepository;
import repository.EmployeeRepository;
import repository.PlaneRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaneService {
    PlaneRepository planeRepository = new PlaneRepository();

    EmployeeRepository employeeRepository = new EmployeeRepository();
    CertificationRepository certificationRepository = new CertificationRepository();

    public void getTotalPilotsOfPlane() throws SQLException {
        List<Plane> planes = planeRepository.getAllPlanes();
        HashMap<Integer, Integer> totalPilots = certificationRepository.getTotalPilots();

        List<TotalPilotOfPlane> totalPilotOfPlanes = new ArrayList<>();
        TotalPilotOfPlane totalPilotOfPlane = new TotalPilotOfPlane();
        planes.stream().forEach(plane -> {
            totalPilotOfPlane.setId(plane.getId());
            totalPilotOfPlane.setType(plane.getType());
            totalPilotOfPlane.setTotalPilot(totalPilots.get(plane.getId()));
            totalPilotOfPlanes.add(totalPilotOfPlane);
        });

        totalPilotOfPlanes.stream().forEach(totalPilot -> {
            System.out.println("Id: "  + totalPilot.getId() + "\tType: " + totalPilot.getType() + "\tTotal Pilot: " + totalPilot.getTotalPilot());
        });
    }

    public void getListPilotIdAndFlyingRange() throws SQLException {
        List<Plane> planes = planeRepository.getAllPlanes();
        HashMap<String, Integer> listEmployeeId = employeeRepository.getAllEmployeeIdFlyMoreThreePlanes();
        planes.stream().forEach(plane -> {
            listEmployeeId.forEach((employeeId, planeId) -> {
                if (plane.getId() == planeId) {
                    System.out.println("Employee Id: " + employeeId + "\t");
                }
            });

        });

    }
}
