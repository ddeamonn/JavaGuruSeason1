package webshop;

import java.sql.SQLException;

/**
 * Created by Amir i Masha on 2016.09.18..
 */
public interface AdminMethodsInterface {
    public void addProductToCatalog(String productName, String productCategory, float productPrice)
            throws SQLException, SecurityException;

    public void disableProductInCatalog(Product product) throws SQLException, SecurityException;

    public void enableProductInCatalog(Product product) throws SQLException, SecurityException;

    public void changeProductPrice(Product product, float newPrice) throws SQLException, SecurityException;

    public void disableUser(User user) throws SQLException, SecurityException;

    public void enableUser(User user) throws SQLException, SecurityException;

    public void showAllSales() throws SQLException, SecurityException;
}
