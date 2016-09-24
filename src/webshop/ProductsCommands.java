package webshop;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir on 24.09.2016..
 */
public class ProductsCommands {

    private ProductDao productDao;
    private User currentUser;

    public ProductsCommands(DatabaseTypes dbType) throws SQLException, ClassNotFoundException {
        DatabaseFactory dao = new DatabaseFactory(dbType);
        this.productDao = dao.getProductDao();
    }

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

    public void addProductToCatalog(String productName, String productCategory, float productPrice)
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);
        productDao.addProduct(productName, productCategory, productPrice);
    }

    public void disableProductInCatalog(Product product)
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);
        productDao.setProductStatusInCatalog(product.getProductID(), Constants.DISABLED);
    }

    public void enableProductInCatalog(Product product)
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);
        productDao.setProductStatusInCatalog(product.getProductID(), Constants.ENABLED);
    }

    public void changeProductPrice(Product product, float newPrice)
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);
        productDao.setProductPrice(product.getProductID(), newPrice);
    }

    public Map<Integer, Product> getProductMapFromCatalog() throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.USER);
        return productDao.getProductMap();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
