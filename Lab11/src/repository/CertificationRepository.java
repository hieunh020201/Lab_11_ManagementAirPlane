package repository;

import entity.Certification;
import entity.Plane;
import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CertificationRepository {
    private Connection connection;

    public List<Certification> getAllCertification() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        List<Certification> certifications = new ArrayList<>();
        PreparedStatement pStatement = null;
        Certification certification;
        try {

            String query = "SELECT MANV, MAMB FROM CHUNGNHAN";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                certification = new Certification();
                certification.setEmployeeId(rs.getString(1));
                certification.setPlaneId(rs.getInt(2));
                certifications.add(certification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return certifications;
    }


    public HashMap<Integer, Integer> getTotalPilots() throws SQLException {

        connection = JdbcConnectionDBUtil.getConnection();
        List<Plane> planes = new ArrayList<>();
        HashMap<Integer, Integer> totalPilots = new HashMap<>();
        PreparedStatement pStatement = null;
        try {

            String query = "SELECT MAMB, COUNT(MANV) FROM CHUNGNHAN GROUP BY MAMB";
            pStatement = connection.prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                totalPilots.put(rs.getInt(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return totalPilots;
    }
}
