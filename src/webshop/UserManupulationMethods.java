package webshop;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Amir on 24.09.2016..
 */
public class UserManupulationMethods {

    private UserDao userDao;
    private User currentUser;

    public UserManupulationMethods(DatabseTypes dbType) throws SQLException, ClassNotFoundException {
        DatabaseFactory dao = new DatabaseFactory(dbType);
        this.userDao = dao.getUserDao();
    }

    public static Map<Integer, String> allowedMethods(User user) {

        Map<Integer, String> allowedMethods = new HashMap<>();

        if ((user.getUserRole() == UserRoleTypes.USER) || (user.getUserRole() == UserRoleTypes.ADMIN)) {
            allowedMethods.put(Constants.LOGIN_USER, "Login user");
            allowedMethods.put(Constants.LOGOUT_USER, "Login logout");
        }

        if (user.getUserRole() == UserRoleTypes.ADMIN) {
            allowedMethods.put(Constants.USER_ADD_USER, "Add user to webshop database");
            allowedMethods.put(Constants.USER_LIST_USERS, "Show all users in webshop database");
            allowedMethods.put(Constants.USER_ENABLE_USER, "Enable user in webshop database");
            allowedMethods.put(Constants.USER_DISABLE_USER, "Disable user in webshop database");
        }

        return allowedMethods;
    }

    public User userLogin(String userName, String userPassword) throws SQLException {
        return this.userDao.getUser(userName, userPassword);
    }

    public void userLogout(User user) {
        currentUser = null;
    }

    public void disableUser(User user)
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);
        userDao.updateUser(user, user.getUserName(), user.getUserPassword(), user.getUserRole(), Constants.DISABLED);
    }

    public void enableUser(User user)
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);
        userDao.updateUser(user, user.getUserName(), user.getUserPassword(), user.getUserRole(), Constants.ENABLED);
    }

    public void addNewUser(User user)
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);
    }

    public List<User> getAllUsers()
            throws SQLException, SecurityException {
        PermissionSecurity.checkRequiredUserPermission(this.currentUser, UserRoleTypes.ADMIN);

        return null;
    }
}

