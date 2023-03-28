package Collection;

import java.text.DecimalFormat;
import java.util.Objects;

public class Product2 implements Comparable<Product2> {
    private String productID;
    private String productName;
    private String productPrice;

    public Product2(String productID, String productName, String productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }




    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o instanceof Product2) {
            Product2 p = (Product2) o;
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
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return productID + "\t" + productName + "\t\t" + decimalFormat.format(Integer.parseInt(productPrice));
    }


    @Override
    public int compareTo(Product2 o) {

        return Integer.parseInt(o.productPrice) - Integer.parseInt(this.productPrice);
    }
}
