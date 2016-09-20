package webshop;

import java.sql.SQLException;

/**
 * Created by Amir i Masha on 2016.09.20..
 */
public interface SalesDao {

    public void addProductToBasket(UserBasket basket, Product product) throws SQLException;

    public void buyProductsFromBasket(UserBasket basket) throws SQLException;

}
