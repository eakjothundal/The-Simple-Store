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
        // Add more products if needed
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
