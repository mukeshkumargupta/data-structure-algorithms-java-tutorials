package com.interview.sort;

import java.util.*;
/*
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * Category: Easy, heap, Must Do
 */

public class KthLargestElementinaStream {
    /*
        ✅ Time and Space Complexity:
        Time Complexity:

        add() runs in O(log k) due to heap insertion/removal.

        Constructor processes n elements → O(n log k)

        Space Complexity: O(k) — the heap stores only k elements.
     */
    Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> {
        return a - b;
    });
    int kthLargest;
    
    public KthLargestElementinaStream(int k, int[] nums) {
        this.kthLargest = k;
        minHeap = new PriorityQueue<>();

        // Initialize the min heap with up to k elements
        for (int num : nums) {
            add(num);
        }
        
    }

    public int add(int val) {
        minHeap.offer(val);

        // Maintain only k largest elements in the heap
        if (minHeap.size() > kthLargest) {
            minHeap.poll();
        }

        // Top of the heap is the kth largest element
        return minHeap.peek();
    }
    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        KthLargestElementinaStream kthLargest = new KthLargestElementinaStream(3, nums);
        System.out.println(kthLargest.add(3));   // returns 4
        System.out.println(kthLargest.add(5));   // returns 5
        System.out.println(kthLargest.add(10));  // returns 5
        System.out.println(kthLargest.add(9));   // returns 8
        System.out.println(kthLargest.add(4));   // returns 8
    }

}
