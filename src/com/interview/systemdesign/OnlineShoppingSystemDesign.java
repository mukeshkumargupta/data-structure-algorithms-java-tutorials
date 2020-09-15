package com.interview.systemdesign;

//Reference: https://www.youtube.com/watch?v=qGR7KoGpqrU&list=PLIA-9QRQ0RqHcTCuIusS1G1LikGoD3WxM&index=5&t=4s
import java.util.*;

enum AccountStatus {
    Inactive, Active
}

enum AccountType {
    Seller, Buyer, Admin
}

enum ProductCategory {
    Appliance, Electronics
}

class Address {
    String name;
    String houseNumber;
    String street;
    String area;
    String landmark;
    String pinCode;
}
class CreditCard {
    String userName;
    int number;
    int cvv;
    Date expiryDate;
}

class CreditCardService {
    CreditCardService() {
        
    }
    boolean validateCreditCard(CreditCard card) {
        return true;
        
    }
}

enum Rating {
    BelowAvergae, Average, AboveAverage, Best
}

enum OrderStatus {
    Unshipped, Pending, Shipped, Completed, Cancled
}

enum ShipmentMethod {
    FirstFlight, Courier
}
class ProductReview {
    int productId;
    String review;
    Rating rating;
}

class Shipment {
    private String shipmentId;
    private Date shipmentDate;
    private Date estimatedArrival;
    private ShipmentMethod shipmentMethod;
    private Order orderDetails;
}
class Order {
    private String orderId;
    private OrderStatus status;
    private Date orderDate;
    private List<OrderLog> orderLog = new ArrayList<>();
    
    
    //Use payment service
    public boolean makePayment(Payment payment) {
        return true;
        
    }
    public boolean sendForShipment() {
        return true;
        
    }

}

class OrderLog {
    private String orderId;
    private Date creationDate;
    private OrderStatus status;
    

}

class Customer {
    private Account account;
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

class Account {
    private String userName;
    private String userPassword;
    private AccountStatus status;
    private AccountType type;
    private String name;
    private Address shippingAddress;
    private Address billingAddress;
    private String email;
    private String phone;
    private List<CreditCard> creditCards = new ArrayList<>();
    public boolean addCreditCard(CreditCard card) {
        //First validate then add card in list
        return true;
        
    }
    public boolean addProduct (Product product) {
        return true;
    }
    
    public boolean addProductReview(ProductReview review) {
        return true;
    }
    
}

class Product {
    private String productId;
    private String name;
    private String description;
    private double price;
    private ProductCategory category;
    private int availableItemCount;
    private Account seller;
}

class Cart {
    List<Item> items = new ArrayList<>();
    
    boolean addItem(Item item) {
        return false;
    }
    
    boolean removeItem(Item item) {
        return false;
    }
    
    boolean updateItem(Item item, int quantity) {
        return false;
    }
    
    List<Item> getItems() {
        return items;
    }
    
    public boolean checkout() {
        return true;
    }
    
}

class Item {
    private String productId;
    private int quantity;
    private double price;
    
    boolean updateQuantity(int quanity) {
        
        return true;
    }
}

public class OnlineShoppingSystemDesign {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
