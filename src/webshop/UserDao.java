package webshop;

import webshop.User;
import webshop.userRoleType;

import java.sql.SQLException;

/**
 * Created by Amir i Masha on 2016.09.20..
 */
public interface UserDao {
    public void addUser(String userName, String userPassword, userRoleType userType) throws SQLException;

    public User getUser(String userName, String userPassword) throws SQLException;

    public boolean checkUserExists(String userName) throws SQLException;

    public void updateUser(User user, String newUserName, String newUserPassword,
                           userRoleType newUserRole, boolean userEnabled) throws SQLException;

}
