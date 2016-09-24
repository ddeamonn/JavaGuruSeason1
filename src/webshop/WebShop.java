package webshop;

import java.sql.*;

/**
 * Created by Amir on 06.09.2016..
 */
public class WebShop {
    public static void main (String[] args) {
        try {
            Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:src/webshop/dbo/webshop.db");

            UserDao userDao = new SqliteUserDao(dbConnection);
            ProductDao productDao = new SqliteProductDao(dbConnection);
            SalesDao salesDao = new SqliteSalesDao(dbConnection);


            System.out.println("Opened database successfully");

            try {
/*
                userDao.addUser("Anonymous", "321123", UserRoleTypes.USER);
                userDao.addUser("Amir", "111", UserRoleTypes.ADMIN);
                userDao.addUser("Andrej", "222", UserRoleTypes.USER);
                userDao.addUser("Aleksej", "333", UserRoleTypes.USER);

                productDao.addProduct("Blender", "Kitchen Electronics", 35.35f);
                productDao.addProduct("Mixer", "Kitchen Electronics", 45.12f);
                productDao.addProduct("Samsung TV", "Electronics", 499.99f);
                productDao.addProduct("LG TV", "Electronics", 399.99f);

                User usr = userDao.getUser("Anonymous", "321123");
                User usrTenp = null;

                if (true) {
                    while (usr.getUserName().equals(Constants.USER_ANONYMOUS)) {
                        String[] creds = ConsoleUI.showLoginForm();
                        usrTenp = userDao.getUser(creds[0], creds[1]);
                        if (usrTenp == null) ConsoleIO.showMessage("Login failed. Please try again");
                        else usr = usrTenp;
                    }
                }
*/
                ConsoleUI consoleUI = new ConsoleUI();
                /*
                User usr = consoleUI.loginUser();

                ConsoleIO.showMessage("Welcome " + usr.getUserName());

                UserBasket basket = new UserBasket(usr);

                basket.addProduct(productDao.getProductByName("Mixer"));
                basket.addProduct(productDao.getProductByName("Blender"));
                basket.addProduct(productDao.getProductByName("Samsung TV"));
                basket.addProduct(productDao.getProductByName("Mixer"));
                basket.addProduct(productDao.getProductByName("Mixer"));
                basket.addProduct(productDao.getProductByName("Mixer"));
                basket.addProduct(productDao.getProductByName("Samsung TV"));
                basket.addProduct(productDao.getProductByName("Blender"));
                basket.addProduct(productDao.getProductByName("Blender"));

                //basket.removeProduct(productDao.getProductByName("Mixer"));

                //salesDao.buyProductsFromBasket(basket);
*/
                consoleUI.showMainMenu();
                //consoleUI.showAllUsers(userDao.getAllUsers());
                //consoleUI.buySaleForm(basket);

                //consoleUI.showProductsInBasket(basket);
                //consoleUI.selectProductFromCatalog(productDao.getProductMap());

            } catch (WebShopSqlException e) {
                System.err.println(e.getClass().getName() + "Webshop exception: " + e.getMessage());
            } catch (SecurityException e) {
                System.err.println(e.getClass().getName() + "Security violation: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
