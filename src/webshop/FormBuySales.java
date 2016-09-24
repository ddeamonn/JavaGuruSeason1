package webshop;

import java.util.*;


/**
 * Created by Amir i Masha on 2016.09.24..
 */
public class FormBuySales {


    public boolean showBuySaleForm(UserBasket basket) {
        ConsoleIO.showMessage("======== Buy Products ========");
        this.productsInBasket(basket);
        ConsoleIO.showMessage("Do you want to buy this products?");
        return ConsoleIO.getYesNo();
    }

    public void productsInBasket(UserBasket basket) {

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
}
