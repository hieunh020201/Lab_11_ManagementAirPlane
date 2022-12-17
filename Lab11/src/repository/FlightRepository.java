package repository;

import entity.Flight;
import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlightRepository {
    private Connection connection;

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
            if (pStatement != null){
                pStatement.close();
            }
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
            if (pStatement != null){
                pStatement.close();
            }
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
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return flights;
    }

    public int countNumberFlightsArriveFromSGN() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        int number = 0;
        PreparedStatement pStatement = null;
        try {
            String query = "SELECT COUNT(f.MACB) FROM CHUYENBAY f WHERE f.GADI = 'SGN'";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                number = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return number;
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
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return flights;
    }

    public List<String> getAllRoundTripFlight() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<String> flights = new ArrayList<>();
        PreparedStatement pStatement = null;
        try {

            String query = "SELECT f1.GADI, f1.GADEN FROM CHUYENBAY f1 " +
                    "CROSS JOIN CHUYENBAY f2 WHERE f1.GADEN = f2.GADI AND f1.GADI = f2.GADEN AND f1.GIODEN < f2.GIODI";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                flights.add(rs.getString(1)  + " to " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return flights;
    }

    public HashMap<String, Integer> numberOfFlightsDepartingFromAirport() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        HashMap<String, Integer> numberFlights = new HashMap<>();

        PreparedStatement pStatement = null;
        try {
            String query = "SELECT GADI, COUNT(MACB) FROM CHUYENBAY GROUP BY GADI";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                numberFlights.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return numberFlights;
    }

    public HashMap<String, Integer> totalSalaryPayingFlightsFromAirport() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        HashMap<String, Integer> totalFee = new HashMap<>();

        PreparedStatement pStatement = null;
        try {
            String query = "SELECT GADI, SUM(CHIPHI) FROM CHUYENBAY GROUP BY GADI";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                totalFee.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return totalFee;
    }

    public List<Flight> getAllFlightsDepartBefore12AM() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Flight> flights = new ArrayList<>();
        PreparedStatement pStatement = null;
        Flight flight;
        try {

            String query = "SELECT MACB, GADI, GADEN, DODAI, GIODI, GIODEN, CHIPHI FROM CHUYENBAY WHERE GIODI < '12:00'";
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
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return flights;
    }

    public HashMap<String, Integer> numberFlightsDepartBefore12AM() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        HashMap<String, Integer> numberFlights = new HashMap<>();

        PreparedStatement pStatement = null;
        try {
            String query = "SELECT GADI, COUNT(MACB) FROM CHUYENBAY WHERE GIODI < '12:00' GROUP BY GADI";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                numberFlights.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return numberFlights;
    }


}
