package webshop;

import java.sql.*;

/**
 * Created by Amir on 06.09.2016..
 */
public class WebShop {
    public static void main(String[] args) {
        try {
            ConsoleUI consoleUI = new ConsoleUI();
            consoleUI.showMainMenu();
        } catch (WebShopSqlException e) {
            System.err.println(e.getClass().getName() + "Webshop exception: " + e.getMessage());
        } catch (SecurityException e) {
            System.err.println(e.getClass().getName() + "Security violation: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
