package repository;

import entity.Address;
import entity.Category;
import entity.User;
import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private Connection connection;

    public int insertCategory(String name) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        int rs = 0;
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO CATEGORY(NAME) " +
                    "VALUES (?)";
            pStatement = connection.prepareStatement(query);

            pStatement.setString(1, name);

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

    public List<Category> getListCategory() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        List<Category> categories = new ArrayList<>();
        try {
            String query = "SELECT ID, NAME FROM CATEGORY";
            pStatement = connection.prepareStatement(query);

            ResultSet rs = pStatement.executeQuery();
            Category category;
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return categories;
    }

    public Category getCategoryById(int id) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        Category category = new Category();
        try {
            String query = "SELECT ID, NAME FROM CATEGORY WHERE ID = ?";
            pStatement = connection.prepareStatement(query);

            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return category;
    }

    public int updateCategory(Category category) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        int rs = 0;
        try {
            connection.setAutoCommit(false);
            String query = "UPDATE CATEGORY " +
                    "SET NAME = ? " +
                    "WHERE ID = ?";
            pStatement = connection.prepareStatement(query);

            pStatement.setInt(2, category.getId());
            pStatement.setString(1, category.getName());

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

    public int deleteCategory(int id) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        int rs = 0;
        try {
            connection.setAutoCommit(false);
            String query = "DELETE FROM CATEGORY WHERE ID= ?";
            pStatement = connection.prepareStatement(query);

            pStatement.setInt(1, id);

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
