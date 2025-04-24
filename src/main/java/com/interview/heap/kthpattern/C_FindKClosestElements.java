package com.interview.heap.kthpattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/find-k-closest-elements/description/
Category: Medium
Related:
https://leetcode.com/problems/guess-number-higher-or-lower/ Easy
https://leetcode.com/problems/guess-number-higher-or-lower-ii/ Medium
https://leetcode.com/problems/find-k-th-smallest-pair-distance/ Hard VVImp having many related questions
https://leetcode.com/problems/find-closest-number-to-zero/ Easy
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b


Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3

Output: [1,2,3,4]

Example 2:

Input: arr = [1,1,2,3,4,5], k = 4, x = -1

Output: [1,1,2,3]



Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
 */
public class C_FindKClosestElements {
    /*
    ✅ Approach 1: Brute Force + Sorting
    🔹 Idea:
    Calculate the absolute difference between each number and x.

    Sort based on the difference (and value in case of tie).

    Pick first k elements and sort them.
    ⏱️ Time Complexity:
O(n log n) for sorting.

📦 Space Complexity:
O(n) for temporary list.
     */
    private static class BruitForce {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            List<Integer> list = new ArrayList<>();
            for (int num : arr) {
                list.add(num);
            }
            list.sort((a, b) -> {
                int diff = Math.abs(a - x) - Math.abs(b - x);
                if (diff == 0) return a - b;
                return diff;
            });

            List<Integer> res = list.subList(0, k);
            Collections.sort(res);
            return res;
        }
    }

    /*
    ✅ Approach 2: Max Heap (Note min heap will not be efficient because u need to put all element in min heap and then k element will be pop so space will be high in case of min heap not efficient
🔹 Idea:
Use a max heap to store the closest k elements.

For each element, push into the heap based on the distance from x.

Maintain heap size k.
⏱️ Time Complexity:
O(n log k) for heap operations., here n insertion will happen so unneccessary insertion will happen

📦 Space Complexity:
O(k) for the heap.


     */
    private static class Better {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
                int diff = Math.abs(b - x) - Math.abs(a - x);
                if (diff == 0) return b - a;
                return diff;
            });

            for (int num : arr) {
                maxHeap.offer(num);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }

            List<Integer> res = new ArrayList<>(maxHeap);
            Collections.sort(res);
            return res;
        }
    }

    /*
    📊 Time & Space Complexity:
    Time Complexity: O(n log k)

    We process n elements.

    Each heap operation is log k.

    Space Complexity: O(k)

    The heap contains at most k elements.
     */
    private static class BetterSlightlyEfficient {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
                int diff = Math.abs(b - x) - Math.abs(a - x);
                if (diff == 0) return b - a; // Prefer smaller element
                return diff; // Max-heap based on distance
            });

            for (int num : arr) {
                if (maxHeap.size() < k) {
                    maxHeap.offer(num);
                } else {
                    int top = maxHeap.peek();
                    int currDiff = Math.abs(num - x);
                    int topDiff = Math.abs(top - x);

                    if (currDiff < topDiff || (currDiff == topDiff && num < top)) {//Avoid redundant element insertion
                        maxHeap.poll();
                        maxHeap.offer(num);
                    }
                    // else skip the element
                }
            }

            List<Integer> result = new ArrayList<>(maxHeap);
            Collections.sort(result);
            return result;
        }
    }

    /*
    📊 Time and Space Complexity
Time Complexity:

Heap construction: O(n log n) — inserting n elements into a min-heap

Extract k elements: O(k log n), since n element in heap and k times heapify, since heap size n so space is high

Final sort: O(k log k)

Total: O(n log n + k log k)

Space Complexity:

Heap stores n elements: O(n)

Result list stores k elements: O(k)

🚀 Summary
This approach is conceptually simpler but less efficient than the optimized binary search or sliding window methods.
 It's good for understanding the distance-based selection logic — and still works well for small arrays or interviews where clarity > performance.
     */
    private static class BetterUsingMinHeapNotEfficient {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            // Min Heap based on distance from x, with tie-breaker on value
            PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> {
                int diff = Math.abs(a - x) - Math.abs(b - x);
                if (diff == 0) return a - b; // smaller value comes first if tie
                return diff;
            });

            // Add all elements to the heap
            for (int num : arr) {
                minHeap.offer(num);
            }

            // Extract the k closest elements
            List<Integer> result = new ArrayList<>();
            while (k-- > 0 && !minHeap.isEmpty()) {
                result.add(minHeap.poll());
            }

            // Sort final list to satisfy "in ascending order" requirement
            Collections.sort(result);
            return result;
        }

        public static void main(String[] args) {
            BetterUsingMinHeapNotEfficient solution = new BetterUsingMinHeapNotEfficient();
            int[] arr = {1, 2, 3, 4, 5};
            int k = 4, x = 3;
            System.out.println(solution.findClosestElements(arr, k, x)); // Output: [1, 2, 3, 4]
        }
    }

    /*
    ✅ Approach 3: Binary Search (Optimal), Not readable find better readable code and standard binary search code
🔹 Idea:
Since array is sorted, we can use binary search to find the best window of size k.

We search for the left bound of the window using binary search.
⏱️ Time Complexity:
O(log(n - k) + k) → binary search + building the result.

📦 Space Complexity:
O(k) for result list.


     */
    private static class Optimal {//Not readable
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int left = 0;
            int right = arr.length - k;

            while (left < right) {
                int mid = (left + right) / 2;
                if (x - arr[mid] > arr[mid + k] - x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            List<Integer> res = new ArrayList<>();
            for (int i = left; i < left + k; i++) {
                res.add(arr[i]);
            }
            return res;
        }
    }
}
