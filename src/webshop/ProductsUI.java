package webshop;

import java.sql.SQLException;

/**
 * Created by Amir i Masha on 2016.10.04..
 */
public interface ProductsUI {
    public void showFormProducts() throws SQLException;

    public void showAllProducts() throws SQLException;
}
