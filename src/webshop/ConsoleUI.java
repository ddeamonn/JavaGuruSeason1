package webshop;

import java.util.*;

/**
 * Created by Amir i Masha on 2016.09.21..
 */
public class ConsoleUI {

    public static void showUserMenu(User user) {
        Map<Integer, String> methods = UserUIMethods.allowedMethods(user);
        int i = 1;

        ConsoleIO.showMessage("======== User Menu ========");

        for (Map.Entry<Integer, String> method : methods.entrySet()) {
            ConsoleIO.showMessage(i + " - " + method.getValue());
            i++;
        }

        ConsoleIO.showMessage(i + " - Exit");

    }

    public static String[] showLoginForm() {

        String[] loginForm = new String[2];
        ConsoleIO.showMessage("======== LOGIN FORM ========");
        ConsoleIO.showMessage("Please enter your login: ");
        loginForm[0] = ConsoleIO.getUserInputString();
        ConsoleIO.showMessage("Please enter your password: ");
        loginForm[1] = ConsoleIO.getUserInputString();

        return loginForm;
    }

    public static int selectProductFromCatalog(Map<Integer, Product> products) {

        ConsoleIO.showMessage("=== Select product from catalog ===");
        for (Map.Entry<Integer, Product> product : products.entrySet()) {
            ConsoleIO.showMessage(product.getKey() + ". Product: " +
                    product.getValue().getProductName() +
                    ". Price: " + product.getValue().getProductPrice());
        }

        ConsoleIO.showMessage("Please enter product number: ");

        return ConsoleIO.getUserInputInt();
    }

    public static void showProductsInBasket(UserBasket basket) {

        ConsoleIO.showMessage("==== Products in user basket ====");
        HashMap<Product, Integer> products = basket.getProductsInBasket();
        int i = 1;
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            ConsoleIO.showMessage(i + ". Product: " + product.getKey().getProductName() +
                    ". Quantity: " + product.getValue() +
                    ". Price per quantity: " + (product.getKey().getProductPrice() * product.getValue()));
            i++;
        }

        ConsoleIO.showMessage("Total sum of products in basket: " + products.entrySet().stream().
                map(map -> {
                    return map.getKey().getProductPrice() * map.getValue();
                }).
                mapToDouble(Float::doubleValue).sum()
        );
    }

    public static int showAllUsers(Map<Integer, User> users) {

        ConsoleIO.showMessage("======== Users in Webshop ========");
        for (Map.Entry<Integer, User> user : users.entrySet()) {
            ConsoleIO.showMessage("User ID: " + user.getKey() +
                    ". Username: " + user.getValue().getUserName() +
                    ". Password: " + user.getValue().getUserPassword() +
                    ". User role: " + user.getValue().getUserRole()
            );
        }

        ConsoleIO.showMessage("Please enter product number: ");

        return ConsoleIO.getUserInputInt();
    }

    public static boolean buySaleForm(UserBasket basket) {
        ConsoleIO.showMessage("======== Buy Products ========");
        ConsoleUI.showProductsInBasket(basket);
        ConsoleIO.showMessage("Do you want to buy this products?");
        return ConsoleIO.getYesNo();
    }

}
