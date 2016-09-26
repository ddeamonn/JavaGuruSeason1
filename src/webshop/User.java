package webshop;

/**
 * Created by Amir on 06.09.2016..
 */
public class User {
    private int userID;
    private String userName;
    private String userPassword;
    private UserRoleTypes userRole;
    private UserStatus userStatus;


    User(int userID, String userName, String userPassword, UserRoleTypes userRole, UserStatus userStatus) {
        this.userID = userID;
        this.userName = userName;
        this.userRole = userRole;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
    }

    public int getUserID() {
        return this.userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserPassword (){
        return this.userPassword;
    }

    public UserRoleTypes getUserRole() {
        return this.userRole;
    }

    public UserStatus getUserStatus() {
        return this.userStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        if (!userPassword.equals(user.userPassword)) return false;
        return userRole == user.userRole;

    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + userPassword.hashCode();
        result = 31 * result + userRole.hashCode();
        return result;
    }
}
