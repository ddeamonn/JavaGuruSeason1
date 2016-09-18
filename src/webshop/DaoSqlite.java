package webshop;
import java.sql.*;
import java.util.*;
import java.util.Date;

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
    public int addUser(String userName, String userPassword, userRoleType userType) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "INSERT INTO USERS(Name, Password, Role) VALUES (?, ?, ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, userName);
        sqlStatement.setString(2, userPassword);
        sqlStatement.setString(3, userType.toString());

        int rowsAffected = sqlStatement.executeUpdate();
        if (rowsAffected == 0) return Constants.OP_SUCCESS;
        return Constants.OP_ERROR;
    }

    @Override
    public User getUser(String userName, String userPassword) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM USERS WHERE (Name = ?) AND (Password = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, userName);
        sqlStatement.setString(2, userPassword);

        ResultSet result = sqlStatement.executeQuery();
        while (result.next()){

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
    public int updateUser(User user, String newUserName, String newUserPassword, userRoleType newUserRole) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "UPDATE USERS SET Name = ?, Password = ?, Role = ? WHERE (UserName=?) AND (UserRole=?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, newUserName);
        sqlStatement.setString(2, newUserPassword);
        sqlStatement.setString(3, newUserRole.toString());
        sqlStatement.setString(4, user.getUserName());
        sqlStatement.setString(5, user.getUserRole().toString());

        int rowsAffected = sqlStatement.executeUpdate();
        if (rowsAffected > 0) return Constants.OP_SUCCESS;
        return Constants.OP_ERROR;
    }

    @Override
    public int addProduct(String productName, String productCategory, float productPrice) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "INSERT INTO Products(Name, Category, Price) VALUES (?, ?, ?)";

        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, productName);
        sqlStatement.setString(2, productCategory);
        sqlStatement.setFloat(3, productPrice);

        int rowsAffected = sqlStatement.executeUpdate();

        if (rowsAffected == 0) return Constants.OP_SUCCESS;
        return Constants.OP_ERROR;

    }

    @Override
    public Product getProductByName(String productName) throws SQLException{

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM PRODUCTS WHERE Name = ?";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        sqlStatement.setString(1, productName);
        ResultSet result = sqlStatement.executeQuery();
        while (result.next()){

            String queryProductName = result.getString("Name");
            String queryProductCategory = result.getString("Category");
            int queryProductPrice = result.getInt("Price");

            Product product = new Product(queryProductName, queryProductCategory, queryProductPrice);

            return product;
        }

        return null;
    }

    @Override
    public List getProductsByCategory(String category) throws SQLException{

        List<Product> productList = new LinkedList<>();

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM PRODUCTS WHERE Category = ?";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, category);

        ResultSet result = sqlStatement.executeQuery();
        while (result.next()){

            String queryProductName = result.getString("Name");
            String queryProductCategory = result.getString("Category");
            int queryProductPrice = result.getInt("Price");

            Product product = new Product(queryProductName, queryProductCategory, queryProductPrice);

            productList.add(product);

        }

        return productList;
    }

    @Override
    public int addProductToBasket(UserBasket basket, Product product) throws SQLException {

        return Constants.OP_ERROR;
    }


    @Override
    public int buyProductsFromBasket(UserBasket basket) throws SQLException {

        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        User buyer = basket.getBasketUser();
        List products = basket.getProducts();

        PreparedStatement sqlStatement = null;
        String sql = "INSERT INTO SALES(DateTime, UserID, ProductID) values (?, ?, ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setDate(1, currentDate);

        ResultSet result = sqlStatement.executeQuery();

        Iterator<Product> productIterator = products.iterator();

        while(productIterator.hasNext()) {
            Product product = productIterator.next();
            Ui.showMessage(product.toString());
        }

        return Constants.OP_ERROR;
    }

}
