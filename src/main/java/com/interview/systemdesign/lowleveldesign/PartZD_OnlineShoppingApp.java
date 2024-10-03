package com.interview.systemdesign.lowleveldesign;

/*
 * Category: Must Do, Amazon
 *
 *
 * Category: E-Commerce
 *
 * Designing an Online Shopping System involves creating a comprehensive platform that
 * allows users to browse products, manage their shopping carts, place orders, and process
 * payments. Below is a low-level design outline that captures key components,
 * interactions, and system functionalities.
 *
 * Low-Level Design for Online Shopping System
 *
 * 1. Key Components
 *    - User Management:
 *      - Handles user registration, authentication, and profile management.
 *      - Supports password management, account settings, and user preferences.
 *
 *    - Product Management:
 *      - Manages product listings, including details such as name, description, price,
 *        images, and stock availability.
 *      - Allows administrators to add, update, and delete products.
 *
 *    - Shopping Cart Management:
 *      - Manages the shopping cart for users, allowing them to add, remove, and update
 *        quantities of products.
 *      - Provides a mechanism to save the cart for future sessions.
 *
 *    - Order Management:
 *      - Manages the creation, updating, and cancellation of orders.
 *      - Handles order status updates and history retrieval.
 *
 *    - Payment Processing:
 *      - Integrates with payment gateways to process transactions securely.
 *      - Manages payment details and transaction histories.
 *
 *    - Inventory Management:
 *      - Keeps track of product stock levels and manages restocking processes.
 *      - Sends notifications for low stock items.
 *
 *    - Review and Rating System:
 *      - Allows users to leave reviews and ratings for products.
 *      - Manages review moderation and display of ratings.
 *
 *    - Notification System:
 *      - Sends real-time notifications to users regarding order statuses, promotions,
 *        and product updates.
 *
 * 2. Core Requirements
 *    - User Registration and Authentication:
 *      - Support user registration, login, password reset, and multi-factor authentication.
 *
 *    - Product Browsing and Search:
 *      - Implement search functionality to allow users to find products by name, category,
 *        or other attributes.
 *
 *    - Shopping Cart Operations:
 *      - Handle various operations related to the shopping cart, including persistence
 *        across user sessions.
 *
 *    - Order Processing:
 *      - Validate orders and provide a checkout process with order summaries and payment
 *        options.
 *
 *    - Data Persistence:
 *      - Use a database for storing user data, product listings, order histories, and
 *        reviews.
 *
 *    - Performance Monitoring:
 *      - Monitor system performance metrics and logs for troubleshooting.
 *
 *    - Scalability:
 *      - Design the system to handle a growing number of users, products, and transactions
 *        efficiently.
 *
 *    - Security:
 *      - Implement encryption for sensitive data, secure API access, and input validation.
 *
 * 3. Class Design
 *    - User: Represents user details, including registration, authentication, and profile management.
 *    - Product: Represents product details and management.
 *    - ShoppingCart: Manages the items in the user's shopping cart.
 *    - Order: Represents order details, including products, payment, and status.
 *    - PaymentGateway: Handles payment processing and transaction management.
 *    - Inventory: Manages stock levels and restocking processes.
 *    - Review: Represents user reviews and ratings for products.
 *    - Notification: Manages notifications sent to users.
 *
 * 4. System Architecture
 *    - Frontend: Web or mobile interface for user interactions, allowing product browsing and checkout.
 *    - Backend: RESTful API to handle requests and responses for user actions.
 *    - Database: SQL or NoSQL database for data storage, including users, products, orders, and reviews.
 *    - External APIs: Integrate with payment gateways for secure transactions.
 *    - Message Queue: For processing asynchronous tasks (e.g., order processing, notifications).
 *
 * 5. Scalability Considerations
 *    - Microservices Architecture: Each component (e.g., user management, order processing) can be developed as a separate service.
 *    - Load Balancing: Distribute traffic across multiple instances of services to ensure performance.
 *    - Database Sharding: Scale database horizontally by distributing data across multiple servers.
 *
 * 6. Security Considerations
 *    - Data Encryption: Secure sensitive user data and communication channels.
 *    - Authentication: Use OAuth2 or JWT for secure API access.
 *    - Input Validation: Prevent SQL injection and cross-site scripting (XSS) attacks.
 *
 * 7. Monitoring and Logging
 *    - Performance Metrics: Track system performance (response times, order processing times).
 *    - Error Tracking: Implement logging mechanisms for troubleshooting and monitoring system health.
 *
 * Conclusion
 * This low-level design for an Online Shopping System provides a structured approach
 * to building a robust platform that supports product management, user interactions,
 * and order processing. The design can be expanded further to include additional
 * features such as personalized recommendations, wish lists, and advanced analytics.
 */


import java.util.*;

// User Class
class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public boolean checkPassword(String password) { return this.password.equals(password); }
}

// Product Class
class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters and Setters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void reduceStock(int quantity) {
        stock -= quantity;
    }
}

// ShoppingCart Class
class ShoppingCart {
    private Map<Product, Integer> items = new HashMap<>();

    public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public double calculateTotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<Product, Integer> getItems() { return items; }
}

// Order Class
class Order {
    private List<Product> products;
    private double totalAmount;

    public Order(List<Product> products, double totalAmount) {
        this.products = products;
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() { return totalAmount; }
}

// PaymentGateway Class
class PaymentGateway {
    public boolean processPayment(double amount) {
        // Simulate payment processing (always successful for simplicity)
        System.out.println("Payment of $" + amount + " processed successfully.");
        return true;
    }
}

// OnlineShoppingSystem Class
class OnlineShoppingSystem {
    private List<User> users = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private ShoppingCart shoppingCart;
    private User currentUser;

    public OnlineShoppingSystem() {
        this.shoppingCart = new ShoppingCart();
    }

    public void registerUser(String username, String password, String email) {
        users.add(new User(username, password, email));
        System.out.println("User registered: " + username);
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                currentUser = user;
                System.out.println("User logged in: " + username);
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void addProduct(String name, double price, int stock) {
        products.add(new Product(name, price, stock));
        System.out.println("Product added: " + name);
    }

    public void addToCart(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            shoppingCart.addItem(product, quantity);
            product.reduceStock(quantity);
            System.out.println("Added " + quantity + " of " + product.getName() + " to cart.");
        } else {
            System.out.println("Not enough stock for " + product.getName());
        }
    }

    public void checkout() {
        double total = shoppingCart.calculateTotal();
        PaymentGateway paymentGateway = new PaymentGateway();
        if (paymentGateway.processPayment(total)) {
            Order order = new Order(new ArrayList<>(shoppingCart.getItems().keySet()), total);
            System.out.println("Order placed successfully! Total amount: $" + total);
        }
    }
}

// Main Class
public class OnlineShoppingApp {
    public static void main(String[] args) {
        OnlineShoppingSystem system = new OnlineShoppingSystem();

        // Sample Data
        system.registerUser("john_doe", "password123", "john@example.com");
        system.registerUser("jane_smith", "mypassword", "jane@example.com");

        system.login("john_doe", "password123");

        system.addProduct("Laptop", 1200.00, 5);
        system.addProduct("Smartphone", 800.00, 10);

        Product laptop = system.products.get(0);
        Product smartphone = system.products.get(1);

        system.addToCart(laptop, 1);
        system.addToCart(smartphone, 2);

        system.checkout();
    }
}
