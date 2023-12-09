package com.shoppingcart.beans;

import com.shoppingcart.model.Product;
import com.shoppingcart.data.ProductDataStore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CartBean implements Serializable {
    private Map<String, Integer> items = new HashMap<>();

    public CartBean() {
        // No-arg constructor
    }

    public void addItem(String productId, int quantity) {
        items.put(productId, quantity);
    }

    public void removeItem(String productId) {
        items.remove(productId);
    }

    public void clearCart() {
        items.clear();
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            Product product = ProductDataStore.getProduct(entry.getKey());
            double price = (product != null) ? product.getPrice() : 0.0;
            subtotal += (price * entry.getValue());
        }
        return subtotal;
    }

    public Map<String, Integer> getItems() {
        return this.items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }
}