package webshop;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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
        }

        if (user.getUserRole() == UserRoleTypes.ADMIN) {
            allowedMethods.put(Constants.USER_ADD_USER, "Add user to webshop database");
            allowedMethods.put(Constants.USER_LIST_USERS, "Show all users in webshop database");
            allowedMethods.put(Constants.USER_ENABLE_USER, "Enable user in webshop database");
            allowedMethods.put(Constants.USER_DISABLE_USER, "Disable user in webshop database");
        }

        return allowedMethods;
    }

    public void showAllSales()
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);
        List allSales = reportDao.getSalesWithSum();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}

