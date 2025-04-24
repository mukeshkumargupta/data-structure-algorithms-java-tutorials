package com.interview.binarysearch.PartFMinOfMaxOrMaxOfMinPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
    https://www.youtube.com/watch?v=thUd_WJn6wk&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=20
    https://leetcode.com/problems/split-array-largest-sum/submissions/

    https://www.naukri.com/code360/problems/split-the-given-array-into-k-sub-arrays_1215015
    https://www.naukri.com/code360/problems/painter-s-partition-problem_1089557 on similar pattern
    Category: Hard
    Related:
    https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/ Medium
    https://leetcode.com/problems/divide-chocolate/ Hard
    https://leetcode.com/problems/fair-distribution-of-cookies/ Medium
    https://leetcode.com/problems/subsequence-of-size-k-with-the-largest-even-sum/ Medium
    https://leetcode.com/problems/maximum-total-beauty-of-the-gardens/ Hard
    https://leetcode.com/problems/number-of-ways-to-split-array/ Medium
    https://leetcode.com/problems/minimum-cost-to-split-an-array/ Hard
    https://leetcode.com/problems/distribute-elements-into-two-arrays-i/ Easy
    https://leetcode.com/problems/distribute-elements-into-two-arrays-ii/ Hard
    Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

    Return the minimized largest sum of the split.

    A subarray is a contiguous part of the array.



    Example 1:

    Input: nums = [7,2,5,10,8], k = 2
    Output: 18
    Explanation: There are four ways to split nums into two subarrays.
    The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
    Example 2:

    Input: nums = [1,2,3,4,5], k = 2
    Output: 9
    Explanation: There are four ways to split nums into two subarrays.
    The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.


    Constraints:

    1 <= nums.length <= 1000
    0 <= nums[i] <= 106
    1 <= k <= min(50, nums.length)
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
