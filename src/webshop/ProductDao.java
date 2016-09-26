package webshop;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Amir i Masha on 2016.09.20..
 */
public interface ProductDao {
    public void addProduct(String productName, String productCategory, float productPrice) throws SQLException;

    public void setProductPrice(int productID, float productPrice) throws SQLException;

    public Product getProductByName(String productName) throws SQLException;

    public Product getProductById(int productId) throws SQLException;

    public List getProductsByCategory(String category) throws SQLException;

    public boolean checkProductExists(String productName, String productCategory) throws SQLException;

    public void setProductStatusInCatalog(int productID, boolean productStatus) throws SQLException;

    public Map<Integer, Product> getProductMap() throws SQLException;

}
