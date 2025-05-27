package com.interview.stackqueue.C_NGEPatterns;

import java.util.Stack;

/*
 🔗 Problem:
 https://leetcode.com/problems/sum-of-subarray-minimums/
 🧠 Category: Medium, Monotonic Stack
 🎥 Reference:
 https://www.youtube.com/watch?v=v0e8p9JCgRc&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=9

 Related Problems:
 - https://leetcode.com/problems/sum-of-subarray-ranges/ (Medium)
 - https://leetcode.com/problems/sum-of-total-strength-of-wizards/ (Hard)

 ✅ Description:
 Given an array of integers arr, find the sum of min(b),
 where b ranges over every (contiguous) subarray of arr.
 Return the answer modulo 10^9 + 7.

 🧪 Example 1:
 Input: arr = [3,1,2,4]
 Output: 17

 🧪 Example 2:
 Input: arr = [11,81,94,43,3]
 Output: 444

 ✅ Constraints:
 1 <= arr.length <= 3 * 10^4
 1 <= arr[i] <= 3 * 10^4
*/
public class PartF_E_SumofSubarrayMinimum {

    public static class SubarrayMinimumBruitforce {
        /*
        Explanation:
            For each subarray starting at index i, we iterate through the array, updating the minimum element as we extend the subarray to the right.
            We add the current minimum to the final result for every subarray.
            Time Complexity:
            O(n^2): For each starting index i, we iterate through the rest of the array to find the minimum and add it to the sum.
            Space Complexity:
            O(1): No additional data structures other than a few variables are used.
         */
        public int sumSubarrayMins(int[] arr) {
            int n = arr.length;
            int mod = 1_000_000_007;
            int sum = 0;

            // For each starting index of the subarray
            for (int i = 0; i < n; i++) {
                int min = arr[i]; // Initialize the minimum as the starting element
                // Extend the subarray and keep track of the minimum
                for (int j = i; j < n; j++) {
                    min = Math.min(min, arr[j]);
                    sum = (sum + min) % mod;
                }
            }

            return sum;
        }

        public static void main(String[] args) {
            int[] arr = {3, 1, 2, 4};
            SubarrayMinimumBruitforce solution = new SubarrayMinimumBruitforce();
            System.out.println(solution.sumSubarrayMins(arr)); // Output: 17
        }
    }

    /*
    Optimal Approach Using Monotonic Stack
        Approach:
        This approach makes use of a monotonic stack to efficiently calculate the number of subarrays where each element is the minimum.

        Key Idea:
        For each element, determine how many subarrays it can be the minimum for, both in the left (previous smaller element) and right (next smaller element) directions.

        Steps:
        Use two arrays to track:
        left[i]: How many subarrays can element arr[i] be the minimum for, towards the left.
        right[i]: How many subarrays can element arr[i] be the minimum for, towards the right.
        Use two monotonic stacks to calculate the number of subarrays.
        One stack for finding the Previous Less Element (PLE) on the left side.
        Another stack for finding the Next Less Element (NLE) on the right side.
        The contribution of arr[i] is arr[i] * left[i] * right[i], and the final sum is the total of these contributions.
        TC: 5N SC: 5N
     */

    public static class SubarrayMinimumOptimal {
        int[] findNSE(int[] arr) {
            int n = arr.length;
            int[] right = new int[n];

            // Stack to find Next Less Element (NLE)
            Stack<Integer> stack = new Stack<>();

            // Calculate right array
            for (int i = n - 1; i >= 0; i--) {
                // Count number of elements greater than arr[i] on the right
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                    stack.pop();
                }
                // If stack is empty, it means there are no smaller elements to the right
                right[i] = stack.isEmpty() ? n: stack.peek();
                stack.push(i);
            }
            return right;
        }

        int[] findPSEE(int[] arr) {
            // Arrays to store count of subarrays where arr[i] is minimum towards left and right
            int n = arr.length;
            int[] left = new int[n];

            // Stack to find Previous Less Element (PLEE)
            Stack<Integer> stack = new Stack<>();

            // Calculate left array
            for (int i = 0; i < n; i++) {
                // Count number of elements greater than arr[i] on the left
                while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                    stack.pop();
                }

                left[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            return left;
        }
        public int sumSubarrayMins(int[] arr) {
            int n = arr.length;
            int mod = 1_000_000_007;

            int[] nse = findNSE(arr);
            int[] psee = findPSEE(arr);

            // Calculate the sum of minimums
            long sum = 0;
            for (int i = 0; i < n; i++) {
                long leftCount = i - psee[i];  // number of subarrays ending at i where arr[i] is the minimum
                long rightCount = nse[i] - i;  // number of subarrays starting at i where arr[i] is the minimum

                // Contribution of arr[i] in all such subarrays
                sum = (sum + (arr[i] * leftCount * rightCount) % mod) % mod;
            }
            return (int) sum;
        }

        public static void main(String[] args) {
            int[] arr = {3, 1, 2, 4};
            SubarrayMinimumOptimal solution = new SubarrayMinimumOptimal();
            System.out.println(solution.sumSubarrayMins(arr)); // Output: 17
        }
    }
}
