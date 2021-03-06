package webshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir on 2016.09.20..
 */
public class SqliteSalesDao implements SalesDao {

    Connection dbConnection;

    SqliteSalesDao(Connection dbConnection) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dbConnection = dbConnection;
    }

    @Override
    public Map<Product, Integer> getProductsFromBasket(User user) throws SQLException {

        Map<Product, Integer> productMap = new HashMap<>();

        PreparedStatement sqlStatement = null;

        String sql = " select Products.Id as pid, Products.Name as pname, Products.Category as pcategory," +
                " Products.Price as pprice, Products.Status as pstatus, Basket.Quantity as bquantity" +
                " from Users, Products, Basket " +
                " where (Basket.ProductId = Products.Id) and (Basket.UserId = ?)";

        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setInt(1, user.getUserID());
        ResultSet result = sqlStatement.executeQuery();

        while (result.next()) {

            int queryProductId = result.getInt("pid");
            String queryProductName = result.getString("pname");
            String queryProductCategory = result.getString("pcategory");
            float queryProductPrice = result.getFloat("pprice");
            int queryProductQuantity = result.getInt("bquantity");
            ProductStatus queryProductStatus = ProductStatus.valueOf(result.getString("pstatus"));

            Product product = new Product(queryProductId, queryProductName, queryProductCategory,
                    queryProductPrice, queryProductStatus);

            productMap.put(product, queryProductQuantity);
        }

        return productMap;
    }

    @Override
    public Product getProductFromBasket(User user, Product product) throws SQLException {

        PreparedStatement sqlStatement = null;

        String sql = " select Products.Id as pid, Products.Name as pname, Products.Category as pcategory," +
                " Products.Price as pprice, Products.Status as pstatus, Basket.Quantity as bquantity" +
                " from Users, Products, Basket " +
                " where (Basket.ProductId = ?) and (Basket.UserId = ?)";

        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setInt(1, product.getProductID());
        sqlStatement.setInt(2, user.getUserID());
        ResultSet result = sqlStatement.executeQuery();

        while (result.next()) {

            int queryProductId = result.getInt("pid");
            String queryProductName = result.getString("pname");
            String queryProductCategory = result.getString("pcategory");
            float queryProductPrice = result.getFloat("pprice");
            int queryProductQuantity = result.getInt("bquantity");
            ProductStatus queryProductStatus = ProductStatus.valueOf(result.getString("pstatus"));

            Product dbProduct = new Product(queryProductId, queryProductName, queryProductCategory,
                    queryProductPrice, queryProductStatus);

            return dbProduct;
        }

        return null;
    }

    @Override
    public void addProductToBasket(User user, Product product) throws SQLException {

        Product basketProduct = this.getProductFromBasket(user, product);
        int quantity = 1;
        if (basketProduct != null) {

            PreparedStatement sqlStatement = null;
            String sql = "SELECT QUANTITY FROM BASKET WHERE (PRODUCTID = ?) AND (USERID = ?)";
            sqlStatement = this.dbConnection.prepareStatement(sql);
            sqlStatement.setInt(1, product.getProductID());
            sqlStatement.setInt(2, user.getUserID());
            ResultSet result = sqlStatement.executeQuery();
            while (result.next()) {
                quantity = result.getInt("QUANTITY");
            }

            sql = "UPDATE BASKET SET QUANTITY = ? WHERE (USERID = ?) AND (PRODUCTID = ?)";
            sqlStatement = this.dbConnection.prepareStatement(sql);
            sqlStatement.setInt(1, quantity + 1);
            sqlStatement.setInt(2, user.getUserID());
            sqlStatement.setInt(3, product.getProductID());
            sqlStatement.executeUpdate();
        } else {
            String sql = "INSERT INTO BASKET(USERID, PRODUCTID, QUANTITY) VALUES (?, ?, ?)";
            PreparedStatement sqlStatement = null;
            sqlStatement = this.dbConnection.prepareStatement(sql);
            sqlStatement.setInt(1, user.getUserID());
            sqlStatement.setInt(2, product.getProductID());
            sqlStatement.setInt(3, product.getProductID());
            sqlStatement.setInt(3, quantity);
            sqlStatement.executeUpdate();
        }

    }

    @Override
    public void buyProductsFromBasket(User user) throws SQLException {

        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

        User buyer = user;
        int saleID = 1;

        Map<Product, Integer> products = this.getProductsFromBasket(user);

        PreparedStatement sqlStatement = null;

        String sql = "SELECT MAX(SaleID) as SaleID FROM SALES";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        ResultSet result = sqlStatement.executeQuery();
        while (result.next()) {
            saleID = result.getInt("SaleID");
        }

        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            sql = "INSERT INTO SALES(SaleID, DateTime, UserID, ProductID, Quantity, Price, SaleSum) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            sqlStatement = this.dbConnection.prepareStatement(sql);
            sqlStatement.setInt(1, saleID + 1);
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
    public void removeProductFromBasket(User user, Product product) throws SQLException {
        PreparedStatement sqlStatement = null;
        String sql = "DELETE FROM BASKET WHERE (USERID = ?) AND (PRODUCTID = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setInt(1, user.getUserID());
        sqlStatement.setInt(2, product.getProductID());
        sqlStatement.executeUpdate();
    }

    @Override
    public void cleanUserBasket(User user) throws SQLException {
        PreparedStatement sqlStatement = null;
        String sql = "DELETE FROM BASKET WHERE USERID = ?";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setInt(1, user.getUserID());
        sqlStatement.executeUpdate();
    }
}
