package webshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir i Masha on 2016.09.20..
 */

public class SqliteUserDao implements UserDao {

    Connection dbConnection;

    SqliteUserDao(Connection dbConnection) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dbConnection = dbConnection;
    }

    @Override
    public void addUser(String userName, String userPassword, UserRoleTypes userType, UserStatus userStatus) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "INSERT INTO USERS(Name, Password, Role, Status) VALUES (?, ?, ?, ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        sqlStatement.setString(1, userName);
        sqlStatement.setString(2, userPassword);
        sqlStatement.setString(3, userType.toString());
        sqlStatement.setString(4, userStatus.toString());

        sqlStatement.executeUpdate();
    }

    @Override
    public boolean checkUserExists(String userName) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM USERS WHERE (Name = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, userName);

        ResultSet result = sqlStatement.executeQuery();
        if (result.next()) {
            return true;
        }
        return false;
    }

    @Override
    public Map<Integer, User> getAllUsers() throws SQLException {

        Map<Integer, User> userMap = new HashMap<>();

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM USERS";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        ResultSet result = sqlStatement.executeQuery();

        while (result.next()) {

            int queryUserId = result.getInt("Id");
            String queryUserName = result.getString("Name");
            String queryUserPassword = result.getString("Password");
            String queryUserRoleName = result.getString("Role");
            String queryUserStatus = result.getString("Status");

            User usr = new User(queryUserId, queryUserName, queryUserPassword, UserRoleTypes.valueOf(queryUserRoleName), UserStatus.valueOf(queryUserStatus));

            userMap.put(queryUserId, usr);
        }

        return userMap;
    }

    @Override
    public User getUser(String userName, String userPassword) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM USERS WHERE (Name = ?) AND (Password = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, userName);
        sqlStatement.setString(2, userPassword);

        ResultSet result = sqlStatement.executeQuery();

        while (result.next()) {

            int queryUserId = result.getInt("Id");
            String queryUserName = result.getString("Name");
            String queryUserPassword = result.getString("Password");
            String queryUserRoleName = result.getString("Role");
            String queryUserStatus = result.getString("Status");

            User usr = new User(queryUserId, queryUserName, queryUserPassword,
                    UserRoleTypes.valueOf(queryUserRoleName), UserStatus.valueOf(queryUserStatus));

            return usr;
        }

        return null;
    }

    @Override
    public User getUser(String userName) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM USERS WHERE (Name = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, userName);

        ResultSet result = sqlStatement.executeQuery();

        while (result.next()) {

            int queryUserId = result.getInt("Id");
            String queryUserName = result.getString("Name");
            String queryUserPassword = result.getString("Password");
            String queryUserRoleName = result.getString("Role");
            String queryUserStatus = result.getString("Status");

            User usr = new User(queryUserId, queryUserName, queryUserPassword,
                    UserRoleTypes.valueOf(queryUserRoleName), UserStatus.valueOf(queryUserStatus));

            return usr;
        }

        return null;
    }

    @Override
    public User getUser(int userId) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "SELECT * FROM USERS WHERE (Id = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setInt(1, userId);

        ResultSet result = sqlStatement.executeQuery();

        while (result.next()) {

            int queryUserId = result.getInt("Id");
            String queryUserName = result.getString("Name");
            String queryUserPassword = result.getString("Password");
            String queryUserRoleName = result.getString("Role");
            String queryUserStatus = result.getString("Status");

            User usr = new User(queryUserId, queryUserName, queryUserPassword,
                    UserRoleTypes.valueOf(queryUserRoleName), UserStatus.valueOf(queryUserStatus));

            return usr;
        }

        return null;
    }

    @Override
    public void updateUser(User user, String newUserName, String newUserPassword,
                           UserRoleTypes newUserRole, UserStatus newUserStatus) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "UPDATE USERS SET Name = ?, Password = ?, Role = ?, Status = ? WHERE (Name = ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, newUserName);
        sqlStatement.setString(2, newUserPassword);
        sqlStatement.setString(3, newUserRole.toString());
        sqlStatement.setString(4, newUserStatus.toString());
        sqlStatement.setString(5, user.getUserName());

        sqlStatement.executeUpdate();
    }

    public void disableUser(User user) throws SQLException {
        PreparedStatement sqlStatement = null;
        String sql = "UPDATE USERS SET ENABLED = FALSE WHERE ID = ?";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setInt(1, user.getUserID());

        sqlStatement.executeUpdate();

    }

}
