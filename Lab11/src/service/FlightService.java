package service;

import entity.Flight;
import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private Connection connection;

    public FlightService() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
    }

    public List<Flight> getAllFlightsToDaLat() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Flight> flights = new ArrayList<>();
        PreparedStatement pStatement = null;
        Flight flight;
        try {
            String query = "SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI " +
                    "FROM CHUYENBAY f WHERE f.GADEN = 'DAD'";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                flight = new Flight();
                flight.setId(rs.getString(1));
                flight.setDepart(rs.getString(2));
                flight.setArrive(rs.getString(3));
                flight.setLength(rs.getInt(4));
                flight.setDepartureTime(rs.getTime(5));
                flight.setArriveTime(rs.getTime(6));
                flight.setCost(rs.getInt(7));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return flights;
    }

    public List<Flight> getAllFlightsHasLengthFrom8000To10000() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Flight> flights = new ArrayList<>();
        PreparedStatement pStatement = null;
        Flight flight;
        try {

            String query = "SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI " +
                    "FROM CHUYENBAY f WHERE f.DODAI > 8000 AND f.DODAI < 10000";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                flight = new Flight();
                flight.setId(rs.getString(1));
                flight.setDepart(rs.getString(2));
                flight.setArrive(rs.getString(3));
                flight.setLength(rs.getInt(4));
                flight.setDepartureTime(rs.getTime(5));
                flight.setArriveTime(rs.getTime(6));
                flight.setCost(rs.getInt(7));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return flights;
    }

    public List<Flight> getAllFlightsArriveFromSGNToBMV() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Flight> flights = new ArrayList<>();
        PreparedStatement pStatement = null;
        Flight flight;
        try {

            String query = "SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI " +
                    "FROM CHUYENBAY f WHERE f.GADI = 'SGN' AND f.GADEN = 'BMV'";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                flight = new Flight();
                flight.setId(rs.getString(1));
                flight.setDepart(rs.getString(2));
                flight.setArrive(rs.getString(3));
                flight.setLength(rs.getInt(4));
                flight.setDepartureTime(rs.getTime(5));
                flight.setArriveTime(rs.getTime(6));
                flight.setCost(rs.getInt(7));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return flights;
    }

    public List<Flight> getAllFlightsArriveFromSGN() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Flight> flights = new ArrayList<>();
        PreparedStatement pStatement = null;
        Flight flight;
        try {

            String query = "SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI " +
                    "FROM CHUYENBAY f WHERE f.GADI = 'SGN'";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                flight = new Flight();
                flight.setId(rs.getString(1));
                flight.setDepart(rs.getString(2));
                flight.setArrive(rs.getString(3));
                flight.setLength(rs.getInt(4));
                flight.setDepartureTime(rs.getTime(5));
                flight.setArriveTime(rs.getTime(6));
                flight.setCost(rs.getInt(7));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return flights;
    }

    public List<Flight> getAllFlightsByMakingAirbusA320() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Flight> flights = new ArrayList<>();
        PreparedStatement pStatement = null;
        Flight flight;
        try {

            String query = "SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI " +
                    "FROM CHUYENBAY f " +
                    "WHERE f.DODAI < (SELECT p.TAMBAY FROM MAYBAY p WHERE p.LOAI = 'Airbus A320')";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                flight = new Flight();
                flight.setId(rs.getString(1));
                flight.setDepart(rs.getString(2));
                flight.setArrive(rs.getString(3));
                flight.setLength(rs.getInt(4));
                flight.setDepartureTime(rs.getTime(5));
                flight.setArriveTime(rs.getTime(6));
                flight.setCost(rs.getInt(7));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pStatement.close();
            connection.close();
        }
        return flights;
    }

    public void displayListFlight(List<Flight> flights) {
        System.out.println("ID\tDEPART\tARRIVE\tLENGTH\tDEPARTURE TIME\tARRIVE TIME\tCOST:");
        for (Flight flight: flights) {
            System.out.println(" " + flight.getId() + "| " + flight.getDepart() + "| " + flight.getArrive()
                    + "| " + flight.getLength() + "| " + flight.getDepartureTime() + "| " + flight.getArriveTime() + "| " + flight.getCost());
        }
    }
}
