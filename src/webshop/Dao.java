package webshop;
import java.sql.SQLException;
import java.util.*;
/**
 * Created by Amir on 13.09.2016..
 */
public interface Dao {

    public int addUser(String userName, String userPassword, userRoleType userType) throws SQLException;
    public User getUser(String userName, String userPassword) throws SQLException;
    public int updateUser(User user, String newUserName, String newUserPassword, userRoleType newUserRole) throws SQLException;

    public int addProduct(String productName, String productCategory, float productPrice) throws SQLException;
    public Product getProductByName(String productName) throws SQLException;
    public List getProductsByCategory(String category) throws SQLException;

    public int addProductToBasket(UserBasket basket, Product product) throws SQLException;
    public int buyProductsFromBasket(UserBasket basket) throws SQLException;
}
