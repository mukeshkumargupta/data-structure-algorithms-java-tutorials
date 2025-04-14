package com.interview.heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
https://leetcode.com/problems/smallest-number-in-infinite-set/description/?envType=study-plan-v2&envId=leetcode-75
Category: Medium, top75, tricky, FAANG, Microsoft
https://www.youtube.com/watch?v=b_Cmig4l6_c&t=3s
Related: https://leetcode.com/problems/first-missing-positive/
You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].

Implement the SmallestInfiniteSet class:

SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
int popSmallest() Removes and returns the smallest integer contained in the infinite set.
void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.


Example 1:

Input
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
Output
[null, null, 1, 2, 3, null, 1, 4, 5]

Explanation
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                   // is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.


Constraints:

1 <= num <= 1000
At most 1000 calls will be made in total to popSmallest and addBack.
 */
public class SmallestNumberinInfiniteSet {
    /*
    🔍 2. Key Observations and Design

    🧠 Idea:
    Instead of actually storing the infinite set, we simulate it by:

    1. Keeping track of the next smallest number not yet popped (`current` pointer).
    2. Maintaining a min-heap (PriorityQueue) to store numbers manually added back.
    3. Using a HashSet to avoid duplicates in the min-heap.

    📘 Example Walkthrough:
    SmallestInfiniteSet obj = new SmallestInfiniteSet();
    obj.popSmallest();  // returns 1
    obj.popSmallest();  // returns 2
    obj.addBack(1);     // adds 1 back
    obj.popSmallest();  // returns 1 again

    ⏱️ Time and Space Complexity:

    popSmallest():
    - Time: O(log k) — if popping from heap of size k, else O(1) if just using current.
    - Space: O(k) — where k is the number of elements added back.

    addBack(num):
    - Time: O(log k) — to insert into min-heap.
    - Space: O(k) — for heap and set.

    🔄 Summary:

    Operation       Time        Space
    -----------     --------    --------
    popSmallest     O(log k)    O(k)
    addBack         O(log k)    O(k)
    */
    private int current;
    private PriorityQueue<Integer> minHeap;
    private Set<Integer> addedBack;

    public SmallestNumberinInfiniteSet() {
        current = 1;  // Start from 1
        minHeap = new PriorityQueue<>();
        addedBack = new HashSet<>();
    }

    public int popSmallest() {
        if (!minHeap.isEmpty()) {
            int smallest = minHeap.poll();
            addedBack.remove(smallest);
            return smallest;
        }

        return current++;// means return current and increase for next time to get min
    }

    public void addBack(int num) {
        if (num < current && !addedBack.contains(num)) {//interested to add smaller one because bigger one there is no advantage to add
            minHeap.offer(num);//Think like maintaining heap to get min out of added all value less than current so u can get min
            addedBack.add(num);
        }
    }
}
