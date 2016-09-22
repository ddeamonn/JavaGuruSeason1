package webshop;

import java.sql.*;

/**
 * Created by Amir i Masha on 2016.09.22..
 */

public class WebShopDbFactory {
    public static Connection getDbConnection(WebShopDbTypes dbType) throws SQLException {
        switch (dbType) {
            case SQLITE:
                Connection dbConnection = DriverManager.getConnection(WebShopSettings.DbConnectionString);
                return dbConnection;
        }
        return null;
    }
}