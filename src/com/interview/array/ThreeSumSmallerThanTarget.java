package com.interview.array;

import java.util.Arrays;

/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k
 * with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * https://leetcode.com/problems/3sum-smaller/
 * Category: Medium,triplet
 * Related: https://leetcode.com/problems/increasing-triplet-subsequence/ Medium
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/ Medium
 * https://leetcode.com/problems/count-good-triplets/ Easy
 * 
 */
public class ThreeSumSmallerThanTarget {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] >= target) {
                    k--;
                } else {
                    count += k - j;
                    j++;
                }
            }
        }
        return count;
    }
}

public class Solution {
    public int threeSumClosest(ArrayList<Integer> A, int B) {
        Collections.sort(A, (a, b) -> {
            return a -b;
        });
        int l = A.size();
        int diff = Integer.MAX_VALUE; 
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < l-2; i++) {
            int j = i+1;
            int k = l-1;
            while (i < j) {
                int sum = A.get(i) + A.get(j) + A.get(k);
                if (Math.abs(sum-B) < diff) {
                    diff = Math.abs(sum-B);
                    result = sum;
                    if (diff ==0) {
                        break;
                    }
                }

            }
            if (diff ==0) {
                break;
            }

        }
        return result;
    }
}

