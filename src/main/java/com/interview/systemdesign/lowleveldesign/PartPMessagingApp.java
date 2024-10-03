package com.interview.systemdesign.lowleveldesign;

import java.util.*;

/*
    Here’s a solution for designing a Messaging System like WhatsApp or Slack in Java, covering key components and real-time messaging features:

    Problem Breakdown:
    1. Users:
       - Represents people using the system who can send and receive messages.
    2. Messages:
       - Represents individual messages sent by users.
    3. Conversations:
       - Represents a series of messages between two or more users.
    4. Groups:
       - Represents group chats where multiple users can send and receive messages.

    Key Components:
    1. User Class:
       - Represents a user in the messaging system, with methods to send and receive messages.
    2. Message Class:
       - Represents a message sent from one user to another, or to a group. Contains content, timestamps, sender, and recipient.
    3. Conversation Class:
       - Manages one-on-one conversations between users. It holds a list of messages between the participants.
    4. Group Class:
       - Represents a group chat with multiple users. Manages group members and group conversations.
    5. MessagingSystem Class (Main):
       - Central system that manages users, messages, conversations, and groups.
       - Handles sending messages, creating conversations, and message delivery.

    Features:
    1. Real-time Messaging:
       - Users can send messages in real-time. The system ensures message delivery.
    2. Message Acknowledgment:
       - Messages have read/delivery receipts to track status.
    3. Conversations and Groups:
       - Supports both individual conversations and group chats.
    4. Message History:
       - Messages are stored and can be retrieved later.
    5. Persistence:
       - Messages and conversations can be persisted using databases for long-term storage.

    Considerations:
    1. Scalability:
       - For large-scale systems, distributed databases and messaging queues like Kafka may be needed.
    2. Real-time Delivery:
       - Using WebSockets or messaging protocols like MQTT for real-time message delivery.
*/
// User class to represent users in the messaging system
// User class to represent each user in the messaging system
class User {
    private String userId;
    private String username;
    private List<Conversation> conversations; // List of one-on-one conversations
    private List<Group> groups; // List of groups the user is part of

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.conversations = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void addConversation(Conversation conversation) {
        conversations.add(conversation);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void sendMessageToUser(User recipient, String content) {
        Conversation conversation = findOrCreateConversation(recipient);
        Message message = new Message(content, this, recipient);
        conversation.addMessage(message);
    }

    public void sendMessageToGroup(Group group, String content) {
        Message message = new Message(content, this, group);
        group.addMessage(message);
    }

    private Conversation findOrCreateConversation(User recipient) {
        for (Conversation conversation : conversations) {
            // Check if this conversation is with the recipient
            if (conversation.getParticipant(this).equals(recipient)) {
                return conversation;
            }
        }
        // Create a new conversation if one doesn't exist
        Conversation newConversation = new Conversation(this, recipient);
        conversations.add(newConversation);
        return newConversation;
    }
}

// Message class to represent individual messages
class Message {
    private String content;
    private Date timestamp;
    private User sender;
    private Object recipient; // Can be either a User (1-to-1) or a Group

    public Message(String content, User sender, Object recipient) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = new Date();
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public User getSender() {
        return sender;
    }

    public Object getRecipient() {
        return recipient;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + sender.getUsername() + ": " + content;
    }
}

// Conversation class to manage one-on-one conversations
class Conversation {
    private User participant1;
    private User participant2;
    private List<Message> messages;

    public Conversation(User participant1, User participant2) {
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.messages = new ArrayList<>();
    }

    // Returns the other participant in the conversation
    public User getParticipant(User currentUser) {
        return currentUser.equals(participant1) ? participant2 : participant1;
    }

    public void addMessage(Message message) {
        messages.add(message);
        System.out.println("Message sent: " + message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}

// Group class to represent group conversations
class Group {
    private String groupId;
    private String groupName;
    private List<User> members;
    private List<Message> messages;

    public Group(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void addMember(User user) {
        members.add(user);
        user.addGroup(this);
    }

    public void addMessage(Message message) {
        messages.add(message);
        notifyGroupMembers(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    private void notifyGroupMembers(Message message) {
        for (User member : members) {
            if (!member.equals(message.getSender())) {
                System.out.println("Message to " + member.getUsername() + ": " + message);
            }
        }
    }
}

// MessagingSystem class to manage all users, conversations, and groups
class MessagingSystem {
    private Map<String, User> users;
    private Map<String, Group> groups;

    public MessagingSystem() {
        this.users = new HashMap<>();
        this.groups = new HashMap<>();
    }

    public User registerUser(String userId, String username) {
        User user = new User(userId, username);
        users.put(userId, user);
        return user;
    }

    public Group createGroup(String groupId, String groupName, List<User> members) {
        Group group = new Group(groupId, groupName);
        for (User member : members) {
            group.addMember(member);
        }
        groups.put(groupId, group);
        return group;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public Group getGroup(String groupId) {
        return groups.get(groupId);
    }
}

// Main class to simulate the messaging system
public class PartPMessagingApp {
    public static void main(String[] args) {
        MessagingSystem system = new MessagingSystem();

        // Registering users
        User user1 = system.registerUser("1", "Alice");
        User user2 = system.registerUser("2", "Bob");
        User user3 = system.registerUser("3", "Charlie");

        // Creating a group
        List<User> groupMembers = Arrays.asList(user1, user2, user3);
        Group group = system.createGroup("101", "Study Group", groupMembers);

        // User-to-user messaging
        user1.sendMessageToUser(user2, "Hello Bob!");
        user2.sendMessageToUser(user1, "Hey Alice!");

        // Group messaging
        user3.sendMessageToGroup(group, "Hi everyone!");
        user1.sendMessageToGroup(group, "Hello Charlie!");
    }
}
