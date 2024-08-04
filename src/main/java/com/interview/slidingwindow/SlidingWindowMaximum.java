package com.interview.slidingwindow;

import java.util.*;

/*
 * https://leetcode.com/problems/sliding-window-maximum/
 * Category: Hard, Tricky, Must Do, Sliding window
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

public class SlidingWindowMaximum {

    /*
    Let's start with the brute force approach to solve the "Sliding Window Maximum" problem, then build towards the optimized solution.

    Brute Force Approach
    In the brute force approach, we can slide the window across the array and find the maximum value within each window. This approach involves nested loops:

    Outer Loop: Slides the window from the beginning to the end of the array.
    Inner Loop: Finds the maximum value within the current window.
    Here’s the brute force code:

    Memory updated
    Let's start with the brute force approach to solve the "Sliding Window Maximum" problem, then build towards the optimized solution.

    Brute Force Approach
    In the brute force approach, we can slide the window across the array and find the maximum value within each window. This approach involves nested loops:

    Outer Loop: Slides the window from the beginning to the end of the array.
    Inner Loop: Finds the maximum value within the current window.
    Here’s the brute force code:

    Explanation of Brute Force Approach:
    Initialization:

    Create an array result to store the maximums.
    Loop through the array from the start to the point where a full window can still fit (n - k + 1).
    Find Maximum in Each Window:

    For each position of the window, find the maximum element by iterating through the window size k.
    Store the maximum in the result array.
     */

    public int[] maxSlidingWindowBruteForce(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            result[i] = max;
        }
        return result;
    }

    /*
    Building the Optimized Approach
    The brute force approach has a time complexity of
    𝑂
    (
    𝑛
    ×
    𝑘
    )
    O(n×k), where
    𝑛
    n is the length of the array and
    𝑘
    k is the window size. This is inefficient for large arrays.

    To optimize, we use a deque to keep track of the indices of useful elements within the current window:

    Deque: A double-ended queue to store indices. The indices in the deque are maintained in such a way that the corresponding values are in decreasing order.
    Sliding Window: As the window slides, maintain the deque and extract the maximum for the current window efficiently.
    Optimized Code Explanation:
    Here's the optimized code with detailed comments:

    Detailed Steps of Optimized Code:
    Initialization:

    list (deque) to store indices.
    result array to store maximums.
    First Window:

    For the first k elements, maintain the deque such that the largest element’s index is at the front.
    Remove indices of elements that are less than the current element from the back of the deque.
    Process the Rest of the Array:

    Slide the window one element at a time.
    Remove elements not in the current window from the front of the deque.
    Maintain the decreasing order in the deque by removing elements less than the current element from the back.
    Add the current element's index to the back of the deque.
    The maximum of the current window is at the front of the deque and is added to the result array.
    Return the Result: Finally, return the result array containing the maximums for each sliding window.

    This approach ensures each element is added and removed from the deque at most once, resulting in an efficient time complexity of
    𝑂
    (
    𝑛
    )
    O(n).
     */
    public int[] maxSlidingWindow(int[] array, int k) {
        // A deque to store indices of array elements
        LinkedList<Integer> list = new LinkedList<>();
        // Result array to store maximums of each window
        int[] result = new int[array.length - k + 1];

        // Initialize the deque for the first window
        for (int i = 0; i < k; i++) {
            // Remove elements from the deque that are less than the current element
            while (!list.isEmpty() && array[list.getLast()] < array[i]) {
                list.removeLast();
            }
            // Add the current element's index at the back
            list.addLast(i);
        }

        // Index to track the position in result array
        int resultIndex = 0;
        // The first element of deque is the max for the first window
        result[resultIndex++] = array[list.getFirst()];

        // Process the rest of the array
        for (int i = k; i < array.length; i++) {
            // Remove elements not in the current window
            while (!list.isEmpty() && list.getFirst() < (i - k + 1)) {
                list.removeFirst();
            }

            // Remove elements from the deque that are less than the current element
            while (!list.isEmpty() && array[list.getLast()] < array[i]) {
                list.removeLast();
            }

            // Add the current element's index at the back
            list.addLast(i);
            // The first element of deque is the max for the current window
            result[resultIndex++] = array[list.getFirst()];
        }

        return result;
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

    public int[] minSlidingWindow(int[] array, int k) {
        // A deque to store indices of array elements
        LinkedList<Integer> list = new LinkedList<>();
        // Result array to store minimums of each window
        int[] result = new int[array.length - k + 1];

        // Initialize the deque for the first window
        for (int i = 0; i < k; i++) {
            // Remove elements from the deque that are greater than the current element
            while (!list.isEmpty() && array[list.getLast()] > array[i]) {
                list.removeLast();
            }
            // Add the current element's index at the back
            list.addLast(i);
        }

        // Index to track the position in result array
        int resultIndex = 0;
        // The first element of deque is the min for the first window
        result[resultIndex++] = array[list.getFirst()];

        // Process the rest of the array
        for (int i = k; i < array.length; i++) {
            // Remove elements not in the current window
            while (!list.isEmpty() && list.getFirst() < (i - k + 1)) {
                list.removeFirst();
            }

            // Remove elements from the deque that are greater than the current element
            while (!list.isEmpty() && array[list.getLast()] > array[i]) {
                list.removeLast();
            }

            // Add the current element's index at the back
            list.addLast(i);
            // The first element of deque is the min for the current window
            result[resultIndex++] = array[list.getFirst()];
        }

        return result;
    }

    
    public static void main(String[] args) {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums1, k1))); // Output: [3, 3, 5, 5, 6, 7]

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums2, k2))); // Output: [1]
        
    }
    
}
