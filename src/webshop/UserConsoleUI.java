package webshop;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Amir on 06.09.2016..
 */

public class UserConsoleUI implements ClientMethodsInterface, AdminMethodsInterface {

    Dao dbo;
    User currentUser;

    public UserConsoleUI(String dbConnectionString) throws SQLException, ClassNotFoundException {
        this.dbo = new DaoSqlite(dbConnectionString);
    }

    public User userLogin(String userName, String userPassword) throws SQLException {
        return this.dbo.getUser(userName, userPassword);
    }

    public void userLogout(User user) {
        currentUser = null;
    }

    public void addProductToBasket(Product product, UserBasket basket) {
        basket.addProduct(product);
    }

    public void showProductsInBasket(UserBasket basket) {
        List<Product> products = basket.getProductsInBasket();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void removeProductFromBasket(Product product, UserBasket basket) {
        basket.removeProduct(product);
    }

    public void buyProductsInBasket(UserBasket basket) throws SQLException {
        dbo.buyProductsFromBasket(basket);
    }

    public void addProductToCatalog(String productName, String productCategory, float productPrice) throws SQLException {
        dbo.addProduct(productName, productCategory, productPrice);
    }

    public void disableProductInCatalog(Product product) throws SQLException {
        dbo.setProductStatusInCatalog(product.getProductID(), Constants.DISABLED);
    }

    public void enableProductInCatalog(Product product) throws SQLException {
        dbo.setProductStatusInCatalog(product.getProductID(), Constants.ENABLED);
    }

    public void changeProductPrice(Product product, float newPrice) throws SQLException {
        dbo.setProductPrice(product.getProductID(), newPrice);
    }

    public void disableUser(User user) throws SQLException {
        dbo.updateUser(user, user.getUserName(), user.getUserPassword(), user.getUserRole(), Constants.DISABLED);
    }

    public void enableUser(User user) throws SQLException {
        dbo.updateUser(user, user.getUserName(), user.getUserPassword(), user.getUserRole(), Constants.ENABLED);
    }

    public void showAllSales() throws SQLException {
        List allSales = dbo.getSalesWithSum();
    }

}
