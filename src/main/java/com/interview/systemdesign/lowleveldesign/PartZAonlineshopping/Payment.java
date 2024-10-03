package com.interview.systemdesign.lowleveldesign.PartZAonlineshopping;

public class Payment {
    private int paymentId;
    private Order order;
    private double amount;
    private String status;

    public Payment(int paymentId, Order order, double amount, String status) {
        this.paymentId = paymentId;
        this.order = order;
        this.amount = amount;
        this.status = status;
    }

    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public Order getOrder() { return order; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}

