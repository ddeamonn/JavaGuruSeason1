package webshop;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir on 24.09.2016..
 */
public class ProductManipulationMethods {
    public static Map<Integer, String> allowedMethods(User user) {

        Map<Integer, String> allowedMethods = new HashMap<>();

        if ((user.getUserRole() == UserRoleTypes.USER) || (user.getUserRole() == UserRoleTypes.ADMIN)) {
            allowedMethods.put(Constants.PRODUCTS_SHOW_IN_CATALOG, "Show products in catalog");
        }

        if (user.getUserRole() == UserRoleTypes.ADMIN) {
            allowedMethods.put(Constants.CATALOG_ADD_PRODUCT, "Add new product to catalog");
            allowedMethods.put(Constants.CATALOG_DISABLE_PRODUCT, "Disable product in catalog");
            allowedMethods.put(Constants.CATALOG_ENABLE_PRODUCT, "Enable product in catalog");
            allowedMethods.put(Constants.CATALOG_CHANGE_PRODUCT_PRICE, "Change product price in catalog");
        }

        return allowedMethods;
    }
}
