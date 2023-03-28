package Collection;

import java.util.Objects;

public class Product {
    private String productID;
    private String productName;
    private String productPrice;

    public Product(String productID, String productName, String productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }




    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o instanceof Product) {
            Product p = (Product) o;
            if(p.productID == this.productID) return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }

    @Override
    public String toString() {
        return productID + "\t" + productName + "\t" + productPrice;
    }




}
