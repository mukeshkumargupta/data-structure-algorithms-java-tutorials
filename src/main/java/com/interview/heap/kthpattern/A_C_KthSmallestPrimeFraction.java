package com.interview.heap.kthpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/k-th-smallest-prime-fraction/description/
Related:
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/ Medium
https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/ Hard
https://leetcode.com/problems/find-k-th-smallest-pair-distance/ Hard
You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.

For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].



Example 1:

Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
Example 2:

Input: arr = [1,7], k = 1
Output: [1,7]


Constraints:

2 <= arr.length <= 1000
1 <= arr[i] <= 3 * 104
arr[0] == 1
arr[i] is a prime number for i > 0.
All the numbers of arr are unique and sorted in strictly increasing order.
1 <= k <= arr.length * (arr.length - 1) / 2


Follow up: Can you solve the problem with better than O(n2) complexity?
 */
public class A_C_KthSmallestPrimeFraction {
    /*
    ✅ 1. Brute Force Approach
🔧 Idea:
Generate all pairs i < j, calculate the fractions.

Store them in a list and sort by their value.

Return the k-th smallest.

⏱️ Time Complexity: O(n^2 log n)
🧠 Space Complexity: O(n^2)
     */
    private static class BruitForce {
        public int[] kthSmallestPrimeFraction(int[] arr, int k) {
            List<int[]> fractions = new ArrayList<>();

            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    fractions.add(new int[]{arr[i], arr[j]});
                }
            }

            fractions.sort((a, b) -> Double.compare((double)a[0] / a[1], (double)b[0] / b[1]));

            return fractions.get(k - 1);
        }
    }

    /*
    ✅ 2. Better Approach — Min Heap (Priority Queue)
🔧 Idea:
Use a min-heap to generate next minimum fraction in each iteration:

Start with (arr[0]/arr[n-1]) to (arr[n-2]/arr[n-1]).

Pop the smallest, and push the next fraction with the same numerator and smaller denominator.

⏱️ Time Complexity: O(k log n)
🧠 Space Complexity: O(n)

     */
    private static class Better {
        /*
        ✅ Why Min Heap is Better for This Problem (K-th Smallest Prime Fraction):

        🌟 1. Natural Fit for Finding Smallest Elements
           - We're trying to find the K-th smallest fraction.
           - Min Heap keeps the smallest element at the top, making it efficient to fetch the next smallest pair.

        📈 2. Controlled Growth
           - We don't generate all possible fractions (which would be O(n²)).
           - Instead, we:
             a. Start with the smallest possible values.
             b. Progress in a greedy and incremental manner by moving to the next smallest fraction with the same numerator
                or decrementing the denominator.

        🔁 3. Efficient Iteration
           - We only process at most K fractions, keeping heap operations efficient at O(log k).

        ❌ Why Max Heap Is Not a Good Fit Here:
           - Max Heap is used when we want to keep track of the top K largest elements,
             or want to discard the largest while retaining smaller ones.
           - In this problem, you don’t know all the candidate fractions up front (we're generating them on-the-fly).
           - Max Heap would require:
             a. Generating all O(n²) fractions.
             b. Storing at most K in the heap and comparing each.
           - This would become similar to brute force + heap — not optimal.

        ⚖️ Summary:

        | Criteria        | Min Heap                              | Max Heap                                |
        |----------------|----------------------------------------|------------------------------------------|
        | Suitability     | ✅ Best fit for finding smallest values| ❌ Poor fit for incremental smallest search|
        | Heap Operations | Efficient (Push/Pop smallest)         | Inefficient unless you precompute all    |
        | Time Complexity | O(k log n)                            | O(n² log k)                              |
        | Space Efficiency| O(n) heap                             | O(k) heap but more input to scan         |

        🧠 Verdict:
        ✅ Use Min Heap for this problem.
        It’s clean, efficient, and tailored to the nature of the problem.

        ----------------------------------------------------------

        👤 You said:
        > i think you are telling wrong — for getting k-th largest element we use minHeap and k-th smallest we use max heap, am i correct?

        🧠 ChatGPT said:
        > You're absolutely right in general — and your understanding is solid!
        > Heap choice may differ depending on the problem structure — especially in special cases like K-th Smallest Prime Fraction.

        ✅ General Rule of Thumb:

        | Goal                 | Heap Type             | Why                                      |
        |----------------------|------------------------|-------------------------------------------|
        | K-th largest element | Min Heap of size K     | Keep K largest seen so far; smallest at top |
        | K-th smallest element| Max Heap of size K     | Keep K smallest seen so far; largest at top |

        🔍 Why the Min Heap Is Used in K-th Smallest Prime Fraction:
        - Instead of scanning all n² pairs, we use a greedy heap-based approach.
        - Each heap element is a pair (i, j) representing arr[i] / arr[j].
        - The heap explores the next smallest fraction on demand, like a 2D matrix traversal.

        | Problem Type                          | Heap Used             | Why                                           |
        |--------------------------------------|------------------------|-----------------------------------------------|
        | K-th largest (unsorted array)        | Min Heap (size K)      | Track top K largest seen so far               |
        | K-th smallest (unsorted array)       | Max Heap (size K)      | Track top K smallest seen so far              |
        | K-th smallest fraction (sorted array)| Min Heap (custom comp) | Efficiently explore next smallest fraction    |

        🧠 TL;DR:
        ✅ You're correct in general.
        📌 This problem is a special case — and Min Heap is better due to the greedy, incremental structure of valid pairs.
        */
        public int[] kthSmallestPrimeFraction(int[] arr, int k) {
            int n = arr.length;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                    arr[a[0]] * arr[b[1]] - arr[b[0]] * arr[a[1]]); // compare a/b and c/d

            // Initialize heap with fractions: arr[0]/arr[n-1], arr[1]/arr[n-1], ...
            for (int i = 0; i < n - 1; i++) {
                pq.offer(new int[]{i, n - 1});
            }

            // Extract the smallest pairs
            int[] result = new int[2];
            while (k-- > 0 && !pq.isEmpty()) {
                int[] frac = pq.poll();
                if ( k == 0) {
                    result[0] = frac[0]; result[1] = frac[1];
                    break;
                }
                int i = frac[0], j = frac[1];
                if (j - 1 > i) {//Tricky
                    pq.offer(new int[]{i, j - 1}); // move to next smaller denominator
                }
            }
            return new int[]{arr[result[0]], arr[result[1]]};

        }
    }

    /*
    ✅ 3. Optimal Approach — Binary Search on Fraction Value
🔧 Idea:
Binary search the value of the fraction (between 0 and 1):

For each mid-value, count how many fractions are ≤ mid.

Track the max fraction ≤ mid.

Use the count to decide to go left or right in binary search.
⏱️ Time Complexity: O(n log(1/ε)) ≈ O(n * 30)
🧠 Space Complexity: O(1)
🧠 Summary of Approaches

Approach	Time Complexity	Space Complexity	Notes
Brute Force	O(n² log n)	O(n²)	Easy to implement, inefficient
Min Heap	O(k log n)	O(n)	Efficient and clean
Binary Search	O(n log(1/ε))	O(1)	Most optimal, complex logic
     */
    private static class Optimal {//Binary search i need to understand intuitive way
        //Looks this pattern https://www.geeksforgeeks.org/n-th-root-number/ it is already calculating n-th root
        public int[] kthSmallestPrimeFraction(int[] arr, int k) {
            int n = arr.length;
            double low = 0.0, high = 1.0;
            int[] result = new int[2];

            while (high - low > 1e-9) {//this kind of pattern is there on binary search question as well
                double mid = (low + high) / 2.0;
                int count = 0;
                int i = -1;
                double maxFrac = 0.0;

                // Count fractions <= mid
                for (int j = 1; j < n; j++) {
                    while (i + 1 < j && arr[i + 1] <= mid * arr[j]) {
                        i++;
                    }

                    count += (i + 1);

                    if (i >= 0 && (double) arr[i] / arr[j] > maxFrac) {
                        maxFrac = (double) arr[i] / arr[j];
                        result[0] = arr[i];
                        result[1] = arr[j];
                    }
                }

                if (count < k) {
                    low = mid;
                } else {
                    high = mid;
                }
            }

            return result;
        }
    }

}

