package webshop;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Amir i Masha on 2016.09.20..
 */
public class SqliteProductDao implements ProductDao {

    Connection dbConnection;

    SqliteProductDao(Connection dbConnection) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean checkProductExists(String productName, String productCategory) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM PRODUCTS WHERE (NAME = ?) AND (CATEGORY = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, productName);
        sqlStatement.setString(2, productCategory);

        ResultSet result = sqlStatement.executeQuery();
        if (result.next()) {
            return true;
        }
        return false;
    }

    public List getProductCategories() throws SQLException {

        List<String> categoryList = new LinkedList<>();

        PreparedStatement sqlStatement = null;
        String sql = "SELECT DISTINCT(Category) FROM Products";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        ResultSet result = sqlStatement.executeQuery();

        while (result.next()) {
            String queryProductCategory = result.getString("Category");
            categoryList.add(queryProductCategory);
        }

        return categoryList;
    }

    @Override
    public void addProduct(String productName, String productCategory, float productPrice) throws SQLException {

        //if (checkProductExists(productName, productCategory)) throw new WebShopSqlException("Product already exists");

        PreparedStatement sqlStatement = null;
        String sql = "INSERT INTO Products(Name, Category, Price) VALUES (?, ?, ?)";

        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, productName);
        sqlStatement.setString(2, productCategory);
        sqlStatement.setFloat(3, productPrice);

        int rowsAffected = sqlStatement.executeUpdate();

    }

    @Override
    public Product getProductByName(String productName) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM PRODUCTS WHERE Name = ?";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        sqlStatement.setString(1, productName);
        ResultSet result = sqlStatement.executeQuery();
        while (result.next()) {

            int queryProductID = result.getInt("Id");
            String queryProductName = result.getString("Name");
            String queryProductCategory = result.getString("Category");
            int queryProductPrice = result.getInt("Price");

            Product product = new Product(queryProductID, queryProductName, queryProductCategory, queryProductPrice);

            return product;
        }

        return null;
    }

    @Override
    public void setProductPrice(int productId, float newPrice) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "UPDATE PRODUCTS SET PRICE = ? WHERE (ID=?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        sqlStatement.setInt(1, productId);
        sqlStatement.setFloat(2, newPrice);

        sqlStatement.executeUpdate();
    }

    @Override
    public List getProductsByCategory(String category) throws SQLException {

        List<Product> productList = new LinkedList<>();

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM PRODUCTS WHERE Category = ?";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, category);

        ResultSet result = sqlStatement.executeQuery();
        while (result.next()) {

            int queryProductID = result.getInt("Id");
            String queryProductName = result.getString("Name");
            String queryProductCategory = result.getString("Category");
            int queryProductPrice = result.getInt("Price");

            Product product = new Product(queryProductID, queryProductName, queryProductCategory, queryProductPrice);

            productList.add(product);

        }

        return productList;
    }

    @Override
    public void setProductStatusInCatalog(int productID, boolean productStatus) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "UPDATE PRODUCTS SET AVAILABILITY = ? WHERE (ID=?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setInt(1, productID);
        sqlStatement.setBoolean(2, productStatus);
        sqlStatement.executeUpdate();
    }
}
