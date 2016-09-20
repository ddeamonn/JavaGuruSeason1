package webshop;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amir on 06.09.2016..
 */

public class UserUIMethods implements ClientMethodsInterface, AdminMethodsInterface {

    Dao dbo;
    User currentUser;

    public UserUIMethods(String dbConnectionString) throws SQLException, ClassNotFoundException {
        this.dbo = new DaoSqlite(dbConnectionString);
    }

    public static HashMap<Integer, String> allowedMethods(User user) {

        HashMap<Integer, String> allowedMethods = new HashMap<>();

        if ((user.getUserRole() == userRoleType.USER) || (user.getUserRole() == userRoleType.ADMIN)) {
            allowedMethods.put(Constants.LOGIN_USER, "Login user");
            allowedMethods.put(Constants.LOGOUT_USER, "Login logout");
            allowedMethods.put(Constants.PRODUCTS_SHOW_IN_CATALOG, "Show products in catalog");
            allowedMethods.put(Constants.BASKET_ADD_PRODUCT, "Add product to basket");
            allowedMethods.put(Constants.BASKET_REMOVE_PRODUCT, "Remove product from basket");
            allowedMethods.put(Constants.BASKET_BUY_PRODUCTS, "Buy products in basket");
        }

        if (user.getUserRole() == userRoleType.ADMIN) {
            allowedMethods.put(Constants.CATALOG_ADD_PRODUCT, "Add new product to catalog");
            allowedMethods.put(Constants.CATALOG_DISABLE_PRODUCT, "Disable product in catalog");
            allowedMethods.put(Constants.CATALOG_ENABLE_PRODUCT, "Enable product in catalog");
            allowedMethods.put(Constants.CATALOG_CHANGE_PRODUCT_PRICE, "Change product price in catalog");
            allowedMethods.put(Constants.USER_ADD_USER, "Add user to webshop database");
            allowedMethods.put(Constants.USER_ENABLE_USER, "Enable user in webshop database");
            allowedMethods.put(Constants.USER_DISABLE_USER, "Disable user in webshop database");
        }

        return allowedMethods;
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
