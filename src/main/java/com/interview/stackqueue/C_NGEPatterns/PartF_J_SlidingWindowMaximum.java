package com.interview.stackqueue.C_NGEPatterns;

import java.util.*;

/*
 * https://leetcode.com/problems/sliding-window-maximum/
 * https://www.youtube.com/watch?v=NwBvene4Imo&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=16
 * Category: Hard, Tricky, Must Do, Sliding window, monotonic stack problem so it is kept under stack package
 * Related: https://leetcode.com/problems/minimum-window-substring/ Hard
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/ Medium
 * https://leetcode.com/problems/paint-house-ii/ Hard
 * https://leetcode.com/problems/jump-game-vi/ Medium
 * 
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]
Example 3:

Input: nums = [1,-1], k = 1
Output: [1,-1]
Example 4:

Input: nums = [9,11], k = 2
Output: [11]
Example 5:

Input: nums = [4,-2], k = 2
Output: [4]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
Accepted
487,516
Submissions
1,063,336
 * 
 */

public class PartF_J_SlidingWindowMaximum {

    /*
            1. Brute Force Approach:
        The brute force approach involves sliding the window over the array and calculating the maximum value for each window. This solution is easy to understand but inefficient for larger inputs.
        Time Complexity:
        O(n * k): For each window, we are iterating over k elements, leading to a time complexity of O(n * k).
        Space Complexity:
        O(1): We only use extra space for the result array.
     */
    public static class SlidingWindowMaximumBruitforce {
        public static int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] result = new int[n - k + 1];

            // Iterate over each window
            for (int i = 0; i <= n - k; i++) {
                int max = nums[i];
                // Find the maximum in the current window
                for (int j = i; j < i + k; j++) {
                    max = Math.max(max, nums[j]);
                }
                result[i] = max;
            }
            return result;
        }
    }

    /*
    2. Optimized Approach: Using a Deque
A more efficient solution uses a deque (double-ended queue) to keep track of indices of the array elements in the current window. The deque helps maintain the maximum element at the front of the window and removes elements from the back that are smaller than the current element.
     */

    /*
        Explanation:
        Deque Maintenance:

        The deque stores indices of elements in a way that the element at the front of the deque is always the maximum for the current window.
        Before adding a new element, we remove all elements from the back of the deque that are smaller than the current element (nums[i]) because they cannot be the maximum for any upcoming windows.
        We also remove elements from the front of the deque if they are out of bounds for the current window.
        Result Construction:

        Once the window size reaches k, we start adding the maximum element (deque front) to the result array.
        Time Complexity:
        O(n): Each element is added and removed from the deque at most once, making the time complexity linear.
        Space Complexity:
        O(k): The deque holds at most k elements at any given time

        Key Differences:
        Brute Force: Simple to implement, but time-consuming (O(n * k)).
        Optimized Using Deque: Efficient (O(n)), leveraging the deque to manage window elements dynamically and compute the maximum in constant time for each window.
        Conclusion:
        Brute Force is useful for small inputs and understanding the problem.
        Deque-based approach is the optimal solution, especially for larger inputs where performance matters.
     */

    public static class SlidingWindowMaximumBetter {
        public static int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || k <= 0) return new int[0];

            int n = nums.length;
            int[] result = new int[n - k + 1]; // Output array
            Deque<Integer> deque = new LinkedList<>(); // Stores indices

            for (int i = 0; i < n; i++) {
                // Remove elements that are out of this window
                while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                    deque.pollFirst();
                }

                // Remove elements that are smaller than the current one(maintain decreasing order)
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.pollLast();
                }

                // Add the current element index
                deque.offerLast(i);

                // Store the maximum for the first valid window
                if (i >= k - 1) {
                    result[i - k + 1] = nums[deque.peekFirst()];
                }
            }
            return result;
        }
    }
    /*
    Derived question: To adapt the "Sliding Window Maximum" problem to find the minimum values instead, we can use the same logic with a slight modification. Instead of maintaining a deque that keeps track of the largest elements, we'll maintain it to keep track of the smallest elements.

    To adapt the "Sliding Window Maximum" problem to find the minimum values instead, we can use the same logic with a slight modification. Instead of maintaining a deque that keeps track of the largest elements, we'll maintain it to keep track of the smallest elements.

    Here is the code implementing this logic:
    Explanation:
    Initialization:

    list (deque) to store indices.
    result array to store minimums.
    First Window:

    For the first k elements, maintain the deque such that the smallest element’s index is at the front.
    Remove indices of elements that are greater than the current element from the back of the deque.
    Process the Rest of the Array:

    Slide the window one element at a time.
    Remove elements not in the current window from the front of the deque.
    Maintain the increasing order in the deque by removing elements greater than the current element from the back.
    Add the current element's index to the back of the deque.
    The minimum of the current window is at the front of the deque and is added to the result array.
    Return the Result: Finally, return the result array containing the minimums for each sliding window.

    This approach ensures each element is added and removed from the deque at most once, resulting in an efficient time complexity of
    𝑂
    (
    𝑛
    )
    O(n).
     */

    public int[] minSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1]; // Output array
        Deque<Integer> deque = new LinkedList<>(); // Stores indices

        for (int i = 0; i < n; i++) {
            // Remove elements that are out of this window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove elements that are greater than the current one (maintain increasing order)
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                deque.pollLast();
            }

            // Add the current element index
            deque.offerLast(i);

            // Store the minimum for the first valid window
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    
    public static void main(String[] args) {
        PartF_J_SlidingWindowMaximum solution = new PartF_J_SlidingWindowMaximum();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println(Arrays.toString(SlidingWindowMaximumBruitforce.maxSlidingWindow(nums1, k1))); // Output: [3, 3, 5, 5, 6, 7]

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println(Arrays.toString(SlidingWindowMaximumBetter.maxSlidingWindow(nums2, k2))); // Output: [1]
        
    }
    
}
