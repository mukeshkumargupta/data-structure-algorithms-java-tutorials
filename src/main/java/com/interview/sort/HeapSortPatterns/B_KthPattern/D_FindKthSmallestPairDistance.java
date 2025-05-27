package com.interview.sort.HeapSortPatterns.B_KthPattern;

import java.util.*;

/*
https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/
Category: Hard, Tricky, VVImp
Related:
https://leetcode.com/problems/find-k-pairs-with-smallest-sums/ Medium
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/ Medium
https://leetcode.com/problems/find-k-closest-elements/ Medium
https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/ Hard
https://leetcode.com/problems/k-th-smallest-prime-fraction/ Medium
https://leetcode.com/problems/find-the-median-of-the-uniqueness-array/ Hard
https://leetcode.com/problems/maximize-score-of-numbers-in-ranges/ Medium
The distance of a pair of integers a and b is defined as the absolute difference between a and b.

Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.



Example 1:

Input: nums = [1,3,1], k = 1
Output: 0
Explanation: Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Example 2:

Input: nums = [1,1,1], k = 2
Output: 0
Example 3:

Input: nums = [1,6,1], k = 3
Output: 5


Constraints:

n == nums.length
2 <= n <= 104
0 <= nums[i] <= 106
1 <= k <= n * (n - 1) / 2
 */
public class D_FindKthSmallestPairDistance {

    /*
    🧨 1. Brute Force
Generate all pairs and compute their distances, then sort.
⏱️ Time & Space Complexity:
Time: O(n² log n²) → due to sorting all pairs

Space: O(n²)
     */
    private static class BruitForce {
        public int smallestDistancePair(int[] nums, int k) {
            List<Integer> distances = new ArrayList<>();
            for (int i = 0; i < nums.length; ++i) {
                for (int j = i + 1; j < nums.length; ++j) {
                    distances.add(Math.abs(nums[i] - nums[j]));
                }
            }
            Collections.sort(distances);
            return distances.get(k - 1);
        }
    }

    /*
    🧠 Idea – Max Heap of Size k:
Generate all unique pairs (i, j) such that i < j.

Compute their absolute difference |nums[i] - nums[j]|.

Use a max heap of size k to keep the k smallest distances.

If the heap exceeds size k, remove the largest element (top of max heap).

After processing all pairs, the top of the max heap is the k-th smallest distance.
     */
    private static class Better {//It will not run and u have to apply binary search just find intuitive binary search implementation
        public int smallestDistancePair(int[] nums, int k) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            int n = nums.length;

            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    int distance = Math.abs(nums[i] - nums[j]);

                    if (maxHeap.size() < k) {
                        maxHeap.offer(distance);
                    } else if (distance < maxHeap.peek()) {
                        maxHeap.poll(); // remove largest
                        maxHeap.offer(distance);
                    }
                }
            }

            return maxHeap.peek(); // the k-th smallest
        }
    }

    private static class BetterButNotEfficient {
        /*
        Problems with the Current Approach:
        Time Complexity:

        The nested loop runs for all pairs (i, j) where i < j, resulting in O(n^2) iterations (where n is the length of the input array nums).

        For each iteration, the maxHeap.add() operation takes O(log k) time (where k is the number of elements in the heap).

        However, when you remove the largest element using maxHeap.remove(), it can take O(n) time because remove() in a priority queue involves searching for and removing an arbitrary element, not just the top element.

        The heap operation in each iteration would have been better if you use maxHeap.poll(), which is optimized for removing the top element of the heap (i.e., the largest in the case of a max-heap). The current remove() method is inefficient and leads to poor performance.

        Inefficient Heap Size Check:

        You first add the new element to the heap and only check if the heap size exceeds k afterward. This means that when the heap size exceeds k, you perform an additional removal operation which could be optimized by only adding elements that need to be kept.

        Heap Overflow:

        After adding all elements from the pairwise comparison, the heap will contain more than k elements, and you are not always efficiently maintaining it to contain the smallest k distances.

        Suggested Fix:
        Use maxHeap.poll() instead of maxHeap.remove(), which is optimized to remove the top element in O(log k) time.

        Optimize the heap management so that you only add an element if it is worth keeping (i.e., if the heap has fewer than k elements).

        Return the heap's top element once you have processed all pairs.
         */
        public int smallestDistancePair(int[] nums, int k) {

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a, b)-> {
                return b -a;
            });
            int len = nums.length;
            for (int i =0; i < len-1; i++) {
                for (int j= i+1; j < len; j++) {
                    maxHeap.add(Math.abs(nums[i] - nums[j]));
                    if (maxHeap.size() > k) {
                        maxHeap.remove();
                    }
                }
            }

            if (maxHeap.size() == k) {
                return maxHeap.peek();
            } else {
                return -1;
            }

        }
    }

    /*
    🚀 3. Optimal Approach — Binary Search on Distance
    Key Idea:
    Sort nums.

    The smallest distance is 0 (when duplicate elements exist), and the largest is max - min.

    Use binary search on answer space: For a candidate distance mid, count how many pairs have distance ≤ mid.

    If count ≥ k, search left; else search right.
    📊 Time and Space Complexity:
    Time: O(n log n + n log W)
    Where W = max(nums) - min(nums)

    O(n log n) to sort

    O(n log W) for binary search + two-pointer count

    Space: O(1) extra (in-place sort)

    ✅ Summary

    Approach	Time Complexity	Space Complexity	Suitable For
    Brute Force	O(n² log n²)	O(n²)	Tiny arrays
    Max Heap	O(n² log k)	O(k)	Still slow
    Binary Search	✅ O(n log n + n log W)	O(1)	✅ Efficient (Optimal)
     */
    private static class Optimal {
        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);
            int left = 0;
            int right = nums[nums.length - 1] - nums[0];

            while (left < right) {
                int mid = (left + right) / 2;
                int count = countPairs(nums, mid);

                if (count >= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        // Counts number of pairs with distance <= mid
        private int countPairs(int[] nums, int mid) {
            int count = 0;
            int left = 0;

            for (int right = 0; right < nums.length; ++right) {
                while (nums[right] - nums[left] > mid) {
                    left++;
                }
                count += right - left;
            }

            return count;
        }
    }
}
