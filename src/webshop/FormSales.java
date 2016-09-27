package webshop;

import java.sql.SQLException;
import java.util.*;


/**
 * Created by Amir i Masha on 2016.09.24..
 */
public class FormSales {

    private SalesCommands salesCommands;
    private ProductsCommands productsCommands;
    private User currentUser;

    public FormSales(User user, SalesCommands salesCommands, ProductsCommands productsCommands) {
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
                    if (!this.salesCommands.getProductsFromBasket().isEmpty())
                        this.showBuyForm();
                    else ConsoleIO.showMessage("No products in basket");
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
            this.salesCommands.buyProductsInBasket();
            ConsoleIO.showMessage("Products are bought");
        } else {
            ConsoleIO.showMessage("Products not bought");
        }
    }

    private void showProductsInBasket() throws SQLException {
        ConsoleIO.showMessage("==== Products in user basket ====");

        Map<Product, Integer> products = this.salesCommands.getProductsFromBasket();

        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            ConsoleIO.showMessage("ID: " + product.getKey().getProductID() + ". Product: " + product.getKey().getProductName() +
                    ". Quantity: " + product.getValue() +
                    ". Price per quantity: " + (product.getKey().getProductPrice() * product.getValue()));
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
            if (product != null) {
                if (product.getProductStatus() == ProductStatus.ENABLED) {
                    this.salesCommands.addProductToBasket(product);
                    ConsoleIO.showMessage("Product added to the basket");
                } else ConsoleIO.showMessage("Failed to select product with this id. Product is diasbled");
            } else ConsoleIO.showMessage("Failed to select product with this id");
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to add product to basket: Reason" + e.getMessage());
        }
    }

    private void showRemoveProductFromBasket() throws SQLException {

        try {
            ConsoleIO.showMessage("==== Remove product from basket ====");
            FormProducts formProducts = new FormProducts(this.currentUser, this.productsCommands);
            this.showProductsInBasket();
            ConsoleIO.showMessage("Please enter product id to remove from basket:");
            int userSelected = ConsoleIO.getUserInputInt();
            Product product = this.productsCommands.getProductById(userSelected);
            if (product != null) {
                this.salesCommands.removeProductFromBasket(product);
                ConsoleIO.showMessage("Product removed from basket");
            } else ConsoleIO.showMessage("Failed to select product with this id");
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to remove product to basket: Reason" + e.getMessage());
        }

    }
}
