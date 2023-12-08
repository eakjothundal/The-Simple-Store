package com.shoppingcart.data;

import com.shoppingcart.model.Product;

import java.util.Arrays;
import java.util.List;

public class ProductDataStore {
    public static List<Product> getDummyProducts() {
        return Arrays.asList(
                new Product("1", "Keyboard", 29.99),
                new Product("2", "Mouse", 19.99),
                new Product("3", "Monitor", 199.99)
                // Add more products as needed
        );
    }
}
