package com.interview.systemdesign.lowleveldesign;

import java.util.HashMap;
import java.util.Map;

/*
    Here’s a solution to the Vending Machine design problem using object-oriented principles in Java:

    Key Components:
    - Product: Represents an item in the vending machine, including details like name, price, and quantity.
    - Slot: Represents a slot in the vending machine that holds a specific product.
    - Money: Manages the money inserted into the machine and handles payments.
    - VendingMachine: Manages slots, products, and handles the selection, payment, dispensing of items, and change return.

    Key Methods:
    - selectItem: Allows the user to select an item.
    - insertMoney: Accepts money from the user.
    - dispenseItem: Dispenses the selected item if enough money is inserted.
    - returnChange: Returns the change to the user.
    - manageStock: Handles inventory and stock management.

    Considerations:
    - Insufficient Funds: The machine should handle situations where the inserted money is less than the item’s price.

    Key Considerations:
    - Handling Insufficient Funds: If the inserted money is less than the price of the product, the user is prompted to insert more money.
    - Stock and Inventory Management: Each slot tracks its product's stock, and the system ensures items are only dispensed if they are available.
    - Return Change: The system calculates and returns change if the inserted money exceeds the item price.
    - Scalability: The system can handle multiple slots and products, and can be easily extended by adding more products or new features like accepting different denominations of money.
*/

// Product class representing an item in the vending machine
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Slot class representing a vending machine slot holding a product
class Slot {
    private int slotNumber;
    private Product product;

    public Slot(int slotNumber, Product product) {
        this.slotNumber = slotNumber;
        this.product = product;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isAvailable() {
        return product.getQuantity() > 0;
    }

    public void dispenseProduct() {
        if (isAvailable()) {
            product.setQuantity(product.getQuantity() - 1);
        }
    }
}

// Money class managing inserted money
class Money {
    private double totalAmount;

    public Money() {
        this.totalAmount = 0;
    }

    public void insertMoney(double amount) {
        totalAmount += amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void reset() {
        totalAmount = 0;
    }

    public double returnChange(double cost) {
        return totalAmount - cost;
    }
}

// VendingMachine class managing slots, products, and transactions
class VendingMachine {
    private Map<Integer, Slot> slots;
    private Money money;

    public VendingMachine() {
        slots = new HashMap<>();
        money = new Money();
    }

    // Add slot with product to vending machine
    public void addSlot(int slotNumber, Product product) {
        slots.put(slotNumber, new Slot(slotNumber, product));
    }

    // Select an item by slot number
    public boolean selectItem(int slotNumber) {
        Slot slot = slots.get(slotNumber);
        if (slot == null) {
            System.out.println("Invalid slot selection.");
            return false;
        }
        if (!slot.isAvailable()) {
            System.out.println("Selected product is out of stock.");
            return false;
        }
        System.out.println("Selected product: " + slot.getProduct().getName());
        return true;
    }

    // Insert money
    public void insertMoney(double amount) {
        money.insertMoney(amount);
        System.out.println("Money inserted: $" + amount);
    }

    // Dispense the product if sufficient funds are inserted
    public void dispenseItem(int slotNumber) {
        Slot slot = slots.get(slotNumber);
        if (slot == null || !slot.isAvailable()) {
            System.out.println("Cannot dispense item. Invalid selection or out of stock.");
            return;
        }

        double price = slot.getProduct().getPrice();
        if (money.getTotalAmount() >= price) {
            slot.dispenseProduct();
            System.out.println("Dispensed: " + slot.getProduct().getName());
            double change = money.returnChange(price);
            if (change > 0) {
                System.out.println("Change returned: $" + change);
            }
            money.reset();
        } else {
            System.out.println("Insufficient funds. Please insert more money.");
        }
    }

    // Check stock of products
    public void checkStock() {
        for (Slot slot : slots.values()) {
            System.out.println("Slot " + slot.getSlotNumber() + ": " + slot.getProduct().getName() +
                    " (Quantity: " + slot.getProduct().getQuantity() + ")");
        }
    }
}

// Main class to test the Vending Machine functionality
public class PartFVendingMachineSystem {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        // Adding products to slots
        vendingMachine.addSlot(1, new Product("Coke", 1.50, 10));
        vendingMachine.addSlot(2, new Product("Pepsi", 1.30, 8));
        vendingMachine.addSlot(3, new Product("Chips", 2.00, 5));

        // Check stock before transactions
        vendingMachine.checkStock();

        // Select product, insert money, and dispense item
        if (vendingMachine.selectItem(1)) {
            vendingMachine.insertMoney(2.00); // Insert money
            vendingMachine.dispenseItem(1); // Dispense product
        }

        // Check stock after transactions
        vendingMachine.checkStock();
    }
}
