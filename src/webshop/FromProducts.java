package webshop;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir i Masha on 2016.09.24..
 */

public class FromProducts {

    private ProductsCommands productsCommands;
    private User currentUser;

    public FromProducts(User currentUser, ProductsCommands productsCommands) {
        this.productsCommands = productsCommands;
        this.currentUser = currentUser;
    }

    public void showFormProducts() throws SQLException {
        boolean toContinue = true;
        while (toContinue) {

            Map<Integer, String> methods = ProductsCommands.allowedMethods(this.currentUser);

            ConsoleIO.showMessage("======== Products Menu ========");

            for (Map.Entry<Integer, String> method : methods.entrySet()) {
                ConsoleIO.showMessage(method.getKey() + " - " + method.getValue());
            }

            ConsoleIO.showMessage(Constants.EXIT + " - Return to main menu ");
            ConsoleIO.showMessage("Please select: ");

            int usersSelected = ConsoleIO.getUserInputInt();
            switch (usersSelected) {
                case Constants.EXIT:
                    toContinue = false;
                    break;
                case Constants.CATALOG_SHOW_PRODUCTS:
                    this.showAllProducts();
                    break;
                case Constants.CATALOG_ADD_PRODUCT:
                    this.showAddProductForm();
                    break;
                case Constants.CATALOG_ENABLE_PRODUCT:
                    this.showEnableProductForm();
                    break;
                case Constants.CATALOG_DISABLE_PRODUCT:
                    this.showDisableProduct();
                    break;
                case Constants.CATALOG_CHANGE_PRODUCT_PRICE:
                    this.showSetProductPrice();
                    break;
            }
        }
    }

    private void printProductToConsole(Product product) {
        ConsoleIO.showMessage("Product ID: " + product.getProductID() +
                ". Product: " + product.getProductID() +
                ". Category: " + product.getProductCategory() +
                ". Price: " + product.getProductPrice() +
                ". Status: " + product.getProductStatus()
        );
    }

    private void showAllProducts() throws SQLException {
        try {

            Map<Integer, Product> products = this.productsCommands.getProductMapFromCatalog();

            ConsoleIO.showMessage("======== Products in Webshop ========");
            for (Map.Entry<Integer, Product> product : products.entrySet()) {
                printProductToConsole(product.getValue());
            }
        } catch (NullPointerException e) {
            ConsoleIO.showMessage("No product found in product catalog. ");
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to list products. Reason: " + e.getMessage());
        }
    }

    private void showAddProductForm() throws SQLException {
        try {

            Map<String, String> productInfo = this.addProductForm();
            boolean productExists =
                    this.productsCommands.checkProductExists(productInfo.get(Constants.PRODUCT_NAME),
                            productInfo.get(Constants.PRODUCT_CATEGORY));
            if (!productExists) {
                this.productsCommands.addProductToCatalog(productInfo.get(
                        Constants.PRODUCT_NAME),
                        productInfo.get(Constants.PRODUCT_CATEGORY),
                        Float.parseFloat(productInfo.get(Constants.PRODUCT_PRICE))
                );
            } else {
                ConsoleIO.showMessage("Failed to add product. Product already exists in catalog.");
            }

        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to add product. Reason: " + e.getMessage());
        }
    }

    private Map<String, String> addProductForm() {

        Map<String, String> productInfo = new HashMap<>();
        ConsoleIO.showMessage("======== Add Product  ========");
        ConsoleIO.showMessage("Please enter new product name: ");
        productInfo.put(Constants.PRODUCT_NAME, ConsoleIO.getUserInputString());
        ConsoleIO.showMessage("Please enter product category: ");
        productInfo.put(Constants.PRODUCT_CATEGORY, ConsoleIO.getUserInputString());
        ConsoleIO.showMessage("Please enter product price: ");
        productInfo.put(Constants.PRODUCT_PRICE, String.valueOf(ConsoleIO.getUserInputFloat()));

        return productInfo;
    }


    private void showEnableProductForm() {
        try {
            ConsoleIO.showMessage("======== Enable product in Webshop ========");
            this.showFormProducts();
            ConsoleIO.showMessage("Please inter product ID: ");
            int productID = ConsoleIO.getUserInputInt();
            Product productToEnable = this.productsCommands.getProductById(productID);
            if (productToEnable != null) {
                this.productsCommands.enableProductInCatalog(productToEnable);
                ConsoleIO.showMessage("Product " + productToEnable.getProductName() + " is successfully enabled");
            } else {
                ConsoleIO.showMessage("No product with such ID");
            }


        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to disable product. Reason: " + e.getMessage());
        }
    }

    private void showDisableProduct() {
        try {
            ConsoleIO.showMessage("======== Disable product in Webshop ========");
            this.showFormProducts();
            ConsoleIO.showMessage("Please inter product ID: ");
            int productID = ConsoleIO.getUserInputInt();
            Product productToDisable = this.productsCommands.getProductById(productID);
            if (productToDisable != null) {
                this.productsCommands.disableProductInCatalog(productToDisable);
                ConsoleIO.showMessage("Product " + productToDisable.getProductName() + " is successfully disabled");
            } else {
                ConsoleIO.showMessage("No product with such ID");
            }


        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to disable product. Reason: " + e.getMessage());
        }
    }

    private void showSetProductPrice() {
        ConsoleIO.showMessage("Not implemented");
    }


}
