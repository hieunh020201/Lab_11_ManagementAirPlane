package repository;

import entity.Address;
import entity.ResponseLogin;
import entity.User;
import util.JdbcConnectionDBUtil;

import java.sql.*;

public class UserRepository {
    private Connection connection;

    public ResponseLogin login(String userName, String password) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        ResponseLogin responseLogin = new ResponseLogin();
        try {
            String query = "SELECT USER_NAME, PASSWORD FROM [dbo.USER] WHERE USER_NAME = ? AND PASSWORD = ?;";
            pStatement = connection.prepareStatement(query);

            pStatement.setString(1, userName);
            pStatement.setString(2, password);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                responseLogin.setUserName(rs.getString(1));
                responseLogin.setPassword(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return responseLogin;
    }

    public int insertUser(User user) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        int rs = 0;
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO [dbo.USER](USER_NAME, PASSWORD, NAME, PHONE, GENDER, EMAIL, BIRTHDAY, ROLE)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pStatement = connection.prepareStatement(query);

            pStatement.setString(1, user.getUserName());
            pStatement.setString(2, user.getPassword());
            pStatement.setString(3, user.getName());
            pStatement.setString(4, user.getPhone());
            pStatement.setString(5, user.getGender());
            pStatement.setString(6, user.getEmail());
            pStatement.setDate(7, user.getBirthday());
            pStatement.setString(8, "USER");

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

    public User getUserById(int id) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        User user = new User();
        try {
            String query = "SELECT ID, USER_NAME, PASSWORD, NAME, PHONE, GENDER, EMAIL, BIRTHDAY, ROLE FROM [dbo.USER] WHERE ID = ?";
            pStatement = connection.prepareStatement(query);

            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setName(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setGender(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setBirthday(rs.getDate(8));
                user.setRole(rs.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return user;
    }

    public User getUserByUserName(String username) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        User user = new User();
        try {
            String query = "SELECT ID, USER_NAME, PASSWORD, NAME, PHONE, GENDER, EMAIL, BIRTHDAY, ROLE FROM [dbo.USER] WHERE USER_NAME = ?";
            pStatement = connection.prepareStatement(query);

            pStatement.setString(1, username);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setName(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setGender(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setBirthday(rs.getDate(8));
                user.setRole(rs.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return user;
    }

    public int UpdateAddress(User user) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        int rs = 0;
        try {
            connection.setAutoCommit(false);
            String query = "UPDATE [dbo.USER] " +
                    "SET USER_NAME = ?, NAME = ?, PHONE =?, GENDER = ?, EMAIL = ?, BIRTHDAY = ? " +
                    "WHERE ID = ?";
            pStatement = connection.prepareStatement(query);

            pStatement.setString(1, user.getUserName());
            pStatement.setString(2, user.getName());
            pStatement.setString(3, user.getPhone());
            pStatement.setString(4, user.getGender());
            pStatement.setString(5, user.getEmail());
            pStatement.setDate(6, user.getBirthday());
            pStatement.setInt(7, user.getId());

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
}
