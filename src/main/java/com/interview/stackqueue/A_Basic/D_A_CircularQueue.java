package com.interview.stackqueue.A_Basic;

/**
 * https://leetcode.com/problems/design-circular-queue/description/
 * Category: Medium, Fundamental, Must Do
 * Related Problems:
 * - https://leetcode.com/problems/design-circular-deque/description/ (Medium)
 * - https://leetcode.com/problems/design-front-middle-back-queue/description/ (Medium)
 *
 * 622. Design Circular Queue
 * --------------------------
 * A circular queue is a FIFO (First In First Out) data structure where the last position
 * is connected back to the first position to form a circle. It is also known as a "Ring Buffer."
 *
 * Benefits of Circular Queue:
 * - Utilizes unused space in front of the queue when elements are dequeued.
 * - Unlike a normal queue where space cannot be reused once the queue is full,
 *   a circular queue allows inserting new values in available space.
 *
 * Problem Statement:
 * ------------------
 * Implement the `MyCircularQueue` class:
 * - `MyCircularQueue(k)`: Initializes the queue with size `k`.
 * - `int Front()`: Returns the front item; if empty, return `-1`.
 * - `int Rear()`: Returns the last item; if empty, return `-1`.
 * - `boolean enQueue(int value)`: Inserts an element into the queue. Returns `true` if successful.
 * - `boolean deQueue()`: Deletes an element from the queue. Returns `true` if successful.
 * - `boolean isEmpty()`: Checks if the queue is empty.
 * - `boolean isFull()`: Checks if the queue is full.
 *
 * Constraints:
 * - Must implement without using built-in queue data structures.
 *
 * Example:
 * --------
 * Input:
 * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 *
 * Output:
 * [null, true, true, true, false, 3, true, true, true, 4]
 *
 * Explanation:
 * MyCircularQueue myCircularQueue = new MyCircularQueue(3);
 * myCircularQueue.enQueue(1); // return True
 * myCircularQueue.enQueue(2); // return True
 * myCircularQueue.enQueue(3); // return True
 * myCircularQueue.enQueue(4); // return False
 * myCircularQueue.Rear();     // return 3
 * myCircularQueue.isFull();   // return True
 * myCircularQueue.deQueue();  // return True
 * myCircularQueue.enQueue(4); // return True
 * myCircularQueue.Rear();     // return 4
 *
 * Understanding the Problem:
 * --------------------------
 * - Circular queue operates in a FIFO manner but wraps around when reaching the end.
 * - Useful for cases where a fixed buffer is needed (e.g., scheduling, networking).
 *
 * Approach:
 * ---------
 * - Implement using an array of size `k`.
 * - Maintain two pointers:
 *   - `front`: Points to the first element.
 *   - `rear`: Points to the last element.
 * - Use modular arithmetic to handle circular behavior:
 *   - `rear = (rear + 1) % capacity` (for enQueue)
 *   - `front = (front + 1) % capacity` (for deQueue)
 * - Track the current size to determine if the queue is full or empty.
 *
 * Time Complexity Analysis:
 * -------------------------
 * | Operation  | Complexity |
 * |------------|-----------|
 * | enQueue()  | O(1)      |
 * | deQueue()  | O(1)      |
 * | Front()    | O(1)      |
 * | Rear()     | O(1)      |
 * | isEmpty()  | O(1)      |
 * | isFull()   | O(1)      |
 *
 * ✅ All operations run in constant time O(1), making the implementation highly efficient.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * MyCircularQueue q = new MyCircularQueue(3);
 * System.out.println(q.enQueue(1)); // true
 * System.out.println(q.enQueue(2)); // true
 * System.out.println(q.enQueue(3)); // true
 * System.out.println(q.enQueue(4)); // false (queue is full)
 * System.out.println(q.Rear());     // 3
 * System.out.println(q.isFull());   // true
 * System.out.println(q.deQueue());  // true
 * System.out.println(q.enQueue(4)); // true
 * System.out.println(q.Rear());     // 4
 *
 * Execution State:
 * ----------------
 * | Operation   | Queue State | front | rear | size |
 * |------------|-------------|-------|------|------|
 * | enQueue(1) | [1, _, _]   | 0     | 0    | 1    |
 * | enQueue(2) | [1, 2, _]   | 0     | 1    | 2    |
 * | enQueue(3) | [1, 2, 3]   | 0     | 2    | 3    |
 * | enQueue(4) | Full → False |
 * | Rear()     | Returns 3 |
 * | isFull()   | Returns true |
 * | deQueue()  | [_, 2, 3]   | 1     | 2    | 2    |
 * | enQueue(4) | [4, 2, 3]   | 1     | 0    | 3    |
 * | Rear()     | Returns 4 |
 *
 * Alternative Implementation Using a Linked List:
 * ----------------------------------------------
 * - Instead of using an array, a doubly linked list can be used.
 * - This approach allows dynamic resizing.
 * - However, it introduces additional pointer overhead.
 *
 * Final Thoughts:
 * ---------------
 * - The array-based implementation is the most efficient.
 * - It provides O(1) time complexity for all operations.
 * - If dynamic resizing is required, consider a linked list implementation.
 */
public class D_A_CircularQueue {
    private int[] queue;  // Array to store queue elements
    private int front;    // Points to the first element
    private int rear;     // Points to the last element
    private int size;     // Current size of the queue
    private int capacity; // Maximum capacity of the queue

    // Constructor to initialize the circular queue
    public D_A_CircularQueue(int k) {
        capacity = k;
        queue = new int[k];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Enqueue operation: Insert an element at the rear
    public boolean enQueue(int value) {
        if (isFull()) return false; // Cannot insert if full
        rear = (rear + 1) % capacity; // Circular increment
        queue[rear] = value;
        size++;
        return true;
    }

    // Dequeue operation: Remove element from the front
    public boolean deQueue() {
        if (isEmpty()) return false; // Cannot remove if empty
        front = (front + 1) % capacity; // Circular increment
        size--;
        return true;
    }

    // Get the front element of the queue
    public int Front() {
        return isEmpty() ? -1 : queue[front];
    }

    // Get the rear element of the queue
    public int Rear() {
        return isEmpty() ? -1 : queue[rear];
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the queue is full
    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        D_A_CircularQueue q = new D_A_CircularQueue(3);
        System.out.println(q.enQueue(1)); // true
        System.out.println(q.enQueue(2)); // true
        System.out.println(q.enQueue(3)); // true
        System.out.println(q.enQueue(4)); // false (queue is full)
        System.out.println(q.Rear());     // 3
        System.out.println(q.isFull());   // true
        System.out.println(q.deQueue());  // true
        System.out.println(q.enQueue(4)); // true
        System.out.println(q.Rear());     // 4
    }
}
