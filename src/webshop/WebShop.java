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

            UserDao userDao = new SqliteUserDao(dbConnection);
            ProductDao productDao = new SqliteProductDao(dbConnection);
            SalesDao salesDao = new SqliteSalesDao(dbConnection);


            System.out.println("Opened database successfully");

            try {
/*
                userDao.addUser("Anonymous", "321123", UserRoleType.USER);
                userDao.addUser("Amir", "111", UserRoleType.ADMIN);
                userDao.addUser("Andrej", "222", UserRoleType.USER);
                userDao.addUser("Aleksej", "333", UserRoleType.USER);

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
                User usr = ConsoleUI.loginUser(userDao);

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

                //ConsoleUI.showUserMenu(usr);
                //ConsoleUI.showAllUsers(userDao.getAllUsers());
                //ConsoleUI.buySaleForm(basket);

                //ConsoleUI.showProductsInBasket(basket);
                //ConsoleUI.selectProductFromCatalog(productDao.getProductMap());

            } catch (WebShopSqlException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
