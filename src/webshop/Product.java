package webshop;

/**
 * Created by Amir on 06.09.2016..
 */
public class Product {
    private int productID;
    private String productName;
    private String productCategory;
    private float productPrice;

    public Product(int productID, String productName, String productCategory, float productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return this.productID;
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
    public String toString() {
        return ("Product name: " + this.productName +
                ". Product category: " + this.productCategory +
                ". Product price: " + this.productPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productID != product.productID) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        return productCategory != null ? productCategory.equals(product.productCategory) : product.productCategory == null;

    }

    @Override
    public int hashCode() {
        int result = productID;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productCategory != null ? productCategory.hashCode() : 0);
        return result;
    }
}
