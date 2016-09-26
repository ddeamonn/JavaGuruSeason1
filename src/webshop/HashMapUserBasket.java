package webshop;
import java.util.*;

import java.util.LinkedList;

/**
 * Created by Amir on 13.09.2016..
 */

public class HashMapUserBasket {

    private HashMap<Product, Integer> productsInBasket;
    private User basketUser;

    HashMapUserBasket(User user) {
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
        if (productsInBasket.containsKey(product)) {
            if (productsInBasket.get(product) > 0)
                this.productsInBasket.put(product, productsInBasket.get(product) - 1);
        } else {
            this.productsInBasket.remove(product);
        }
    }

    public void cleanupBasket() {
        this.productsInBasket.clear();
    }

    public HashMap<Product, Integer> getProductsInBasket() {
        return productsInBasket;
    }

    public User getBasketUser() {
        return this.basketUser;
    }
}
