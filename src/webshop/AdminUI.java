package webshop;

/**
 * Created by Amir i Masha on 2016.09.18..
 */
public interface AdminUI {
    public void addProductToCatalog(Product product);

    public void removeProductFromCatalog(Product product);

    public void setProductAvailability(Product product);

    public void changeProductPrice(Product product);

    public void disbaleUser(User user);

    public void enableUser(User user);
}
