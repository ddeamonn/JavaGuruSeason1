package webshop;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir on 24.09.2016..
 */
public class ReportsCommands {

    private ReportDao reportDao;
    private User currentUser;

    public ReportsCommands(DatabaseTypes dbType) throws SQLException, ClassNotFoundException {
        DatabaseFactory dao = new DatabaseFactory(dbType);
        this.reportDao = dao.getReportDao();
    }

    public static Map<Integer, String> allowedMethods(User user) {

        Map<Integer, String> allowedMethods = new HashMap<>();

        if ((user.getUserRole() == UserRoleTypes.USER) || (user.getUserRole() == UserRoleTypes.ADMIN)) {
            allowedMethods.put(Constants.REPORT_ALL_SALES_BY_USER, "Show all sales for user");
        }

        if (user.getUserRole() == UserRoleTypes.ADMIN) {
            allowedMethods.put(Constants.REPORT_ALL_SALES, "Show all sales");
        }

        return allowedMethods;
    }

    public Map<Integer, Float> getAllSales()
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);
        return reportDao.getSalesWithSum();

    }

    public Map<Integer, Float> getSalesByUser(User user)
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.USER);
        return reportDao.getSalesByUser(this.currentUser);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}

