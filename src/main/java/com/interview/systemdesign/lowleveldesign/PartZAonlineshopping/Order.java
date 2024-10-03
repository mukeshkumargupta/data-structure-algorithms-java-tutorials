package com.interview.systemdesign.lowleveldesign.PartZAonlineshopping;

import java.util.List;

public class Order {
    private int orderId;
    private User user;
    private List<Product> products;
    private String status;
    private double totalPrice;

    public Order(int orderId, User user, List<Product> products, String status, double totalPrice) {
        this.orderId = orderId;
        this.user = user;
        this.products = products;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public User getUser() { return user; }
    public List<Product> getProducts() { return products; }
    public String getStatus() { return status; }
    public double getTotalPrice() { return totalPrice; }

    public void setStatus(String status) { this.status = status; }
}
