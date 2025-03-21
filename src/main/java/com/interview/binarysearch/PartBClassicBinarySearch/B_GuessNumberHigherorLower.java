package com.interview.binarysearch.PartBClassicBinarySearch;

/*
 * https://leetcode.com/problems/guess-number-higher-or-lower/description/
 * Category: Easy
 *
 * Related Problems:
 * - https://leetcode.com/problems/first-bad-version/ (Easy)
 * - https://leetcode.com/problems/guess-number-higher-or-lower-ii/ (Medium)
 * - https://leetcode.com/problems/find-k-closest-elements/ (Medium)
 *
 * Problem Statement:
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I will tell you whether the number I picked is
 * higher or lower than your guess.
 *
 * You call a pre-defined API int guess(int num), which returns three possible results:
 * -1: Your guess is higher than the number I picked (i.e., num > pick).
 *  1: Your guess is lower than the number I picked (i.e., num < pick).
 *  0: Your guess is equal to the number I picked (i.e., num == pick).
 *
 * Your task is to return the correct number.
 *
 * Constraints:
 * - 1 <= n <= 2^31 - 1
 * - 1 <= pick <= n
 *
 * Example 1:
 * Input: n = 10, pick = 6
 * Output: 6
 *
 * Example 2:
 * Input: n = 1, pick = 1
 * Output: 1
 *
 * Example 3:
 * Input: n = 2, pick = 1
 * Output: 1
 */

public class B_GuessNumberHigherorLower {

    /*
     * 🛠️ Brute Force Approach (Linear Search)
     *
     * 🔹 Approach:
     * - Start from 1 and go up to n, checking each number.
     * - Use the guess(num) API to check whether it's correct.
     * - If guess(num) == 0, return num.
     * - If guess(num) == -1, continue checking larger numbers.
     * - If guess(num) == 1, stop when we find the correct number.
     *
     * 🔹 Time & Space Complexity:
     * - Time Complexity: O(N) (Worst case, we check all n numbers)
     * - Space Complexity: O(1) (Only using a few extra variables)
     *
     * 🔹 Why is this inefficient?
     * - If n = 10^6, this approach will take a long time.
     * - We are making unnecessary checks when we can eliminate half
     *   of the possibilities in each step.
     */
    private static class BruteForce {
        int guess(int i) { // Dummy method
            return 0;
        }

        public int guessNumber(int n) {
            for (int i = 1; i <= n; i++) {
                if (guess(i) == 0) {
                    return i; // Found the correct number
                }
            }
            return -1; // Should never reach here
        }
    }

    /*
     * 🚀 Optimized Approach (Binary Search)
     *
     * 🔹 Why Binary Search?
     * - Since the numbers are in a sorted sequence (1 to n), binary search
     *   is the best approach.
     * - At each step, we eliminate half of the remaining numbers.
     *
     * 🔹 Approach:
     * - Set two pointers:
     *   - left = 1 (smallest possible number)
     *   - right = n (largest possible number)
     * - While left ≤ right:
     *   - Compute mid = left + (right - left) / 2
     *   - Use the guess(mid) API:
     *     - If guess(mid) == 0, return mid (found the number 🎯).
     *     - If guess(mid) == -1, search in the left half (right = mid - 1).
     *     - If guess(mid) == 1, search in the right half (left = mid + 1).
     * - If no number is found, return -1 (should never happen in a valid case).
     *
     * 🔹 Time & Space Complexity:
     * - Time Complexity: O(log N) (Each step cuts the search space in half)
     * - Space Complexity: O(1) (No extra memory is used except variables)
     *
     * 🔥 Final Thoughts:
     * | Approach                | Time Complexity | Space Complexity | Pros                  | Cons                      |
     * |-------------------------|----------------|------------------|-----------------------|---------------------------|
     * | Brute Force (Linear)    | O(N)           | O(1)             | Simple & easy         | Very slow for large n     |
     * | Binary Search (Optimal) | O(log N)       | O(1)             | Fast & efficient      | Slightly complex logic    |
     *
     * ✅ Best Choice: Binary Search
     * - For Small n → Linear search is okay.
     * - For Large n → Always use Binary Search.
     */
    private static class Optimized {
        int guess(int i) { // Dummy method
            return 0;
        }

        public int guessNumber(int n) {
            int left = 1, right = n;
            while (left <= right) {
                int mid = left + (right - left) / 2; // Avoid overflow
                int result = guess(mid);
                if (result == 0) return mid; // Found the correct number
                else if (result == -1) right = mid - 1; // Search left half
                else left = mid + 1; // Search right half
            }
            return -1; // Should never reach here
        }
    }
}