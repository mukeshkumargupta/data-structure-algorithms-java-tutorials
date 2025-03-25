package com.interview.slidingwindow;

/*
 * https://leetcode.com/problems/minimum-size-subarray-sum/ 99.98 runtime
 * Category: Medium, Tricky
 * https://www.youtube.com/watch?v=S6Xg-0uaODc
 * Related:
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/ Medium VVImp Locked
 * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/ Medium VVImp
 * https://leetcode.com/problems/k-radius-subarray-averages/ Medium VImp
 * 
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of
 * which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.

 

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
 

Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 105
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        /*
         * Runtime: 1 ms, faster than 99.98% of Java online submissions for Minimum Size Subarray Sum.
Memory Usage: 44.9 MB, less than 21.33% of Java online submissions for Minimum Size Subarray Sum.
Complexity analysis

Time complexity: O(n). Single iteration of O(n).
Each element can be visited atmost twice, once by the right pointer(ii) and (atmost)once by the \text{left}left pointer.
Space complexity: O(1)O(1) extra space. Only constant space required for \text{left}left, \text{sum}sum, \text{ans}ans and ii.
         */
        int result = Integer.MAX_VALUE;
        int l = nums.length;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < l; i++) {
            sum += nums[i];
            
            while (sum >= target) {//minimize window
                result = Math.min(result, (i -  start + 1) );
                sum -= nums[start++];//minimize
            }
            
        }
        return  result != Integer.MAX_VALUE ? result : 0;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
