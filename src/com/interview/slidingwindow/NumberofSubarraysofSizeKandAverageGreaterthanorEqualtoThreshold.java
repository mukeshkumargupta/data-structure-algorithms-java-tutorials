package com.interview.slidingwindow;

/*
 * https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/ 
 * Category: Medium
 * Related: 
 * https://leetcode.com/problems/largest-component-size-by-common-factor/ Hard
 * https://leetcode.com/problems/longest-arithmetic-subsequence/ Medium
 * https://leetcode.com/problems/min-cost-to-connect-all-points/ Medium
 */
public class NumberofSubarraysofSizeKandAverageGreaterthanorEqualtoThreshold {
    public int numOfSubarrays(int[] arr, int k, int threshold) { //runtime 96%
        int l = arr.length;
        int i = 0;
        int sum = 0;
        int count = 0;
        for (; i < k; i++) {
           sum += arr[i];
        }
        if (sum/k >= threshold) {
           count++; 
        }
        int startIndex = 0;
        for (; i < l; i++) {
            sum -= arr[startIndex++];
            sum += arr[i];
            if (sum/k >= threshold) {
               count++; 
            }
        }
        return count;
        
    }
}
