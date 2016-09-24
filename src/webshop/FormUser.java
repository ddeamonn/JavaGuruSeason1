package webshop;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Amir i Masha on 2016.09.24..
 */
public class FormUser {

    private UserCommands userCommands;
    private User user;

    public FormUser(User user, UserCommands userCommands) {
        this.userCommands = userCommands;
        this.user = user;
    }

    public void showFormUser() throws SQLException {
        boolean toContinue = true;
        while (toContinue) {

            Map<Integer, String> methods = UserCommands.allowedMethods(this.user);

            ConsoleIO.showMessage("======== User Menu ========");

            for (Map.Entry<Integer, String> method : methods.entrySet()) {
                ConsoleIO.showMessage(method.getKey() + " - " + method.getValue());
            }

            ConsoleIO.showMessage(Constants.EXIT + " - Exit");
            ConsoleIO.showMessage("Please select: ");

            int usersSelected = ConsoleIO.getUserInputInt();
            switch (usersSelected) {
                case Constants.EXIT:
                    toContinue = false;
                    break;
                case Constants.LOGIN_USER:
                    this.user = this.showLoginForm();
                    break;
                case Constants.LOGOUT_USER:
                    User tmpUser = userCommands.getDefaultUser();
                    if (tmpUser != null) {
                        this.user = tmpUser;
                    } else {
                        this.user = userCommands.getDefaultUser();
                    }
                    break;
                case Constants.USER_ADD_USER:
                    toContinue = false;
                    break;
                case Constants.USER_DISABLE_USER:
                    toContinue = false;
                    break;
                case Constants.USER_LIST_USERS:
                    this.showAllUsers();
                    break;
            }
        }
    }

    private void showAllUsers() throws SQLException {
        try {

            List<User> users = this.userCommands.getAllUsers();

            ConsoleIO.showMessage("======== Users in Webshop ========");
            for (User user : users) {
                ConsoleIO.showMessage("User ID: " + user.getUserID() +
                        ". Username: " + user.getUserName() +
                        ". Password: " + user.getUserPassword() +
                        ". User role: " + user.getUserRole()
                );
            }
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to list users. Reason: " + e.getMessage());
        }
    }

    private User showLoginForm() {
        User usr = null;
        try {
            while (usr == null) {
                String[] creds = this.loginForm();
                User usrTemp = this.userCommands.userLogin(creds[0], creds[1]);
                if (usrTemp == null) ConsoleIO.showMessage("Login failed. Please try again");
                else usr = usrTemp;
            }
            return usr;
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to login. Reason: " + e.getMessage());
        }

        return null;
    }

    private String[] loginForm() {

        String[] loginForm = new String[2];
        ConsoleIO.showMessage("======== LOGIN FORM ========");
        ConsoleIO.showMessage("Please enter your login: ");
        loginForm[0] = ConsoleIO.getUserInputString();
        ConsoleIO.showMessage("Please enter your password: ");
        loginForm[1] = ConsoleIO.getUserInputString();

        return loginForm;
    }

    private void showAddUserForm() {
        try {
            Map<String, String> userInfo = this.addUserForm();

            User usr = this.userCommands.getUser(userInfo.get(Constants.USER_LOGIN),
                    userInfo.get(Constants.USER_PASSWORD));

            if (usr != null) {
                this.userCommands.addNewUser(userInfo.get(Constants.USER_LOGIN),
                        userInfo.get(Constants.USER_PASSWORD),
                        userInfo.get(Constants.USER_ROLE));
            }
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to add user new user. Reason: " + e.getMessage());
        }
    }

    private Map<String, String> addUserForm() {

        Map<String, String> loginInfo = new HashMap<>();

        ConsoleIO.showMessage("======== Add User  ========");
        ConsoleIO.showMessage("Please enter new user login: ");
        loginInfo.put(Constants.USER_LOGIN, ConsoleIO.getUserInputString());
        ConsoleIO.showMessage("Please enter new user password: ");
        loginInfo.put(Constants.USER_PASSWORD, ConsoleIO.getUserInputString());
        ConsoleIO.showMessage("Please enter new role: ADMIN or USER");
        loginInfo.put(Constants.USER_ROLE, ConsoleIO.getUserInputString());

        return loginInfo;
    }
}
