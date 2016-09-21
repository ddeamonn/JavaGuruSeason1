package webshop;
import java.util.*;

import java.util.LinkedList;

/**
 * Created by Amir on 13.09.2016..
 */

public class UserBasket {

    private HashMap<Product, Integer> productsInBasket;
    private User basketUser;

    UserBasket(User user) {
        this.productsInBasket = new HashMap<>();
        this.basketUser = user;
    }

    public void addProduct(Product product) {
        if(productsInBasket.containsKey(product)) {
            this.productsInBasket.put(product, productsInBasket.get(product) + 1);
        } else {
            this.productsInBasket.put(product, 1);
        }
    }

    public void removeProduct(Product product) {
        this.productsInBasket.remove(product);
    }

    public HashMap<Product, Integer> getProductsInBasket() {
        return productsInBasket;
    }

    public User getBasketUser() {
        return this.basketUser;
    }
}
