package repository;

import entity.Plane;
import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneRepository {
    private Connection connection;

    public List<Plane> getAllPlanesHasFlyingRangeHigher10000() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Plane> planes = new ArrayList<>();
        PreparedStatement pStatement = null;
        Plane plane;
        try {
            String query = "SELECT MAMB, LOAI, TAMBAY FROM MAYBAY p WHERE p.TAMBAY > 10000";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                plane = new Plane();
                plane.setId(rs.getInt(1));
                plane.setType(rs.getString(2));
                plane.setFlyingRange(rs.getInt(3));

                planes.add(plane);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return planes;
    }

    public int countNumberPlanesHasTypeIsBoeing() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        int number = 0;
        try {
            String query = "SELECT COUNT(p.MAMB) FROM MAYBAY p WHERE p.LOAI LIKE '%Boeing%'";
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

    public List<Integer> getAllPlaneIdOfPilotHasNameIsNguyen() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Integer> listPlaneId = new ArrayList<>();
        PreparedStatement pStatement = null;
        try {
            String query = "SELECT c.MAMB FROM NHANVIEN e " +
                    "JOIN CHUNGNHAN c ON e.MANV = c.MANV WHERE e.TEN LIKE 'Nguyen%'";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                listPlaneId.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return listPlaneId;
    }

    public List<Plane> getAllPlanesMakeFlightVN280() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Plane> planes = new ArrayList<>();
        PreparedStatement pStatement = null;
        Plane plane;
        try {
            String query = "SELECT MAMB, LOAI, TAMBAY FROM MAYBAY p " +
                    "WHERE p.TAMBAY > (SELECT f.DODAI FROM CHUYENBAY f WHERE f.MACB = 'VN280')";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                plane = new Plane();
                plane.setId(rs.getInt(1));
                plane.setType(rs.getString(2));
                plane.setFlyingRange(rs.getInt(3));
                planes.add(plane);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return planes;
    }

    public void displayListPlane(List<Plane> planes) {
        System.out.println("ID\tTYPE\tFlying Range:");
        for (Plane plane: planes) {
            System.out.println(" " + plane.getId() + "| " + plane.getType() + "| " + plane.getFlyingRange());
        }
    }
}
