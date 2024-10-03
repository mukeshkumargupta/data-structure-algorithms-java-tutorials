package com.interview.systemdesign.lowleveldesign.PartZAonlineshopping;

public class PaymentService {
    public Payment processPayment(Order order) {
        // Simulate payment processing
        Payment payment = new Payment(order.getOrderId(), order, order.getTotalPrice(), "Completed");
        // Here, you would integrate with a real payment gateway
        return payment;
    }
}

