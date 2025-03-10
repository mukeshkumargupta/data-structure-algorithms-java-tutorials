package com.interview.sort;

import java.util.PriorityQueue;

/*
Approach 1: Using a Min Heap (Optimal for K largest)

Time Complexity: O(N log K)
Space Complexity: O(K) (heap storage)

Steps:
1. Use a Min Heap of size K to store the largest K elements.
2. Iterate through the array and maintain the heap.
3. The heap's top will always be the Kth largest element after processing.
4. Extract all heap elements to get the result.
*/
public class KLargestElements {
    public static int[] findKLargest(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min Heap

        for (int num : arr) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the smallest element
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3;
        int[] result = findKLargest(arr, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
