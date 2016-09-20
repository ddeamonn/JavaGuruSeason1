package webshop;

import java.sql.*;
import java.util.List;

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
    public List getSalesWithSum() throws SQLException {

        PreparedStatement sqlStatement = null;

        String sql = "SELECT SaleID as SID, SUM(SaleSum) as Sum FROM SALES GROUP BY SaleID";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        ResultSet result = sqlStatement.executeQuery();
        while (result.next()) {
            int querySaleID = result.getInt("SID");
            float querySaleSum = result.getFloat("Sum");
        }

        return null;

    }
}