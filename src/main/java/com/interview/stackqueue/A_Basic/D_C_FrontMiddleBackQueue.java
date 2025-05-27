package com.interview.stackqueue.A_Basic;

import java.util.Deque;
import java.util.LinkedList;

/*
 * LeetCode 1670: Design Front Middle Back Queue
 * 🔗 Problem Link: https://leetcode.com/problems/design-front-middle-back-queue/
 * 📌 Category: Medium, Must Do
 *
 * 💡 **Core Idea**
 * The queue is implemented using **two deques**:
 * - `left` → Stores the first half of elements (including the middle when odd).
 * - `right` → Stores the second half of elements.
 *
 * 🔹 **Push Operations:**
 * - `pushFront(val)` → Insert at the **front** of `left`.
 * - `pushMiddle(val)` → Insert in `left` (shifting elements if needed).
 * - `pushBack(val)` → Insert at the **end** of `right`.
 *
 * 🔹 **Pop Operations:**
 * - `popFront()` → Remove from the **front** of `left`.
 * - `popMiddle()` → Remove the **last element** from `left`.
 * - `popBack()` → Remove from `right` (or from `left` if `right` is empty).
 *
 * 🔄 **Balancing Rule:**
 * - If `left.size() > right.size() + 1`, move the **last element** from `left` to `right`.
 * - If `left.size() < right.size()`, move the **first element** from `right` to `left`.
 * - Ensures `left` is always **equal to or one more** than `right`.
 *
 * 🚀 **Complexity Analysis**
 * | Operation      | Time Complexity | Explanation |
 * |---------------|----------------|-------------|
 * | `pushFront()` | O(1)           | Insert at the front of `left`. |
 * | `pushMiddle()` | O(1)           | Insert at the end of `left`, balancing at most 1 move. |
 * | `pushBack()`  | O(1)           | Insert at the back of `right`. |
 * | `popFront()`  | O(1)           | Remove from the front of `left`, balance at most 1 move. |
 * | `popMiddle()` | O(1)           | Remove the last element of `left`, balance at most 1 move. |
 * | `popBack()`   | O(1)           | Remove from `right`, or last of `left` if `right` is empty. |
 * ✅ **Total Complexity: O(1) for all operations** 🚀
 *
 * 📊 **Tabular Explanation of Operations**
 * | Step | Operation        | Left Deque | Right Deque | Output | Explanation |
 * |------|----------------|------------|-------------|--------|-------------|
 * | 1️⃣  | `pushFront(1)`  | `[1]`      | `[]`        | `-`    | Insert 1 at front of `left`. |
 * | 2️⃣  | `pushBack(2)`   | `[1]`      | `[2]`       | `-`    | Insert 2 at back of `right`. |
 * | 3️⃣  | `pushMiddle(3)` | `[1, 3]`   | `[2]`       | `-`    | Insert 3 at middle (left last), balance is fine. |
 * | 4️⃣  | `pushMiddle(4)` | `[1, 4]`   | `[3, 2]`    | `-`    | Insert 4 at middle (left last), move 3 to `right` for balance. |
 * | 5️⃣  | `popFront()`    | `[4, 3]`   | `[2]`       | `1`    | Remove front `1` from `left`, move `3` from `right` to `left`. |
 * | 6️⃣  | `popMiddle()`   | `[4]`      | `[2]`       | `3`    | Remove middle (last of `left`, which is `3`). |
 * | 7️⃣  | `popMiddle()`   | `[2]`      | `[]`        | `4`    | Remove middle (last of `left`, which is `4`), move `2` to `left`. |
 * | 8️⃣  | `popBack()`     | `[]`       | `[]`        | `2`    | Remove last from `left` (`2`). |
 * | 9️⃣  | `popFront()`    | `[]`       | `[]`        | `-1`   | Queue is empty, return `-1`. |
 *
 * 🔑 **Key Observations**
 * - `left` always contains the **middle element** when the size is odd.
 * - `right` stores the **second half** and helps balance operations.
 * - **Balancing ensures** `left` is always equal to or **one more** than `right`.
 * - All operations remain **O(1)** due to **deque efficiency**.
 *
 * 🎯 **Final Takeaways**
 * - Using **two deques** ensures fast access to the **front, middle, and back**.
 * - **Middle is always in `left`**, ensuring easy access.
 * - **Balancing ensures** `left` is equal to or **one more** than `right`.
 * - **Passes all edge cases on LeetCode**.
 * - This is the **optimal and correct** implementation. 🚀
 */
class D_C_FrontMiddleBackQueue {
    private Deque<Integer> left;
    private Deque<Integer> right;

    public D_C_FrontMiddleBackQueue() {
        left = new LinkedList<>();
        right = new LinkedList<>();
    }

    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        if (left.size() > right.size()) {
            right.addFirst(left.pollLast()); // Move last of left to right
        }
        left.addLast(val); // Insert into left
        balance();
    }

    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }

    public int popFront() {
        if (left.isEmpty()) return -1; // Empty queue
        int val = left.pollFirst();
        balance();
        return val;
    }

    public int popMiddle() {
        if (left.isEmpty()) return -1; // Empty queue
        int val = left.pollLast(); // Always remove last from left
        balance();
        return val;
    }

    public int popBack() {
        if (right.isEmpty()) {
            if (left.isEmpty()) return -1; // Empty queue
            return left.pollLast();
        }
        int val = right.pollLast();
        balance();
        return val;
    }

    private void balance() {
        if (left.size() > right.size() + 1) {
            right.addFirst(left.pollLast()); // Move last of left to right
        } else if (left.size() < right.size()) {
            left.addLast(right.pollFirst()); // Move first of right to left
        }
    }
}
