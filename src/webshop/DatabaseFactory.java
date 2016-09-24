package webshop;

import java.sql.*;


/**
 * Created by Amir i Masha on 2016.09.22..
 */

public class DatabaseFactory {

    private UserDao userDao;
    private ProductDao productDao;
    private SalesDao salesDao;
    private ReportDao reportDao;


    public DatabaseFactory(DatabaseTypes dbType) throws SQLException, ClassNotFoundException {
        switch (dbType) {
            case SQLITE:
                Connection dbConnection = DriverManager.getConnection(WebShopSettings.DbConnectionString);
                this.userDao = new SqliteUserDao(dbConnection);
                this.productDao = new SqliteProductDao(dbConnection);
                this.salesDao = new SqliteSalesDao(dbConnection);
                this.reportDao = new SqliteReportDao(dbConnection);
        }
    }

    public UserDao getUserDao() {
        return this.userDao;
    }

    public ProductDao getProductDao() {
        return this.productDao;
    }

    public SalesDao getSalesDao() {
        return salesDao;
    }

    public ReportDao getReportDao() {
        return reportDao;
    }
}