package webshop;

import org.omg.CORBA.UnknownUserException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Amir on 06.09.2016..
 */

public class UserUIMethods implements ClientMethodsInterface, AdminMethodsInterface {

    Dao dbo;
    User currentUser;

    public static List allowedMethods(User user) {

        List<String> allowedMethods = new LinkedList<>();

        allowedMethods.add("Login user");
        allowedMethods.add("Login logout");
        allowedMethods.add("Add product to basket");
        allowedMethods.add("Remove product from basket");
        allowedMethods.add("Buy products in basket");

        if (user.getUserRole() == userRoleType.ADMIN) {
            allowedMethods.add("Add new product to catalog");
            allowedMethods.add("Disable product in catalog");
            allowedMethods.add("Enable product in catalog");
            allowedMethods.add("Change product price in catalog");
            allowedMethods.add("Add user to webshop");
            allowedMethods.add("Enable user in webshop");
            allowedMethods.add("Disable user in webshop");
        }

        return allowedMethods;
    }

    public UserUIMethods(String dbConnectionString) throws SQLException, ClassNotFoundException {
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
            System.out.printf("Product %s quantity %n", product.getKey(), product.getValue() );
        }
    }

    public void removeProductFromBasket(Product product, UserBasket basket) {
        basket.removeProduct(product);
    }

    public void buyProductsInBasket(UserBasket basket) throws SQLException {
        dbo.buyProductsFromBasket(basket);
    }

    public void addProductToCatalog(String productName, String productCategory, float productPrice)
            throws SQLException, SecurityException {
        Security.checkRequiredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.addProduct(productName, productCategory, productPrice);
    }

    public void disableProductInCatalog(Product product)
            throws SQLException, SecurityException {
        Security.checkRequiredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.setProductStatusInCatalog(product.getProductID(), Constants.DISABLED);
    }

    public void enableProductInCatalog(Product product)
            throws SQLException, SecurityException {
        Security.checkRequiredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.setProductStatusInCatalog(product.getProductID(), Constants.ENABLED);
    }

    public void changeProductPrice(Product product, float newPrice)
            throws SQLException, SecurityException {
        Security.checkRequiredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.setProductPrice(product.getProductID(), newPrice);
    }

    public void disableUser(User user)
            throws SQLException, SecurityException {
        Security.checkRequiredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.updateUser(user, user.getUserName(), user.getUserPassword(), user.getUserRole(), Constants.DISABLED);
    }

    public void enableUser(User user)
            throws SQLException, SecurityException {
        Security.checkRequiredUserPermission(this.currentUser, userRoleType.ADMIN);
        dbo.updateUser(user, user.getUserName(), user.getUserPassword(), user.getUserRole(), Constants.ENABLED);
    }

    public void showAllSales()
            throws SQLException, SecurityException {
        Security.checkRequiredUserPermission(this.currentUser, userRoleType.ADMIN);
        List allSales = dbo.getSalesWithSum();
    }

}
