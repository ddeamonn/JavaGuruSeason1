package webshop;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir on 24.09.2016..
 */
public class SalesManipulationMethods {
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
}
