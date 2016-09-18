package webshop;

import java.sql.SQLException;

/**
 * Created by Amir on 2016.09.18..
 */
public interface ClientMethodsInterface {
    public User userLogin(String userName, String userPassword) throws SQLException;

    public void userLogout(User user);

    public void addProductToBasket(Product product, UserBasket basket);

    public void showProductsInBasket(UserBasket basket);

    public void removeProductFromBasket(Product product, UserBasket basket);

    public void buyProductsInBasket(UserBasket basket) throws SQLException;
}
