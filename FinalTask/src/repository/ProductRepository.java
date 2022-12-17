package repository;

import entity.Category;
import entity.Product;
import util.JdbcConnectionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private Connection connection;

    public List<Product> getListProductByCategoryId(int id) throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT ID, NAME, PRICE, SELLER_NAME, CATEGORY_ID " +
                    "FROM PRODUCT " +
                    "WHERE CATEGORY_ID = ? " +
                    "ORDER BY NAME " +
                    "OFFSET 0 ROWS " +
                    "FETCH NEXT 5 ROWS ONLY";
            pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, id);

            ResultSet rs = pStatement.executeQuery();
            Product product;
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getInt(3));
                product.setSellerName(rs.getString(4));
                product.setCategoryId(rs.getInt(5));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return products;
    }

    public List<Product> getListProduct() throws SQLException {
        connection = JdbcConnectionDBUtil.getConnection();
        PreparedStatement pStatement = null;
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT ID, NAME, PRICE, SELLER_NAME, CATEGORY_ID " +
                    "FROM PRODUCT " +
                    "ORDER BY NAME " +
                    "OFFSET 0 ROWS " +
                    "FETCH NEXT 5 ROWS ONLY";
            pStatement = connection.prepareStatement(query);

            ResultSet rs = pStatement.executeQuery();
            Product product;
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getInt(3));
                product.setSellerName(rs.getString(4));
                product.setCategoryId(rs.getInt(5));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStatement != null){
                pStatement.close();
            }
            connection.close();
        }
        return products;
    }
}
