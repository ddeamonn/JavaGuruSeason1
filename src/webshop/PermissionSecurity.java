package webshop;

/**
 * Created by Amir on 19.09.2016..
 */
public class PermissionSecurity {
    public static void checkRequiredUserPermission(User user, UserRoleTypes userRoleRequired) throws SecurityException {
        if (user.getUserRole() != userRoleRequired){
            throw new SecurityException("No required permissions for user: " + user.getUserName());
        }
    }
}
