package com.interview.systemdesign.lowleveldesign;

/*
 * Date: 05/08/2020
 * Author: Mukesh Kumar Gupta
 *
 * Reference: https://www.geeksforgeeks.org/design-a-logistics-system/?ref=lbp
 * Difficulty: Hard
 * Companies: Google, Amazon, Facebook
 * Status: Not Done
 *
 * Overview:
 * A low-level design for a Logistics System that details the components and interactions needed for managing
 * logistics operations, such as tracking shipments, managing inventory, and coordinating between various stakeholders
 * like warehouses, suppliers, and customers.
 *
 * Core Requirements:
 * 1. Shipment Management: Track shipments from source to destination.
 * 2. Inventory Management: Maintain an accurate record of items in stock across different warehouses.
 * 3. Order Processing: Process customer orders and manage delivery.
 * 4. Route Optimization: Find optimal delivery routes to reduce costs and improve efficiency.
 * 5. User Roles: Manage user roles such as Admin, Warehouse Manager, Driver, and Customer.
 * 6. Notifications: Send alerts and notifications regarding shipment status or delivery issues.
 * 7. Reporting: Provide daily, weekly, and monthly reports for inventory, shipments, and overall logistics performance.
 *
 * Approach:
 * The system is designed using a modular, service-oriented architecture (SOA) with microservices for different functionalities.
 * Each service focuses on a single responsibility, ensuring scalability and maintainability.
 *
 * Components:
 * 1. User Management:
 *    Classes: User, Admin, WarehouseManager, Driver, Customer
 *    Functions:
 *    - login(): Authenticates users.
 *    - viewProfile(): Allows users to view their profile.
 *    - assignRoles(): Admin assigns roles to different users.
 *
 * 2. Inventory Management:
 *    Classes: Item, Warehouse, Inventory
 *    Functions:
 *    - addItem(): Adds a new item to inventory.
 *    - updateInventory(): Updates item quantity in inventory.
 *    - trackInventory(): Returns current stock levels across warehouses.
 *
 * 3. Order Processing:
 *    Classes: Order, CustomerOrder, OrderItem
 *    Functions:
 *    - createOrder(): Creates a new customer order.
 *    - assignWarehouse(): Assigns a warehouse to fulfill the order.
 *    - processOrder(): Processes the order for shipment.
 *
 * 4. Shipment Management:
 *    Classes: Shipment, ShipmentStatus, Route
 *    Functions:
 *    - createShipment(): Creates a shipment for an order.
 *    - updateShipmentStatus(): Updates shipment status (in transit, delivered, etc.).
 *    - trackShipment(): Retrieves the current shipment status.
 *
 * 5. Route Optimization:
 *    Classes: Route, Location, Vehicle
 *    Functions:
 *    - calculateOptimalRoute(): Calculates the most efficient delivery route.
 *    - assignVehicle(): Assigns a delivery vehicle to a shipment.
 *
 * 6. Notification System:
 *    Classes: Notification, EmailNotification, SMSNotification
 *    Functions:
 *    - sendNotification(): Sends notifications (email/SMS) to users about shipment status or delays.
 *    - setNotificationPreferences(): Allows users to set notification preferences.
 *
 * 7. Reporting:
 *    Classes: Report, InventoryReport, ShipmentReport, OrderReport
 *    Functions:
 *    - generateInventoryReport(): Generates reports on inventory levels.
 *    - generateShipmentReport(): Generates reports on shipments.
 *    - generateOrderReport(): Generates reports on processed orders.
 *
 * System Design:
 * 1. Database Design:
 *    Tables:
 *    - User Table: userId, name, role, email, passwordHash
 *    - Warehouse Table: warehouseId, location, managerId
 *    - Item Table: itemId, name, description, stockQuantity, warehouseId
 *    - Order Table: orderId, customerId, status, warehouseId
 *    - Shipment Table: shipmentId, orderId, vehicleId, status
 *    - Route Table: routeId, shipmentId, startLocation, endLocation, estimatedDeliveryTime
 *
 * 2. Service Layer:
 *    - UserService: Handles user-related activities (authentication, profile management).
 *    - InventoryService: Manages inventory updates and stock levels.
 *    - OrderService: Processes orders and assigns warehouses.
 *    - ShipmentService: Manages shipment status tracking and updates.
 *    - RouteService: Optimizes delivery routes for shipments.
 *
 * 3. API Design:
 *    - POST /login: Authenticates a user.
 *    - GET /inventory/{warehouseId}: Retrieves inventory from a specific warehouse.
 *    - POST /order: Creates a new customer order.
 *    - GET /shipment/{shipmentId}: Retrieves the shipment status.
 *    - POST /route/calculate: Calculates the optimal delivery route.
 *
 * Scalability Considerations:
 * 1. Database Sharding: Split large tables (e.g., Orders, Shipments) across different databases or shards.
 * 2. Load Balancing: Use load balancers to distribute traffic across service instances.
 * 3. Caching: Implement caching (Redis, Memcached) for frequently accessed data like inventory levels, shipment statuses, etc.
 * 4. Message Queues: Use message queues (RabbitMQ, Kafka) for asynchronous task processing.
 *
 * Security Considerations:
 * 1. Authentication & Authorization: Implement role-based access control (RBAC) for different user types (Admin, Warehouse Manager, etc.).
 * 2. Data Encryption: Encrypt sensitive data such as user information and shipment details.
 * 3. Logging & Monitoring: Enable detailed logging for audit trails and monitor system activity.
 *
 * Additional Features:
 * - Real-time Tracking: Integrate GPS tracking for shipments.
 * - Automated Notifications: Send SMS/email alerts when shipment status changes.
 * - Analytics & Reports: Generate reports for performance tracking (order fulfillment, delays, inventory turnover).
 *
 * Breakdown of the Code:
 * - Item Class: Represents an item with an ID, name, and quantity.
 * - Inventory Class: Manages inventory items within a warehouse.
 * - Warehouse Class: Each warehouse has an ID and inventory.
 * - Order Class: Represents an order with multiple items.
 * - Shipment Class: Represents a shipment and its status (created, in transit, delivered).
 * - InventoryService Class: Manages adding items, updating inventory, and tracking stock.
 * - ShipmentService Class: Manages creating shipments, updating shipment statuses, and tracking shipments.
 *
 * This system allows management of multiple warehouses, orders, shipments, and tracking inventory and shipment statuses.
 */

import java.util.Map;
import java.util.HashMap;
import java.util.List;

// Item class
class Item {
    private String itemId;
    private String name;
    private int quantity;

    public Item(String itemId, String name, int quantity) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Inventory class
class Inventory {
    private Map<String, Item> itemMap = new HashMap<>();

    public void addItem(Item item) {
        itemMap.put(item.getItemId(), item);
    }

    public void updateQuantity(String itemId, int quantity) {
        if (itemMap.containsKey(itemId)) {
            Item item = itemMap.get(itemId);
            item.setQuantity(quantity);
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    public int getQuantity(String itemId) {
        return itemMap.getOrDefault(itemId, new Item(itemId, "", 0)).getQuantity();
    }
}

// Warehouse class
class Warehouse {
    private String warehouseId;
    private Inventory inventory;

    public Warehouse(String warehouseId) {
        this.warehouseId = warehouseId;
        this.inventory = new Inventory();
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public Inventory getInventory() {
        return inventory;
    }
}

// Order class
class Order {
    private String orderId;
    private List<Item> items;

    public Order(String orderId, List<Item> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Item> getItems() {
        return items;
    }
}

// ShipmentStatus Enum
enum ShipmentStatus {
    CREATED,
    IN_TRANSIT,
    DELIVERED
}

// Shipment class
class Shipment {
    private String shipmentId;
    private Order order;
    private ShipmentStatus status;

    public Shipment(Order order) {
        this.shipmentId = "SHP-" + System.currentTimeMillis();
        this.order = order;
        this.status = ShipmentStatus.CREATED;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }
}

// InventoryService class
class InventoryService {
    private Map<String, Warehouse> warehouseMap = new HashMap<>();

    public void addWarehouse(Warehouse warehouse) {
        warehouseMap.put(warehouse.getWarehouseId(), warehouse);
    }

    public void addItem(String warehouseId, Item item) {
        Warehouse warehouse = warehouseMap.get(warehouseId);
        if (warehouse != null) {
            warehouse.getInventory().addItem(item);
        } else {
            System.out.println("Warehouse not found.");
        }
    }

    public void updateInventory(String warehouseId, String itemId, int quantity) {
        Warehouse warehouse = warehouseMap.get(warehouseId);
        if (warehouse != null) {
            warehouse.getInventory().updateQuantity(itemId, quantity);
        } else {
            System.out.println("Warehouse not found.");
        }
    }

    public int trackInventory(String warehouseId, String itemId) {
        Warehouse warehouse = warehouseMap.get(warehouseId);
        if (warehouse != null) {
            return warehouse.getInventory().getQuantity(itemId);
        } else {
            System.out.println("Warehouse not found.");
            return 0;
        }
    }
}

// ShipmentService class
class ShipmentService {
    private Map<String, Shipment> shipmentMap = new HashMap<>();

    public Shipment createShipment(Order order) {
        Shipment shipment = new Shipment(order);
        shipmentMap.put(shipment.getShipmentId(), shipment);
        return shipment;
    }

    public void updateShipmentStatus(String shipmentId, ShipmentStatus status) {
        Shipment shipment = shipmentMap.get(shipmentId);
        if (shipment != null) {
            shipment.setStatus(status);
        } else {
            System.out.println("Shipment not found.");
        }
    }

    public ShipmentStatus trackShipment(String shipmentId) {
        Shipment shipment = shipmentMap.get(shipmentId);
        if (shipment != null) {
            return shipment.getStatus();
        } else {
            System.out.println("Shipment not found.");
            return null;
        }
    }
}

// Main class to demonstrate functionality
public class LogisticsSystemDesign {
    public static void main(String[] args) {
        // Creating InventoryService and ShipmentService
        InventoryService inventoryService = new InventoryService();
        ShipmentService shipmentService = new ShipmentService();

        // Creating Warehouse
        Warehouse warehouse1 = new Warehouse("WH-001");
        inventoryService.addWarehouse(warehouse1);

        // Adding Items to Warehouse
        Item item1 = new Item("ITEM-001", "Laptop", 10);
        Item item2 = new Item("ITEM-002", "Phone", 20);
        inventoryService.addItem("WH-001", item1);
        inventoryService.addItem("WH-001", item2);

        // Updating Inventory
        inventoryService.updateInventory("WH-001", "ITEM-001", 15);

        // Tracking Inventory
        int quantity = inventoryService.trackInventory("WH-001", "ITEM-001");
        System.out.println("Inventory for ITEM-001: " + quantity);

        // Creating an Order
        Order order = new Order("ORD-001", List.of(item1, item2));

        // Creating Shipment for the Order
        Shipment shipment = shipmentService.createShipment(order);
        System.out.println("Shipment created with ID: " + shipment.getShipmentId());

        // Updating Shipment Status
        shipmentService.updateShipmentStatus(shipment.getShipmentId(), ShipmentStatus.IN_TRANSIT);

        // Tracking Shipment
        ShipmentStatus status = shipmentService.trackShipment(shipment.getShipmentId());
        System.out.println("Shipment status: " + status);
    }
}



