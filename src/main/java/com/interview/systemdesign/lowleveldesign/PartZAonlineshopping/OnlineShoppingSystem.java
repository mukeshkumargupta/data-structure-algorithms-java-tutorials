package com.interview.systemdesign.lowleveldesign.PartZAonlineshopping;

import java.util.ArrayList;
import java.util.List;

/*
Key Components Explained:

- **User Management:**
  - Handles user registration, authentication, and profile updates.
  - Ensures secure management of user data.

- **Product Management:**
  - Manages the addition, update, and removal of products.
  - Provides functionality to list available products.

- **Shopping Cart:**
  - Manages products added by users before purchase.
  - Allows for easy addition and removal of items.

- **Order Processing:**
  - Handles the creation and management of orders.
  - Integrates payment processing and updates order status.

- **Payment Gateway Integration:**
  - Processes payments securely through third-party payment gateways.
  - Manages payment details and refund requests.

- **Order Tracking:**
  - Provides users with tracking information for their orders.
  - Enables visibility into the order delivery process.

Scalability and Security:
- **Scalability:** Designed to handle increased loads and independent service scaling.
- **Security:** Incorporates measures for authentication, encryption, and input validation to protect user data and maintain system integrity.

This design can be expanded further with additional functionalities, such as product reviews, wishlists, and more sophisticated order management.
*/
public class OnlineShoppingSystem {

    public static void main(String[] args) {
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        CartService cartService = new CartService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();

        // Creating a user
        User user = new User(1, "Alice", "alice@example.com", "password123", "123 Main St");
        userService.registerUser(user);

        // Adding products
        productService.addProduct(new Product(1, "Laptop", "High-performance laptop", 1200.00, 10));
        productService.addProduct(new Product(2, "Smartphone", "Latest model smartphone", 800.00, 15));

        // Adding to cart
        cartService.addToCart(user.getId(), productService.listProducts().get(1), 1);
        Cart cart = cartService.viewCart(user.getId());
        System.out.println("Cart total: " + cart.getTotalPrice());

        // Creating an order
        List<Product> productsInOrder = new ArrayList<>(cart.getItems().keySet());
        Order order = orderService.createOrder(user, productsInOrder, cart.getTotalPrice());
        System.out.println("Order created with ID: " + order.getOrderId());

        // Processing payment
        Payment payment = paymentService.processPayment(order);
        System.out.println("Payment processed for order ID: " + payment.getOrder().getOrderId());

        // Updating order status
        orderService.updateOrderStatus(order.getOrderId(), "Shipped");
        System.out.println("Order status updated to: " + orderService.getOrderDetails(order.getOrderId()).getStatus());
    }
}
