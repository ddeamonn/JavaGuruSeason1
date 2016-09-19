package webshop;

/**
 * Created by Amir on 06.09.2016..
 */
public class WebShop {
    public static void main (String[] args) {
        try {
            Dao db = new DaoSqlite("jdbc:sqlite:src/webshop/dbo/webshop.db");

            System.out.println("Opened database successfully");

            try {
                //db.addUser("Amir", "123", userRoleType.ADMIN);
                //db.addUser("Anonymous", "321123", userRoleType.USER);
                //db.addProduct("Blender", "Home", 1.35f);
                User usr = null;

                while(usr == null) {
                    String[] creds = ConsoleIO.showLoginForm();
                    usr = db.getUser(creds[0], creds[1]);
                    if (usr == null) ConsoleIO.showMessage("Login failed. Please try again");
                }
                ConsoleIO.showMessage("Welcome " + usr.getUserName());
                ConsoleIO.showUserMenu(usr);

            } catch (WebShopSqlException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
