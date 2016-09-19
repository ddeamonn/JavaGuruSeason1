package webshop;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

/**
 * Created by Amir on 06.09.2016..
 */

public class DaoSqlite implements Dao {

    Connection dbConnection;

    DaoSqlite(String dbPath) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dbConnection = DriverManager.getConnection(dbPath);
    }

    @Override
    public void addUser(String userName, String userPassword, userRoleType userType) throws SQLException {

        if (checkUserExists(userName)) throw new WebShopSqlException("User with name " + userName + " already exists");

        PreparedStatement sqlStatement = null;
        String sql = "INSERT INTO USERS(Name, Password, Role) VALUES (?, ?, ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        sqlStatement.setString(1, userName);
        sqlStatement.setString(2, userPassword);
        sqlStatement.setString(3, userType.toString());

        sqlStatement.executeUpdate();
    }

    @Override
    public boolean checkUserExists(String userName) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM USERS WHERE (Name = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, userName);

        ResultSet result = sqlStatement.executeQuery();
        if (result.next()) {
            return true;
        }
        return false;
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

    @Override
    public User getUser(String userName, String userPassword) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM USERS WHERE (Name = ?) AND (Password = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, userName);
        sqlStatement.setString(2, userPassword);

        ResultSet result = sqlStatement.executeQuery();

        while (result.next()) {

            int queryUserId = result.getInt("Id");
            String queryUserName = result.getString("Name");
            String queryUserPassword = result.getString("Password");
            String queryUserRoleName = result.getString("Role");

            User usr = new User(queryUserId, queryUserName, queryUserPassword, userRoleType.valueOf(queryUserRoleName));

            return usr;
        }

        return null;
    }

    @Override
    public void updateUser(User user, String newUserName, String newUserPassword,
                           userRoleType newUserRole, boolean userEnabled) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "UPDATE USERS SET Name = ?, Password = ?, Role = ?, Enabled = ? WHERE (UserName=?) AND (UserRole=?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, newUserName);
        sqlStatement.setString(2, newUserPassword);
        sqlStatement.setString(3, newUserRole.toString());
        sqlStatement.setBoolean(4, userEnabled);
        sqlStatement.setString(5, user.getUserName());
        sqlStatement.setString(6, user.getUserRole().toString());

        sqlStatement.executeUpdate();
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

        if (checkProductExists(productName, productCategory)) throw new WebShopSqlException("Product already exists");

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
    public void addProductToBasket(UserBasket basket, Product product) throws SQLException {
        basket.addProduct(product);
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

    @Override
    public void buyProductsFromBasket(UserBasket basket) throws SQLException {

        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

        User buyer = basket.getBasketUser();
        HashMap<Product, Integer> products = basket.getProducts();

        int saleID = 1;

        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            PreparedStatement sqlStatement = null;
            String sql = "INSERT INTO SALES(SaleID, DateTime, UserID, ProductID, Quantity, Price, SaleSum) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            sqlStatement = this.dbConnection.prepareStatement(sql);
            sqlStatement.setInt(1, saleID);
            sqlStatement.setDate(2, currentDate);
            sqlStatement.setInt(3, buyer.getUserID());
            sqlStatement.setInt(4, product.getKey().getProductID());
            sqlStatement.setInt(5, product.getValue());
            sqlStatement.setFloat(6, product.getKey().getProductPrice());
            sqlStatement.setFloat(7, product.getKey().getProductPrice() * product.getValue());

            sqlStatement.executeUpdate();
        }
    }

    @Override
    public List getSalesWithSum() throws SQLException {

        PreparedStatement sqlStatement = null;

        String sql = "SELECT SaleID as SID, SUM(SaleSum) as Sum FROM SALES GROUP BY SaleID";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        ResultSet result = sqlStatement.executeQuery();
        while (result.next()) {
            int querySaleID = result.getInt("SID");
            float querySaleSum = result.getFloat("Sum");
        }

        return null;

    }

}

