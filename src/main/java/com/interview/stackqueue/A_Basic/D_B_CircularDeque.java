package com.interview.stackqueue.A_Basic;

/**
 * https://leetcode.com/problems/design-circular-deque/
 *
 * Related:
 * https://leetcode.com/problems/design-circular-queue/submissions/1540420996/
 *
 * Understanding the Problem:
 * A circular deque allows insertion and deletion from both ends while maintaining a circular structure.
 * ✅ It follows the double-ended queue (deque) principle but uses circular indexing to optimize space.
 *
 * Key Differences from Circular Queue (622):
 *
 * Feature            Circular Queue      Circular Deque
 * ------------------------------------------------------
 * Insertion         Only at rear        At front and rear
 * Deletion         Only from front      From front and rear
 * Data Structure   Array-based queue    Array or linked list
 *
 * Approach:
 * We can implement this using:
 * - Fixed-size array (int[] queue)
 * - Use two pointers: front and rear
 * - Maintain size for tracking occupancy
 * - Use modulo arithmetic for circular behavior
 *
 * Alternative Approach:
 * - Doubly Linked List
 * - Uses dynamic memory allocation
 * - Eliminates the need for a fixed size
 *
 * Implementation Details:
 * Circular Indexing:
 * - When inserting at front: front = (front - 1 + capacity) % capacity;
 * - When inserting at rear: rear = (rear + 1) % capacity;
 * - When deleting from front: front = (front + 1) % capacity;
 * - When deleting from rear: rear = (rear - 1 + capacity) % capacity;
 *
 * Edge Cases:
 * - Trying to insert when full → Return false
 * - Trying to delete when empty → Return false
 * - Handling a single element deque correctly
 *
 * Time Complexity:
 *
 * Operation        Complexity
 * ----------------------------
 * insertFront()   O(1)
 * insertLast()    O(1)
 * deleteFront()   O(1)
 * deleteLast()    O(1)
 * getFront()      O(1)
 * getRear()       O(1)
 * isEmpty()       O(1)
 * isFull()        O(1)
 * ✅ All operations run in O(1) time, making this an efficient implementation.
 *
 * Example Walkthrough:
 *
 * MyCircularDeque q = new MyCircularDeque(3);
 * System.out.println(q.insertLast(1));  // true
 * System.out.println(q.insertLast(2));  // true
 * System.out.println(q.insertFront(3)); // true
 * System.out.println(q.insertFront(4)); // false (full)
 * System.out.println(q.getRear());      // 2
 * System.out.println(q.isFull());       // true
 * System.out.println(q.deleteLast());   // true
 * System.out.println(q.insertFront(4)); // true
 * System.out.println(q.getFront());     // 4
 *
 * Execution:
 *
 * Operation          Queue State   front  rear  size
 * ---------------------------------------------------
 * insertLast(1)     [1, _, _]       0      0     1
 * insertLast(2)     [1, 2, _]       0      1     2
 * insertFront(3)    [1, 2, 3]       2      1     3
 * insertFront(4)    Full → False    -      -     -
 * getRear()         Returns 2
 * isFull()          Returns true
 * deleteLast()      [1, _, 3]       2      0     2
 * insertFront(4)    [1, 4, 3]       1      0     3
 * getFront()        Returns 4
 *
 * Alternative Approach: Using a Doubly Linked List
 * - Instead of using an array, we can use a doubly linked list, where:
 *   - head points to the front
 *   - tail points to the rear
 * - Each node contains:
 *   - val
 *   - next (points to next node)
 *   - prev (points to previous node)
 *
 * Pros of Linked List Approach:
 * - Dynamic size (no fixed k)
 * - No need for circular indexing
 * - Easier to implement
 *
 * Cons:
 * - Uses extra memory for pointers (next and prev)
 *
 * Final Thoughts:
 * - The array-based approach is more efficient because it uses O(1) space and constant-time operations.
 * - The linked list approach is more flexible but less cache-friendly.
 * ✅ For interview purposes, use the array-based approach unless dynamic resizing is explicitly required.
 */
class D_B_CircularDeque {

    private int[] deque;
    private int front, rear, size, capacity;

    public D_B_CircularDeque(int k) {
        this.capacity = k;
        this.deque = new int[k];
        this.front = 0; // Always points to the first element
        this.rear = -1; // Last inserted element
        this.size = 0;
    }

    /** Inserts an element at the front. Returns true if successful. */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        front = (front - 1 + capacity) % capacity; // Move front backward
        deque[front] = value;
        if (size == 0) rear = front; // If first element, sync rear with front, Tricky
        size++;
        return true;
    }

    /** Inserts an element at the rear. Returns true if successful. */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        rear = (rear + 1) % capacity; // Move rear forward
        deque[rear] = value;
        if (size == 0) front = rear; // If first element, sync front with rear tricky
        size++;
        return true;
    }

    /** Deletes an element from the front. Returns true if successful. */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        front = (front + 1) % capacity; // Move front forward
        size--;
        return true;
    }

    /** Deletes an element from the rear. Returns true if successful. */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        rear = (rear - 1 + capacity) % capacity; // Move rear backward
        size--;
        return true;
    }

    /** Gets the front item. Returns -1 if deque is empty. */
    public int getFront() {
        return isEmpty() ? -1 : deque[front];
    }

    /** Gets the last item. Returns -1 if deque is empty. */
    public int getRear() {
        return isEmpty() ? -1 : deque[rear];
    }

    /** Checks if the deque is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks if the deque is full. */
    public boolean isFull() {
        return size == capacity;
    }


    public static void main(String[] args) {
        D_B_CircularDeque deque = new D_B_CircularDeque(3);
        System.out.println(deque.insertLast(1));  // true
        System.out.println(deque.insertLast(2));  // true
        System.out.println(deque.insertFront(3)); // true
        System.out.println(deque.insertFront(4)); // false (full)
        System.out.println(deque.getRear());      // 2
        System.out.println(deque.isFull());       // true
        System.out.println(deque.deleteLast());   // true
        System.out.println(deque.insertFront(4)); // true
        System.out.println(deque.getFront());     // 4
    }
}
