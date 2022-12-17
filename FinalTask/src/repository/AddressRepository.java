package repository;

import entity.Address;
import entity.ResponseLogin;
import entity.User;
import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository {

    private Connection connection;

    public int UpdateAddress(int id, Address address) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        int rs = 0;
        int rs1 = 0;
        try {
            connection.setAutoCommit(false);
//            CHECK DEFAULT NEW ADDRESS
            if (address.isDefaultAddress()) {
                String update = "UPDATE [dbo.ADDRESS] " +
                        "SET DEFAULT_ADDRESS = 0 " +
                        "WHERE USER_ID = ?";
                pStatement = connection.prepareStatement(update);
                pStatement.setInt(1, id);
                rs1 = pStatement.executeUpdate();
            }
            String query = "UPDATE [dbo.ADDRESS] " +
                    "SET NAME = ?, PHONE = ?, PROVINCE =?, DISTRICT = ?, STREET = ?, TYPE = ?, DEFAULT_ADDRESS = ? " +
                    "WHERE ID = ? AND USER_ID = ?";
            pStatement = connection.prepareStatement(query);


            pStatement.setString(1, address.getName());
            pStatement.setString(2, address.getPhone());
            pStatement.setString(3, address.getProvince());
            pStatement.setString(4, address.getDistrict());
            pStatement.setString(5, address.getStreet());
            pStatement.setBoolean(6, address.isType());
            pStatement.setBoolean(7, address.isDefaultAddress());
            pStatement.setInt(8, address.getId());
            pStatement.setInt(9, id);

            rs = pStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return rs;
    }

    public int AddNewAddress(int id, Address address) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        int rs = 0;
        int rs1 = 0;
        try {
            connection.setAutoCommit(false);
//            CHECK DEFAULT NEW ADDRESS
            if (address.isDefaultAddress()) {
                String update = "UPDATE [dbo.ADDRESS] " +
                        "SET DEFAULT_ADDRESS = 0 " +
                        "WHERE USER_ID = ?";
                pStatement = connection.prepareStatement(update);
                pStatement.setInt(1, id);
                rs1 = pStatement.executeUpdate();
            }
            String query = "INSERT INTO [dbo.ADDRESS](NAME, PHONE, PROVINCE, DISTRICT, STREET, TYPE, DEFAULT_ADDRESS, USER_ID) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            pStatement = connection.prepareStatement(query);


            pStatement.setString(1, address.getName());
            pStatement.setString(2, address.getPhone());
            pStatement.setString(3, address.getProvince());
            pStatement.setString(4, address.getDistrict());
            pStatement.setString(5, address.getStreet());
            pStatement.setBoolean(6, address.isType());
            pStatement.setBoolean(7, address.isDefaultAddress());
            pStatement.setInt(8, id);

            rs = pStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return rs;
    }

    public List<Address> getListAddressById(int id) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        List<Address> addresses = new ArrayList<>();
        try {
            String query = "SELECT ID, NAME, PHONE, PROVINCE, DISTRICT, STREET, " +
                    "TYPE, DEFAULT_ADDRESS, USER_ID FROM [dbo.ADDRESS] WHERE USER_ID = ? AND IS_DELETED = 0";
            pStatement = connection.prepareStatement(query);

            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            Address address;
            while (rs.next()) {
                address = new Address();
                address.setId(rs.getInt(1));
                address.setName(rs.getString(2));
                address.setPhone(rs.getString(3));
                address.setProvince(rs.getString(4));
                address.setDistrict(rs.getString(5));
                address.setStreet(rs.getString(6));
                address.setType(rs.getBoolean(7));
                address.setDefaultAddress(rs.getBoolean(8));
                address.setUserId(rs.getInt(9));
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return addresses;
    }

    public Address getAddressById(int id) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        Address address = new Address();
        try {
            String query = "SELECT ID, NAME, PHONE, PROVINCE, DISTRICT, STREET, TYPE, DEFAULT_ADDRESS, USER_ID FROM [dbo.ADDRESS] WHERE ID = ?";
            pStatement = connection.prepareStatement(query);

            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                address.setId(rs.getInt(1));
                address.setName(rs.getString(2));
                address.setPhone(rs.getString(3));
                address.setProvince(rs.getString(4));
                address.setDistrict(rs.getString(5));
                address.setStreet(rs.getString(6));
                address.setType(rs.getBoolean(7));
                address.setDefaultAddress(rs.getBoolean(8));
                address.setUserId(rs.getInt(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return address;
    }
}
