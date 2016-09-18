package webshop;
import java.util.*;

import java.util.LinkedList;

/**
 * Created by Amir on 13.09.2016..
 */

public class UserBasket {

    private List<Product> productsInBasket;
    private User basketUser;

    UserBasket(User user) {
        this.productsInBasket = new LinkedList<>();
        this.basketUser = user;
    }

    public void addProduct(Product product) {
        this.productsInBasket.add(product);
    }

    public void removeProduct(Product product) {
        this.productsInBasket.remove(product);
    }

    public List getProductsInBasket() {
        return productsInBasket;
    }

    public List getProducts(){
        return this.productsInBasket;
    }

    public User getBasketUser() {
        return this.basketUser;
    }
}
