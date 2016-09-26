package webshop;

import java.sql.SQLException;
import java.util.*;


/**
 * Created by Amir i Masha on 2016.09.24..
 */
public class FormBuySales {

    private SalesCommands salesCommands;
    private ProductsCommands productsCommands;
    private User currentUser;

    public FormBuySales(User user, SalesCommands salesCommands, ProductsCommands productsCommands) {
        this.currentUser = user;
        this.salesCommands = salesCommands;
        this.productsCommands = productsCommands;
    }

    public void showFormBuySales() throws SQLException {
        boolean toContinue = true;
        while (toContinue) {

            ConsoleIO.showMessage("======== Sales Menu ========");

            Map<Integer, String> methods = this.salesCommands.allowedMethods(this.currentUser);
            for (Map.Entry<Integer, String> method : methods.entrySet()) {
                ConsoleIO.showMessage(method.getKey() + " - " + method.getValue());
            }

            ConsoleIO.showMessage(Constants.EXIT + " - Return to main menu ");
            ConsoleIO.showMessage("Please select: ");

            int usersSelected = ConsoleIO.getUserInputInt();
            switch (usersSelected) {
                case Constants.BASKET_ADD_PRODUCT:
                    this.showAddProductToBasket();
                    break;
                case Constants.BASKET_REMOVE_PRODUCT:
                    this.showRemoveProductFromBasket();
                    break;
                case Constants.BASKET_REMOVE_ALL_PRODUCTS:
                    this.showRemoveProductFromBasket();
                    break;
                case Constants.BASKET_SHOW_PRODUCTS:
                    this.showProductsInBasket();
                    break;
                case Constants.BASKET_BUY_PRODUCTS:
                    this.showBuyForm();
                    break;
                case Constants.EXIT:
                    toContinue = false;
                    break;
            }
        }
    }


    private void showBuyForm() throws SQLException {
        ConsoleIO.showMessage("======== Buy Products ========");
        this.showProductsInBasket();
        ConsoleIO.showMessage("Do you want to buy this products?");
        if (ConsoleIO.getYesNo() == true) {
            ConsoleIO.showMessage("Products are bought.");
        } else {
            ConsoleIO.showMessage("Canceled");
        }
    }

    private void showProductsInBasket() throws SQLException {
        ConsoleIO.showMessage("==== Products in user basket ====");

        Map<Product, Integer> products = this.salesCommands.getProductsFromBasket();

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

    private void showAddProductToBasket() throws SQLException {
        try {
            ConsoleIO.showMessage("==== Add product to basket ====");
            FormProducts formProducts = new FormProducts(this.currentUser, this.productsCommands);
            formProducts.showAllProducts();
            ConsoleIO.showMessage("Please enter product id to add to basket:");
            int userSelected = ConsoleIO.getUserInputInt();
            Product product = this.productsCommands.getProductById(userSelected);
            this.salesCommands.addProductToBasket(product);
            ConsoleIO.showMessage("Product added to the basket");
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to add product to basket: Reason" + e.getMessage());
        }
    }

    private void showRemoveProductFromBasket() throws SQLException {

        ConsoleIO.showMessage("==== Products in user basket ====");

        Map<Product, Integer> products = this.salesCommands.getProductsFromBasket();

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


}
