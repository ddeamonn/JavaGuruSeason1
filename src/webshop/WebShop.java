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

/*
                db.addUser("Anonymous", "321123", userRoleType.USER);
                db.addUser("Amir", "111", userRoleType.ADMIN);
                db.addUser("Amir", "112", userRoleType.ADMIN);
                db.addUser("Andrej", "222", userRoleType.USER);
                db.addUser("Aleksej", "333", userRoleType.USER);

                db.addProduct("Blender", "Kitchen Electronics", 35.35f);
                db.addProduct("Mixer", "Kitchen Electronics", 45.12f);
                db.addProduct("Samsung TV", "Electronics", 499.99f);
                db.addProduct("LG TV", "Electronics", 399.99f);

                User usr = db.getUser("Anonymous", "321123");

                if (false) {
*/
                User usr = db.getUser("Anonymous", "321123");
                    while (usr.getUserName() == Constants.USER_ANONYMOUS) {
                        String[] creds = ConsoleIO.showLoginForm();
                        usr = db.getUser(creds[0], creds[1]);
                        if (usr == null) ConsoleIO.showMessage("Login failed. Please try again");

                    }
                //}


                ConsoleIO.showMessage("Welcome " + usr.getUserName());
/*
                UserBasket basket = new UserBasket(usr);

                basket.addProduct(db.getProductByName("Mixer"));
                basket.addProduct(db.getProductByName("Blender"));
                basket.addProduct(db.getProductByName("Samsung TV"));
                basket.addProduct(db.getProductByName("Mixer"));

                //basket.removeProduct(db.getProductByName("Mixer"));

                db.buyProductsFromBasket(basket);
*/
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

