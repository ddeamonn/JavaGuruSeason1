package webshop;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Amir i Masha on 2016.09.20..
 */
public interface SalesDao {

    public void addProductToBasket(User user, Product product) throws SQLException;

    public void removeProductFromBasket(User user, Product product) throws SQLException;

    public void cleanUserBasket(User user) throws SQLException;

    public void buyProductsFromBasket(User user) throws SQLException;

    public Product getProductFromBasket(User user, Product product) throws SQLException;

    public Map<Product, Integer> getProductsFromBasket(User user) throws SQLException;

}
