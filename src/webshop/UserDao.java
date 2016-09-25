package webshop;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Amir i Masha on 2016.09.20..
 */
public interface UserDao {
    public void addUser(String userName, String userPassword, UserRoleTypes userType) throws SQLException;

    public User getUser(String userName, String userPassword) throws SQLException;

    public User getUser(String userName) throws SQLException;

    public User getUser(int userId) throws SQLException;

    public Map<Integer, User> getAllUsers() throws SQLException;

    public void disableUser(User user) throws SQLException;

    public boolean checkUserExists(String userName) throws SQLException;

    public void updateUser(User user, String newUserName, String newUserPassword,
                           UserRoleTypes newUserRole, boolean userEnabled) throws SQLException;

}
