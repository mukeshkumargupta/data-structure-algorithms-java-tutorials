package com.interview.heap;

import java.util.*;

/* Reference: https://leetcode.com/problems/find-median-from-val-stream
 * Category: Hard, Must Do, VVImp, Fundamental
 * Reference: https://www.youtube.com/watch?v=1LkOrc-Le-Y&t=1186s
 * Refere this explanation: https://chatgpt.com/c/65480b1f-e71d-4c68-9a48-f5926d8be71e
 */
public class FindMedianfromvalStream {
    // Max heap for the smaller half
    private PriorityQueue<Integer> maxHeap;
    // Min heap for the larger half
    private PriorityQueue<Integer> minHeap;

    /** Initialize the data structure here. */
    public FindMedianfromvalStream() {
        // Max-heap for the lower half of numbers
        maxHeap = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        // Min-heap for the upper half of numbers
        minHeap = new PriorityQueue<>((a, b) -> {
            return a - b;
        });
    }

    /** Adds a number into the data structure. */
    public void addNum(int num) {
        // Add to max heap first
        maxHeap.add(num);

        // Balance heaps
        minHeap.add(maxHeap.remove());

        // If min heap is larger, move the top element to max heap
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    /** Returns the median of all elements so far. */
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
