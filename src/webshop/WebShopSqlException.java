package webshop;

import java.sql.SQLException;

/**
 * Created by Amir i Masha on 2016.09.18..
 */

public class WebShopSqlException extends SQLException {
    public WebShopSqlException(String message) {
        super(message);
    }
}
