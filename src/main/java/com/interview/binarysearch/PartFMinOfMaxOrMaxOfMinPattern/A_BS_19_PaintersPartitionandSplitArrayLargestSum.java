package com.interview.binarysearch.PartFMinOfMaxOrMaxOfMinPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * Problem: Painter's Partition Problem
 *
 * Links:
 * - Video: https://www.youtube.com/watch?v=thUd_WJn6wk&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=20
 * - Leetcode: https://leetcode.com/problems/split-array-largest-sum/
 * - CodeStudio: https://www.naukri.com/code360/problems/split-the-given-array-into-k-sub-arrays_1215015
 * - Similar: https://www.naukri.com/code360/problems/painter-s-partition-problem_1089557
 *
 * Category: Hard
 *
 * Related Problems:
 * - https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/ (Medium)
 * - https://leetcode.com/problems/divide-chocolate/ (Hard)
 * - https://leetcode.com/problems/fair-distribution-of-cookies/ (Medium)
 * - https://leetcode.com/problems/subsequence-of-size-k-with-the-largest-even-sum/ (Medium)
 * - https://leetcode.com/problems/maximum-total-beauty-of-the-gardens/ (Hard)
 * - https://leetcode.com/problems/number-of-ways-to-split-array/ (Medium)
 * - https://leetcode.com/problems/minimum-cost-to-split-an-array/ (Hard)
 * - https://leetcode.com/problems/distribute-elements-into-two-arrays-i/ (Easy)
 * - https://leetcode.com/problems/distribute-elements-into-two-arrays-ii/ (Hard)
 *
 * -------------------------------------------------------------------------------------
 *
 * Problem Statement:
 *
 * Given an array/list of length ‘n’, where the array/list represents the boards and
 * each element of the given array/list represents the length of each board.
 * Some ‘k’ number of painters are available to paint these boards.
 * Each unit of a board takes 1 unit of time to paint.
 *
 * You are supposed to return the minimum time required to paint all the boards
 * under the constraint that any painter will only paint a contiguous section of boards.
 *
 * -------------------------------------------------------------------------------------
 *
 * Example:
 * Input: arr = [2, 1, 5, 6, 2, 3], k = 2
 * Output: 11
 * Explanation:
 *   - Painter 1 paints boards [2, 1, 5] -> 8 units
 *   - Painter 2 paints boards [6, 2, 3] -> 11 units
 *   => max(8, 11) = 11
 *
 * -------------------------------------------------------------------------------------
 *
 * Sample Input 1:
 *   n = 4, k = 2
 *   arr = [10, 20, 30, 40]
 *
 * Sample Output 1:
 *   60
 *
 * Explanation:
 *   - Painter 1 paints [10, 20, 30] => 60 units
 *   - Painter 2 paints [40] => 40 units
 *   => max(60, 40) = 60
 *
 * -------------------------------------------------------------------------------------
 *
 * Sample Input 2:
 *   n = 2, k = 2
 *   arr = [48, 90]
 *
 * Sample Output 2:
 *   90
 *
 * -------------------------------------------------------------------------------------
 *
 * Expected Time Complexity:
 *   O(n * log(sum(arr)))
 *
 * -------------------------------------------------------------------------------------
 *
 * Constraints:
 *   1 <= n <= 10^5
 *   1 <= k <= n
 *   1 <= arr[i] <= 10^9
 *   Time Limit: 1 second
 *
 * -------------------------------------------------------------------------------------
 *
 * Insight:
 * This is a classic Binary Search on Answer problem.
 *   - Minimum possible time = max(arr)
 *   - Maximum possible time = sum(arr)
 * Use binary search to find the smallest possible maximum time such that we can
 * divide the boards into ≤ k subarrays where each subarray represents one painter's work.
 */
public class A_BS_19_PaintersPartitionandSplitArrayLargestSum {

    /*
        Complexity Analysis

        Time Complexity: O(N * (sum(arr[])-max(arr[])+1)), where N = size of the array, sum(arr[]) = sum of all array elements, max(arr[]) = maximum of all array elements.
        Reason: We are using a loop from max(arr[]) to sum(arr[]) to check all possible values of time. Inside the loop, we are calling the countPainters() function for each number. Now, inside the countPainters() function, we are using a loop that runs for N times.

        Space Complexity:  O(1) as we are not using any extra space to solve this problem.
     */
    private static class BruitForce {
        public static int countPainters(ArrayList<Integer> boards, int time) {
            int n = boards.size(); // size of array.
            int painters = 0;
            long boardsPainter = 0;
            for (int i = 0; i < n; i++) {
                if (boardsPainter + boards.get(i) <= time) {
                    // allocate board to current painter
                    boardsPainter += boards.get(i);
                } else {
                    // allocate board to next painter
                    painters++;
                    boardsPainter = boards.get(i);
                }
            }
            if (boardsPainter > 0) {
                painters++;
            }
            return painters;
        }

        public static int findLargestMinDistance(ArrayList<Integer> boards, int k) {
            int low = Collections.max(boards);
            int high = boards.stream().mapToInt(Integer::intValue).sum();

            for (int time = low; time <= high; time++) {
                if (countPainters(boards, time) <= k) {
                    return time;
                }
            }
            return low;
        }

        public static void main(String[] args) {
            ArrayList<Integer> boards = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
            int k = 2;
            int ans = findLargestMinDistance(boards, k);
            System.out.println("The answer is: " + ans);
        }
    }

    /*
    Complexity Analysis

    Time Complexity: O(N * log(sum(arr[])-max(arr[])+1)), where N = size of the array, sum(arr[]) = sum of all array elements, max(arr[]) = maximum of all array elements.
    Reason: We are applying binary search on [max(arr[]), sum(arr[])]. Inside the loop, we are calling the countPainters() function for the value of ‘mid’. Now, inside the countPainters() function, we are using a loop that runs for N times.

    Space Complexity:  O(1) as we are not using any extra space to solve this problem.
     */
    private static class Optimized {//Exactly same as book pages allocations
        public static int countPainters(ArrayList<Integer> boards, int time) {
            int n = boards.size(); // size of array.
            int painters = 0;
            long boardsPainter = 0;
            for (int i = 0; i < n; i++) {
                if (boardsPainter + boards.get(i) <= time) {
                    // allocate board to current painter
                    boardsPainter += boards.get(i);
                } else {
                    // allocate board to next painter
                    painters++;
                    boardsPainter = boards.get(i);
                }
            }
            if (boardsPainter > 0) {
                painters++;
            }
            return painters;
        }

        public static int findLargestMinDistance(ArrayList<Integer> boards, int k) {
            int low = Collections.max(boards);
            int high = boards.stream().mapToInt(Integer::intValue).sum();

            // Apply binary search:
            int ans = high;
            while (low <= high) {
                int mid = (low + high) / 2;
                int painters = countPainters(boards, mid);
                if (painters <= k) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            //return low;
            return ans;
        }

        public static void main(String[] args) {
            ArrayList<Integer> boards = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
            int k = 2;
            int ans = findLargestMinDistance(boards, k);
            System.out.println("The answer is: " + ans);
        }
    }
}
