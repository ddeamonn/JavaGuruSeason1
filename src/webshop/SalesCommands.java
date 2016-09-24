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
            allowedMethods.put(Constants.BASKET_ADD_PRODUCT, "Add product to basket");
            allowedMethods.put(Constants.BASKET_REMOVE_PRODUCT, "Remove product from basket");
            allowedMethods.put(Constants.BASKET_BUY_PRODUCTS, "Buy products in basket");
        }

        if (user.getUserRole() == UserRoleTypes.ADMIN) {
        }

        return allowedMethods;
    }

    public void addProductToBasket(Product product, UserBasket basket) {
        basket.addProduct(product);
    }

    public void removeProductFromBasket(Product product, UserBasket basket) {
        basket.removeProduct(product);
    }

    public void buyProductsInBasket(UserBasket basket) throws SQLException {
        salesDao.buyProductsFromBasket(basket);
    }

}
