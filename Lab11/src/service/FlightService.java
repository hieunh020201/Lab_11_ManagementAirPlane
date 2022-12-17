package service;

import entity.Flight;

import java.util.List;

public class FlightService {


    public void displayListFlight(List<Flight> flights) {
        System.out.println("ID\tDEPART\tARRIVE\tLENGTH\tDEPARTURE TIME\tARRIVE TIME\tCOST:");
        for (Flight flight: flights) {
            System.out.println(" " + flight.getId() + "| " + flight.getDepart() + "| " + flight.getArrive()
                    + "| " + flight.getLength() + "| " + flight.getDepartureTime() + "| " + flight.getArriveTime() + "| " + flight.getCost());
        }
    }
}
