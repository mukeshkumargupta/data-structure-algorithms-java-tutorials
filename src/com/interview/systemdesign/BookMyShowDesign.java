package com.interview.systemdesign;

import java.util.*;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference: https://www.youtube.com/watch?v=qGR7KoGpqrU&list=PLIA-9QRQ0RqHcTCuIusS1G1LikGoD3WxM&index=4&t=1s
 * Reference: https://www.geeksforgeeks.org/design-movie-ticket-booking-system-like-bookmyshow/?ref=rp
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Status: Not Done
 */

public class BookMyShowDesign {
    
    class Product {
        private String productId;
        private String name;
        private String description;
        private Double price;
        private ProductCategory category;
        private int availableItemCount;
        private Account seller;
    }
    
    class ProductCategory {
        private String name;
        private String description;
    }
    
    class Item {
        private String productId;
        private int quanity;
        private double price;
        public void updateQuantity(int quantity) {
        }
    }
    
    class Cart {
        private List<Item> items;
        private int quanity;
        private double price;
        public boolean addItem(Item item) {
            
            return true;
        }
        public boolean removeItem(Item item) { // It seems we need to map with item id which are internally should map with itemId
            return true;       
        }
        
        public boolean updateItemQuanity(Item item, int quanity) { 
            return true;
        }
        
        public List<Item> getItems() { 
            return null;
        }
        
        public boolean checkout() { 
            return true;
        }
    }
    
    class Account {
        private String userId;
        private String userName;
        private String password;
        private Account status;
        private Address shippingAddress;
        private String email;
        private String phone;
        List<CreditCard> creditCards;
        
        public boolean addProduct(Product product) {
            return true;
        }
        
        public boolean addProductReview(ProductReview productReview) {
            return true;
        }
    }
    
    class Customer {
        private Account account;
        private String userName;
        private Cart cart;
        private Order order;
        
        public boolean addItemToCart(Item item) {
            return true;
        }
        
        public boolean removeItemFromCart(Item item) {
            return true;
        }
        
        public OrderStatus placeOrder(Order order) {
            return null;
        }
    }
    
    enum OrderStatus {
        UNSHIPED, PENDING, SHIPPED, COMPLETED, CANCELED
    }
    
    class Order {
        private String orderNumber;
        private OrderStatus status;
        private Date orderDate;
        private List<OrderLog> orderLog;
        
        public boolean sendForShipment() {
            return true;
        }
        
        public boolean makePayment(Payment payment) {
            return true;
        }
        
        public boolean addOrderLog(OrderLog orderLog) {
            return true;
        }
    }
    
    class OrderLog {
        private String orderNumber;
        private Date creationDate;
        private OrderStatus orderStatus;
        
    }
    
    class Shipment {
        private String shipmentNumber;
        private Date shipmentDate;
        private Date estimatedArrival;
        private String shipmentMethod;
        private Order orderDetails;
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
