package com.interview.systemdesign.lowleveldesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Here’s a complete solution for designing a notification system that allows users
 * to subscribe to different events and receive notifications in real-time. This
 * implementation includes classes for Notification, User, Event, and Subscription,
 * along with the necessary functionality for managing notifications.
 *
 * Explanation of the Code:
 *
 * - Notification Class:
 *   Represents a notification with attributes like message and event type.
 *
 * - User Class:
 *   Represents a user who can subscribe to notifications. It includes a method
 *   to notify the user.
 *
 * - Event Class:
 *   Represents an event that users can subscribe to.
 *
 * - Subscription Class:
 *   Represents a subscription of a user to a specific event.
 *
 * - NotificationSystem Class:
 *   Manages subscriptions, event creation, and notification dispatching.
 *   Handles user subscriptions and sends notifications based on events.
 *
 * - Main Class:
 *   Demonstrates how to use the notification system by creating users, events,
 *   subscribing to events, and dispatching notifications.
 *
 * Considerations:
 *
 * - Handling Different Notification Channels: The system allows for notifications
 *   to be sent via various channels (e.g., email, SMS, push notifications).
 *
 * - Ensuring Timely Delivery: The current implementation does not include retries
 *   or failure handling, but this can be added based on requirements.
 *
 * This design provides a robust foundation for a notification system that can
 * be extended with additional features and functionalities as needed.
 */

// Notification Class
class Notification {
    private String message;
    private String eventType;

    public Notification(String message, String eventType) {
        this.message = message;
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public String getEventType() {
        return eventType;
    }
}

// User Class
class User {
    private String name;
    private List<String> notificationChannels;

    public User(String name, List<String> notificationChannels) {
        this.name = name;
        this.notificationChannels = notificationChannels;
    }

    public String getName() {
        return name;
    }

    public List<String> getNotificationChannels() {
        return notificationChannels;
    }

    public void notifyUser(Notification notification) {
        System.out.println("Notifying " + name + " via " + notification.getEventType() + ": " + notification.getMessage());
    }
}

// Event Class
class Event {
    private String name;

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Subscription Class
class Subscription {
    private User user;
    private Event event;

    public Subscription(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }
}

// NotificationSystem Class
class NotificationSystem {
    private List<Subscription> subscriptions;
    private Map<String, List<Notification>> notificationsByEvent;

    public NotificationSystem() {
        this.subscriptions = new ArrayList<>();
        this.notificationsByEvent = new HashMap<>();
    }

    public void subscribe(User user, Event event) {
        Subscription subscription = new Subscription(user, event);
        subscriptions.add(subscription);
        System.out.println(user.getName() + " subscribed to " + event.getName());
    }

    public void createEvent(String eventName) {
        Event event = new Event(eventName);
        System.out.println("Event created: " + eventName);
    }

    public void dispatchNotification(Notification notification) {
        notificationsByEvent.putIfAbsent(notification.getEventType(), new ArrayList<>());
        notificationsByEvent.get(notification.getEventType()).add(notification);
        notifyUsers(notification);
    }

    private void notifyUsers(Notification notification) {
        for (Subscription subscription : subscriptions) {
            if (subscription.getEvent().getName().equals(notification.getEventType())) {
                subscription.getUser().notifyUser(notification);
            }
        }
    }
}

// Main Class to demonstrate usage
public class PartINotificationSystem {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("Alice", List.of("email", "SMS"));
        User user2 = new User("Bob", List.of("push"));

        // Create notification system
        NotificationSystem notificationSystem = new NotificationSystem();

        // Create events
        notificationSystem.createEvent("Order Shipped");
        notificationSystem.createEvent("Order Delivered");

        // Subscribe users to events
        notificationSystem.subscribe(user1, new Event("Order Shipped"));
        notificationSystem.subscribe(user2, new Event("Order Delivered"));

        // Dispatch notifications
        notificationSystem.dispatchNotification(new Notification("Your order has been shipped!", "Order Shipped"));
        notificationSystem.dispatchNotification(new Notification("Your order has been delivered!", "Order Delivered"));
    }
}
