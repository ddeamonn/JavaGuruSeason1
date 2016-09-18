package webshop;

/**
 * Created by Amir on 06.09.2016..
 */
public class WebShop {
    public static void main (String[] args) {
        try {
            DaoInterface db = new DaoInterfaceSqlite("jdbc:sqlite:src/webshop/dbo/webshop.db");

            System.out.println("Opened database successfully");

            try {
                db.addUser("Amir", "123", userRoleType.ADMIN);
                db.addProduct("Blender", "Home", 1.35f);
            } catch (WebShopSqlException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
