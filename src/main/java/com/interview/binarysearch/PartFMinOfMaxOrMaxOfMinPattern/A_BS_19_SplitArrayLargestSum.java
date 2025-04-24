package com.interview.binarysearch.PartFMinOfMaxOrMaxOfMinPattern;

import java.util.ArrayList;
import java.util.Collections;

/*
    https://leetcode.com/problems/split-array-largest-sum/description/
    Category: Hard, Facebook, FAANG, Tricky
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
public class A_BS_19_SplitArrayLargestSum {//Same as painter, book proble all are same pattern
    public int splitArray(int[] nums, int k) {
        ArrayList<Integer> input = new ArrayList<>();
        for (int elm: nums) {
            input.add(elm);
        }
        return findLargestMinDistance(input, k);
    }

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

    private static class Solution {
        int subarrayCount(int[] nums, int subArraySum) {
            int count = 0;  // at least one subarray
            int sum = 0;

            for (int num : nums) {
                if (sum + num <= subArraySum) {
                    sum += num;
                } else {
                    count++;
                    sum = num;
                }
            }
            if (sum > 0) {
                count++;
            }

            return count;
        }

        public int splitArray(int[] nums, int k) {
            int low = Integer.MIN_VALUE;
            int high = 0;
            for (int num : nums) {
                low = Math.max(low, num);  // min possible max sum
                high += num;              // max possible max sum
            }

            int ans = high;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int count = subarrayCount(nums, mid);
                if (count <= k) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return ans;
        }
    }
}
