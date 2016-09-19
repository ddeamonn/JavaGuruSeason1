package webshop;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

        HashMap<Product, Integer> products = basket.getProductsInBasket();

        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            System.out.printf("Product %s quanity %n", product.getKey(), product.getValue() );
        }
    }

    public void removeProductFromBasket(Product product, UserBasket basket) {
        basket.removeProduct(product);
    }

    public void buyProductsInBasket(UserBasket basket) throws SQLException {
        dbo.buyProductsFromBasket(basket);
    }

    public void addProductToCatalog(String productName, String productCategory, float productPrice) throws SQLException {
        Security.checkRequredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.addProduct(productName, productCategory, productPrice);
    }

    public void disableProductInCatalog(Product product) throws SQLException {
        Security.checkRequredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.setProductStatusInCatalog(product.getProductID(), Constants.DISABLED);
    }

    public void enableProductInCatalog(Product product) throws SQLException {
        Security.checkRequredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.setProductStatusInCatalog(product.getProductID(), Constants.ENABLED);
    }

    public void changeProductPrice(Product product, float newPrice) throws SQLException {
        Security.checkRequredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.setProductPrice(product.getProductID(), newPrice);
    }

    public void disableUser(User user) throws SQLException {
        Security.checkRequredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.updateUser(user, user.getUserName(), user.getUserPassword(), user.getUserRole(), Constants.DISABLED);
    }

    public void enableUser(User user) throws SQLException {
        Security.checkRequredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.updateUser(user, user.getUserName(), user.getUserPassword(), user.getUserRole(), Constants.ENABLED);
    }

    public void showAllSales() throws SQLException {
        Security.checkRequredUserPermission(this.currentUser, userRoleType.ADMIN);
        List allSales = dbo.getSalesWithSum();
    }

}
