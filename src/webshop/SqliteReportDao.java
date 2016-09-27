package webshop;

import java.sql.*;
import java.util.*;


/**
 * Created by Amir i Masha on 2016.09.20..
 */
public class SqliteReportDao implements ReportDao {

    Connection dbConnection;

    SqliteReportDao(Connection dbConnection) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dbConnection = dbConnection;
    }

    @Override
    public Map<Integer, Float> getSalesWithSum() throws SQLException {

        Map<Integer, Float> sales = new HashMap<>();
        PreparedStatement sqlStatement = null;

        String sql = "SELECT SaleID as SID, SUM(SaleSum) as Sum FROM SALES GROUP BY SaleID";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        ResultSet result = sqlStatement.executeQuery();
        while (result.next()) {
            sales.put(result.getInt("SID"), result.getFloat("Sum"));
        }

        return sales;

    }

    @Override
    public Map<Integer, Float> getSalesByUser(User user) throws SQLException {

        Map<Integer, Float> sales = new HashMap<>();
        PreparedStatement sqlStatement = null;

        String sql = "SELECT SaleID as SID, SUM(SaleSum) as Sum FROM SALES WHERE USERID = ? GROUP BY SaleID";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setInt(1, user.getUserID());
        ResultSet result = sqlStatement.executeQuery();
        while (result.next()) {
            sales.put(result.getInt("SID"), result.getFloat("Sum"));
        }

        return sales;

    }
}
