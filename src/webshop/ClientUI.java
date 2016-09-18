package webshop;

/**
 * Created by Amir i Masha on 2016.09.18..
 */
public interface ClientUI {
    public void userLogin(User user);

    public void userLogout(User user);

    public void addProductToBasket(Product product, UserBasket basket);

    public void showProductsInBasket(UserBasket basket);

    public void removeProductFromBasket(Product product, UserBasket basket);

    public void buyProductsInBasket(UserBasket basket);
}
