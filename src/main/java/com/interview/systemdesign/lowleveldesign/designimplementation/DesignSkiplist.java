package com.interview.systemdesign.lowleveldesign.designimplementation;

import java.util.Random;

/*
Category: Hard, Google, Facebook, Must Do, Tricky, It is extremely hard so please watch the video
https://www.youtube.com/watch?v=FMYKVdWywcg
Related:
https://leetcode.com/problems/design-hashset/ Easy
https://leetcode.com/problems/design-hashmap/ Easy
https://leetcode.com/problems/design-linked-list/ Medium

 */
public class DesignSkiplist {
    /*
        🧠 Skiplist Concept Summary
        A Skiplist is a layered, probabilistic data structure that improves search, insert, and delete
        operations over a standard linked list by maintaining multiple levels of forward pointers
        that allow for fast traversal (like express lanes in a highway).

        Structure:
        - The bottom level is a fully connected sorted singly linked list.
        - Each higher level skips over elements to speed up access.
        - The head node has pointers for all levels.
        - Nodes are promoted to higher levels with 50% probability
          (or configurable via PROMOTION_PROBABILITY).

        📘 Methods Explained

        1. search(int target)
           - Starts from the highest level and scans right until it can’t go further without overshooting target.
           - Drops down one level and repeats.
           - Ends on level 0 where it checks for equality.
           - ✅ Returns true if found, false otherwise.

        2. add(int num)
           - Similar traversal to search but records the last position visited on each level in an update[] array.
           - Randomly generates the new node's level (higher with smaller probability).
           - Inserts the new node by adjusting pointers at each applicable level.

        3. erase(int num)
           - Again, traverses the skiplist while storing previous nodes on each level.
           - If num exists on level 0, removes the node by bypassing it on all levels.
           - Also trims unused top levels to keep the skiplist compact.

        ⏱️ Time Complexity

        Operation    | Average Time | Worst Time | Reason
        -------------------------------------------------------------
        Search       | O(log n)     | O(n)       | Level traversal and node skipping
        Add          | O(log n)     | O(n)       | Depends on level height and insertion
        Erase        | O(log n)     | O(n)       | Similar to search + pointer updates

        - In practice, operations are logarithmic thanks to the random distribution of node heights
          (expected height: O(log n)).

        🧮 Space Complexity
        - Each node has up to log n forward pointers (levels).
        - The total number of forward pointers across the skiplist is O(n) on average.
        - The update[] array used in add and erase methods is of fixed size MAX_LEVEL, so it contributes O(1).

        ✅ Overall Space Complexity: O(n)

        🔍 Use Case Summary
        - Efficient alternative to balanced binary search trees (like AVL or Red-Black Trees).
        - Useful in concurrent systems or for indexing, caches, and approximate data structures.
     */
    private static final int MAX_LEVEL = 16; // Maximum number of levels for the skiplist
    private static final double PROMOTION_PROBABILITY = 0.5; // Probability for promoting a node to a higher level

    // Node class representing each node in the skiplist
    static class Node {
        int value;            // The value of the node
        Node[] next;          // Array of forward pointers to nodes at each level

        Node(int value, int level) {
            this.value = value;
            this.next = new Node[level]; // Create an array of pointers for each level
        }
    }

    private Node head;           // Head node of the skiplist
    private int currentLevel;    // Current highest level in the skiplist
    private Random random;       // Random generator for level promotion

    public DesignSkiplist() {
        this.head = new Node(-1, MAX_LEVEL); // Create a dummy head node with -1 value and max level pointers
        this.currentLevel = 1;              // Initially, the skiplist has only one level
        this.random = new Random();         // Initialize random generator
    }

    // Generate a random level for the new node
    private int generateRandomLevel() {
        int level = 1;
        while (level < MAX_LEVEL && random.nextDouble() < PROMOTION_PROBABILITY) {
            level++; // Promote with a probability of 0.5
        }
        return level;
    }

    // Search for a target value in the skiplist
    public boolean search(int target) {
        Node current = head;
        // Start from the top level and go down to level 0
        for (int level = currentLevel - 1; level >= 0; level--) {
            // Move forward while next node exists and is less than target
            while (current.next[level] != null && current.next[level].value < target) {
                current = current.next[level];
            }
        }
        // Move to the next node on level 0 to check for equality
        current = current.next[0];
        return current != null && current.value == target;
    }

    // Add a value into the skiplist
    public void add(int num) {
        Node[] update = new Node[MAX_LEVEL]; // Track nodes at each level where new links need to be updated
        Node current = head;

        // Traverse from top to bottom to find insertion point at each level
        for (int level = currentLevel - 1; level >= 0; level--) {
            while (current.next[level] != null && current.next[level].value < num) {
                current = current.next[level];
            }
            update[level] = current; // Store the last node before insertion point at this level
        }

        int newLevel = generateRandomLevel(); // Decide the level of the new node

        // If new node has more levels than current skiplist, initialize them
        if (newLevel > currentLevel) {
            for (int i = currentLevel; i < newLevel; i++) {
                update[i] = head; // Update list at higher levels to head
            }
            currentLevel = newLevel; // Update the skiplist's current level
        }

        Node newNode = new Node(num, newLevel); // Create the new node
        // Insert the node at each appropriate level
        for (int i = 0; i < newLevel; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    // Erase a value from the skiplist
    public boolean erase(int num) {
        Node[] update = new Node[MAX_LEVEL]; // Track nodes that need to update their next pointers
        Node current = head;

        // Traverse top-down to find the node just before the target at each level
        for (int level = currentLevel - 1; level >= 0; level--) {
            while (current.next[level] != null && current.next[level].value < num) {
                current = current.next[level];
            }
            update[level] = current;
        }

        current = current.next[0]; // Move to potential target node on level 0
        if (current == null || current.value != num) {
            return false; // Node to delete not found
        }

        // Unlink the target node from all levels where it appears
        for (int i = 0; i < current.next.length; i++) {
            if (update[i].next[i] != current) break;
            update[i].next[i] = current.next[i];
        }

        // Remove empty levels from the top
        while (currentLevel > 1 && head.next[currentLevel - 1] == null) {
            currentLevel--;
        }

        return true;
    }
}

