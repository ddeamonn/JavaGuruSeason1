package webshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir i Masha on 2016.09.20..
 */
public class SqliteSalesDao implements SalesDao {

    Connection dbConnection;

    SqliteSalesDao(Connection dbConnection) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dbConnection = dbConnection;
    }

    @Override
    public void addProductToBasket(UserBasket basket, Product product) throws SQLException {
        basket.addProduct(product);
    }

    @Override
    public void buyProductsFromBasket(UserBasket basket) throws SQLException {

        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

        User buyer = basket.getBasketUser();
        HashMap<Product, Integer> products = basket.getProductsInBasket();

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
}
