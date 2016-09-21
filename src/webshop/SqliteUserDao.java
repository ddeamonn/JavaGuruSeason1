package webshop;

import java.sql.*;
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
    public void addUser(String userName, String userPassword, UserRoleType userType) throws SQLException {

        //if (checkUserExists(userName)) throw new WebShopSqlException("User with name " + userName + " already exists");

        PreparedStatement sqlStatement = null;
        String sql = "INSERT INTO USERS(Name, Password, Role) VALUES (?, ?, ?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);

        sqlStatement.setString(1, userName);
        sqlStatement.setString(2, userPassword);
        sqlStatement.setString(3, userType.toString());

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

            User usr = new User(queryUserId, queryUserName, queryUserPassword, UserRoleType.valueOf(queryUserRoleName));

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

            User usr = new User(queryUserId, queryUserName, queryUserPassword, UserRoleType.valueOf(queryUserRoleName));

            return usr;
        }

        return null;
    }

    @Override
    public void updateUser(User user, String newUserName, String newUserPassword,
                           UserRoleType newUserRole, boolean userEnabled) throws SQLException {

        PreparedStatement sqlStatement = null;
        String sql = "UPDATE USERS SET Name = ?, Password = ?, Role = ?, Enabled = ? WHERE (UserName=?) AND (UserRole=?)";
        sqlStatement = this.dbConnection.prepareStatement(sql);
        sqlStatement.setString(1, newUserName);
        sqlStatement.setString(2, newUserPassword);
        sqlStatement.setString(3, newUserRole.toString());
        sqlStatement.setBoolean(4, userEnabled);
        sqlStatement.setString(5, user.getUserName());
        sqlStatement.setString(6, user.getUserRole().toString());

        sqlStatement.executeUpdate();
    }

}
