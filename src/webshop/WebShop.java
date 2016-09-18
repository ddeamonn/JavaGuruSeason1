package webshop;

/**
 * Created by Amir on 06.09.2016..
 */
public class WebShop {
    public static void main (String[] args) {
        try {
            DaoSqlite db = new DaoSqlite();

            System.out.println("Opened database successfully");
            int res = db.addUser("Amir", "123", userRoleType.ADMIN);

            if (res == Constants.OP_SUCCESS) System.out.println("Inserted user");
            else System.out.println("Not inserted");

            res = db.addProduct("Blender", "Home", 1.35f);
            if (res == Constants.OP_SUCCESS) System.out.println("Inserted product");
            else System.out.println("Not inserted");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
