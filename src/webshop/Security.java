package webshop;

/**
 * Created by Amir on 19.09.2016..
 */
public class Security {
    public static void checkRequredUserPermission(User user, userRoleType userRoleRequred) throws SecurityException{
        if (user.getUserRole() != userRoleRequred){
            throw new SecurityException("No required permissions for user: " + user.getUserName());
        }
    }
}
