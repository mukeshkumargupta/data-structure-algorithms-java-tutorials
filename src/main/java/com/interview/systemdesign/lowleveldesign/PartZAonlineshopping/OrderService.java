package com.interview.systemdesign.lowleveldesign.PartZAonlineshopping;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private int orderIdCounter = 1;

    public Order createOrder(User user, List<Product> products, double totalPrice) {
        Order order = new Order(orderIdCounter++, user, products, "Pending", totalPrice);
        orders.add(order);
        return order;
    }

    public Order getOrderDetails(int orderId) {
        return orders.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);
    }

    public void updateOrderStatus(int orderId, String status) {
        Order order = getOrderDetails(orderId);
        if (order != null) {
            order.setStatus(status);
        }
    }
}

