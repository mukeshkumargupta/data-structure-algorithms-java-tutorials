package com.interview.systemdesign.lowleveldesign.PartZAonlineshopping;

import java.util.HashMap;
import java.util.Map;

public class CartService {
    private Map<Integer, Cart> userCarts = new HashMap<>();

    public void addToCart(int userId, Product product, int quantity) {
        userCarts.putIfAbsent(userId, new Cart());
        userCarts.get(userId).addToCart(product, quantity);
    }

    public Cart viewCart(int userId) {
        return userCarts.get(userId);
    }

    public void removeFromCart(int userId, Product product) {
        if (userCarts.containsKey(userId)) {
            userCarts.get(userId).removeFromCart(product);
        }
    }
}
