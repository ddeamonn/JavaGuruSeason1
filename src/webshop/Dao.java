package webshop;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Amir on 13.09.2016..
 */
public interface Dao {

    public void addUser(String userName, String userPassword, userRoleType userType) throws SQLException;

    public User getUser(String userName, String userPassword) throws SQLException;

    public boolean checkUserExists(String userName) throws SQLException;

    public void updateUser(User user, String newUserName, String newUserPassword,
                           userRoleType newUserRole, boolean userEnabled) throws SQLException;

    public List getProductCategories() throws SQLException;

    public void addProduct(String productName, String productCategory, float productPrice) throws SQLException;

    public void setProductPrice(int productID, float productPrice) throws SQLException;

    public Product getProductByName(String productName) throws SQLException;

    public List getProductsByCategory(String category) throws SQLException;

    public boolean checkProductExists(String productName, String productCategory) throws SQLException;

    public void setProductStatusInCatalog(int productID, boolean productStatus) throws SQLException;

    public void addProductToBasket(UserBasket basket, Product product) throws SQLException;

    public void buyProductsFromBasket(UserBasket basket) throws SQLException;

    public List getSalesWithSum() throws SQLException;
}
