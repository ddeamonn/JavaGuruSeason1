package webshop;

import java.sql.*;
import java.util.*;

/**
 * Created by Amir on 24.09.2016..
 */
public class SqliteInitDatabase implements InitDatabase {

    private Connection dbConnection;
    private d

    public SqliteInitDatabase() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dbConnection = dbConnection;
    }

    public void createDb() throws WebShopSqlException {

    }

    public void fillDatabaseWithSampleData() throws WebShopSqlException {

    }
}
