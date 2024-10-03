package com.interview.systemdesign.lowleveldesign.PartZAonlineshopping;

import java.util.HashMap;
import java.util.Map;

public class ProductService {
    private Map<Integer, Product> products = new HashMap<>();
    private int productIdCounter = 1;

    public void addProduct(Product product) {
        product.setId(productIdCounter++);
        products.put(product.getId(), product);
    }

    public void updateProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void removeProduct(int productId) {
        products.remove(productId);
    }

    public Map<Integer, Product> listProducts() {
        return products;
    }
}

