package webshop;

import java.sql.*;


/**
 * Created by Amir i Masha on 2016.09.22..
 */

public class DatabaseFactory {

    private static Connection dbSingleConnection = null;
    private UserDao userDao;
    private ProductDao productDao;
    private SalesDao salesDao;
    private ReportDao reportDao;


    public DatabaseFactory(DatabaseTypes dbType) throws SQLException, ClassNotFoundException {
        switch (dbType) {
            case SQLITE:
                if (this.dbSingleConnection == null) {
                    this.dbSingleConnection = DriverManager.getConnection(WebShopSettings.DbConnectionString);
                }
                this.userDao = new SqliteUserDao(dbSingleConnection);
                this.productDao = new SqliteProductDao(dbSingleConnection);
                this.salesDao = new SqliteSalesDao(dbSingleConnection);
                this.reportDao = new SqliteReportDao(dbSingleConnection);
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