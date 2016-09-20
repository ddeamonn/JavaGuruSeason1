package webshop;

import java.sql.*;

/**
 * Created by Amir on 06.09.2016..
 */
public class WebShop {
    public static void main (String[] args) {
        try {
            Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:src/webshop/dbo/webshop.db");

            // Dao db = new DaoSqlite(dbConnection);

            UserDao userDb = new SqliteUserDao(dbConnection);
            ProductDao productDb = new SqliteProductDao(dbConnection);
            SalesDao saleDb = new SqliteSalesDao(dbConnection);


            System.out.println("Opened database successfully");

            try {
/*
                userDb.addUser("Anonymous", "321123", userRoleType.USER);
                userDb.addUser("Amir", "111", userRoleType.ADMIN);
                userDb.addUser("Andrej", "222", userRoleType.USER);
                userDb.addUser("Aleksej", "333", userRoleType.USER);

                productDb.addProduct("Blender", "Kitchen Electronics", 35.35f);
                productDb.addProduct("Mixer", "Kitchen Electronics", 45.12f);
                productDb.addProduct("Samsung TV", "Electronics", 499.99f);
                productDb.addProduct("LG TV", "Electronics", 399.99f);
*/
                User usr = userDb.getUser("Anonymous", "321123");
                User usrTenp = null;

                if (true) {
                    while (usr.getUserName().equals(Constants.USER_ANONYMOUS)) {
                        String[] creds = ConsoleIO.showLoginForm();
                        usrTenp = userDb.getUser(creds[0], creds[1]);
                        if (usrTenp == null) ConsoleIO.showMessage("Login failed. Please try again");
                        else usr = usrTenp;
                    }
                }

                ConsoleIO.showMessage("Welcome " + usr.getUserName());
/*
                UserBasket basket = new UserBasket(usr);

                basket.addProduct(productDb.getProductByName("Mixer"));
                basket.addProduct(productDb.getProductByName("Blender"));
                basket.addProduct(productDb.getProductByName("Samsung TV"));
                basket.addProduct(productDb.getProductByName("Mixer"));
                basket.addProduct(productDb.getProductByName("Mixer"));
                basket.addProduct(productDb.getProductByName("Mixer"));
                basket.addProduct(productDb.getProductByName("Samsung TV"));
                basket.addProduct(productDb.getProductByName("Blender"));
                basket.addProduct(productDb.getProductByName("Blender"));

                //basket.removeProduct(productDb.getProductByName("Mixer"));

                saleDb.buyProductsFromBasket(basket);
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

