package webshop;

/**
 * Created by Amir on 06.09.2016..
 */
public class User {
    private int userID;
    private String userName;
    private String userPassword;
    private userRoleType userRole;


    User (int userID, String userName, String userPassword, userRoleType userRole) {
        this.userID = userID;
        this.userName = userName;
        this.userRole = userRole;
        this.userPassword = userPassword;
    }

    public String getUserName (){
        return this.userName;
    }

    public String getUserPassword (){
        return this.userPassword;
    }

    public userRoleType getUserRole(){
        return this.userRole;
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
