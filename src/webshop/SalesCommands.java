package webshop;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir on 24.09.2016..
 */
public class SalesCommands {

    private SalesDao salesDao;
    private User currentUser;

    public SalesCommands(DatabaseTypes dbType) throws SQLException, ClassNotFoundException {
        DatabaseFactory dao = new DatabaseFactory(dbType);
        this.salesDao = dao.getSalesDao();
    }

    public static Map<Integer, String> allowedMethods(User user) {

        Map<Integer, String> allowedMethods = new HashMap<>();

        if ((user.getUserRole() == UserRoleTypes.USER) || (user.getUserRole() == UserRoleTypes.ADMIN)) {
            allowedMethods.put(Constants.BASKET_SHOW_PRODUCTS, "Show product in basket");
            allowedMethods.put(Constants.BASKET_ADD_PRODUCT, "Add product to basket");
            allowedMethods.put(Constants.BASKET_REMOVE_PRODUCT, "Remove product from basket");
            allowedMethods.put(Constants.BASKET_BUY_PRODUCTS, "Buy products in basket");
        }

        if (user.getUserRole() == UserRoleTypes.ADMIN) {
        }

        return allowedMethods;
    }

    public Map<Product, Integer> getProductsFromBasket() throws SQLException {
        return salesDao.getProductsFromBasket(this.currentUser);
    }

    public Product getProductsFromBasket(Product product) throws SQLException {
        return salesDao.getProductFromBasket(this.currentUser, product);
    }

    public void addProductToBasket(Product product) throws SQLException {
        salesDao.addProductToBasket(this.currentUser, product);
    }

    public void removeProductFromBasket(Product product) throws SQLException {
        salesDao.removeProductFromBasket(this.currentUser, product);
    }

    public void buyProductsInBasket() throws SQLException {
        salesDao.buyProductsFromBasket(this.currentUser);
    }

    public void cleanUserBasket() throws SQLException {
        salesDao.cleanUserBasket(this.currentUser);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
