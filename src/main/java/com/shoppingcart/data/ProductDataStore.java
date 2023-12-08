package com.shoppingcart.data;

import com.shoppingcart.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDataStore {
    private static final List<Product> products = new ArrayList<>();

    static {
        // Dummy list of products
        products.add(new Product("P001", "Keyboard", 49.99));
        products.add(new Product("P002", "Mouse", 19.99));
        products.add(new Product("P003", "Monitor", 159.99));
        products.add(new Product("P004", "CPU", 199.99));
        products.add(new Product("P005", "RAM", 99.99));
        products.add(new Product("P006", "Hard Drive", 79.99));
        products.add(new Product("P007", "Motherboard", 129.99));
        products.add(new Product("P008", "GPU", 299.99));
        products.add(new Product("P009", "Power Supply", 49.99));
        products.add(new Product("P010", "Case", 59.99));
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static Product getProduct(String productId) {
        return products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }
}
