package webshop;

/**
 * Created by Amir on 06.09.2016..
 */
public class Product {
    private String productName;
    private String productCategory;
    private float productPrice;

    public Product(String productName, String productCategory, float productPrice) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public float getProductPrice() {
        return this.productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!productName.equals(product.productName)) return false;
        return productCategory.equals(product.productCategory);

    }

    @Override
    public int hashCode() {
        int result = productName.hashCode();
        result = 31 * result + productCategory.hashCode();
        return result;
    }
}
