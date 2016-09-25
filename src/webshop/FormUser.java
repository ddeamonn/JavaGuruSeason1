package webshop;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Amir i Masha on 2016.09.24..
 */
public class FormUser {

    private UserCommands userCommands;
    private User currentUser;

    public FormUser(User currentUser, UserCommands userCommands) {
        this.userCommands = userCommands;
        this.currentUser = currentUser;
    }

    public void showFormUser() throws SQLException {
        boolean toContinue = true;
        while (toContinue) {

            Map<Integer, String> methods = UserCommands.allowedMethods(this.currentUser);

            ConsoleIO.showMessage("======== User Menu ========");

            for (Map.Entry<Integer, String> method : methods.entrySet()) {
                ConsoleIO.showMessage(method.getKey() + " - " + method.getValue());
            }

            ConsoleIO.showMessage(Constants.EXIT + " - Return to main menu ");
            ConsoleIO.showMessage("Please select: ");

            int usersSelected = ConsoleIO.getUserInputInt();
            switch (usersSelected) {
                case Constants.EXIT:
                    toContinue = false;
                    break;
                case Constants.LOGIN_USER:
                    this.currentUser = this.showLoginForm();
                    this.userCommands.setCurrentUser(this.currentUser);
                    break;
                case Constants.LOGOUT_USER:
                    User tmpUser = userCommands.getDefaultUser();
                    if (tmpUser != null) {
                        this.currentUser = tmpUser;
                    } else {
                        this.currentUser = userCommands.getDefaultUser();
                    }
                    break;
                case Constants.USER_ADD_USER:
                    this.showAddUserForm();
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

            Map<Integer, User> users = this.userCommands.getAllUsers();

            ConsoleIO.showMessage("======== Users in Webshop ========");
            for (Map.Entry<Integer, User> user : users.entrySet()) {
                ConsoleIO.showMessage("User ID: " + user.getValue().getUserID() +
                        ". Username: " + user.getValue().getUserName() +
                        ". Password: " + user.getValue().getUserPassword() +
                        ". User role: " + user.getValue().getUserRole()
                );
            }
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to list users. Reason: " + e.getMessage());
        }
    }

    private User showLoginForm() {
        User usr = null;
        try {
            String[] creds = this.loginForm();
            User usrTemp = this.userCommands.userLogin(creds[0], creds[1]);
            if (usrTemp == null) {
                ConsoleIO.showMessage("Login failed. Please try again");
                usr = this.currentUser;
            } else {
                usr = usrTemp;
                ConsoleIO.showMessage("Login succeded. Welcome " + usr.getUserName());
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

            boolean userExists = this.userCommands.checkUserExists(userInfo.get(Constants.USER_LOGIN));

            if (!userExists) {
                String userRoleString = userInfo.get(Constants.USER_ROLE).toUpperCase();
                this.userCommands.addNewUser(userInfo.get(Constants.USER_LOGIN),
                        userInfo.get(Constants.USER_PASSWORD),
                        UserRoleTypes.valueOf(userRoleString));
                ConsoleIO.showMessage("User successfully added");
            } else {
                ConsoleIO.showMessage("Can't add new user. User already exists.");
            }
        } catch (IllegalArgumentException e) {
            ConsoleIO.showMessage("Failed to add new user. Role type is incorrect.");
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to add new user. Reason: " + e.getMessage());
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

    private void showDiableUserForm() {
        try {

            Map<Integer, User> users = this.userCommands.getAllUsers();

            ConsoleIO.showMessage("======== Disable User ========");
            for (Map.Entry<Integer, User> user : users.entrySet()) {
                ConsoleIO.showMessage("User ID: " + user.getValue().getUserID() +
                        ". Username: " + user.getValue().getUserName() +
                        ". User role: " + user.getValue().getUserRole()
                );
            }

            ConsoleIO.showMessage("Please inter user ID: ");
            int userId = ConsoleIO.getUserInputInt();
            User userToDisable = this.userCommands.getUser(userId);
            if (userToDisable != null) {
                this.userCommands.disableUser(userToDisable);
            } else {
                ConsoleIO.showMessage("No user with suck ID");
            }


        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to disable user. Reason: " + e.getMessage());
        }
    }


    public User getCurrentUser() {
        return this.currentUser;
    }
}
