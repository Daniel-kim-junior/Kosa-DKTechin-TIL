package exam;

import java.text.DecimalFormat;

public class Product {
    private String name;
    private int balance;
    private int price;

    Product() {
        this("듀크인형", 5, 10000);
    }

    Product(String name, int balance, int price) {
        this.name = name;
        this.balance = balance;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public static String getCommaNum(int n) {
        return new DecimalFormat("#,##0").format(n);
    }

    @Override
    public String toString() {
        return "(" + getName() + " " + getBalance() + " " + getCommaNum(price) + "원)";
    }

    public int getBalance() {
        return balance;
    }

    public int getPrice() {
        return price;
    }
}
