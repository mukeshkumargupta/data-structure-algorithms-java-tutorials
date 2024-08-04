package com.interview.dynamic;

import java.util.*;

/*
 * https://leetcode.com/problems/largest-divisible-subset/
 * https://www.youtube.com/watch?v=gDuZwBW9VvM
 * Category: Medium, lis, Must Do
 * Related: https://leetcode.com/problems/number-of-corner-rectangles/ Medium Locked
 * https://leetcode.com/problems/find-all-good-strings/ Hard VImp
 * https://leetcode.com/problems/count-fertile-pyramids-in-a-land/ Hard VVImp
 * Related: 
 * 368. Largest Divisible Subset
Medium

3193

150

Add to List

Share
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
Accepted
150,474
Submissions
370,949
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        /*Runtime: 23 ms, faster than 79.98% of Java online submissions for Largest Divisible Subset.
Memory Usage: 42.4 MB, less than 84.85% of Java online submissions for Largest Divisible Subset.
*/
        int l = nums.length;
        int[] dp = new int[l];
        
        for (int i = 0; i < l; i++) {
            dp[i] = 1;
            
        }
        Arrays.sort(nums);
        int[] cameFram = new int[l];
        int lastIndex = 0;
        int max = 1;//1 length by default
        for (int i =1 ; i < l; i++) {
            cameFram[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cameFram[i] = j;
                        if (dp[i] > max) {//This will save some check, if you don't do max, outside of inner loop
                            max = dp[i];
                            lastIndex = i;
                        }
                    }
                    
                }
                
            }

            
        }
        List<Integer> result = new ArrayList<>();
        result.add(nums[lastIndex]);
        /*for (int elm: cameFram) {
            System.out.println(elm);
        }*/
        while (lastIndex != cameFram[lastIndex]) {
            lastIndex = cameFram[lastIndex];
            result.add(nums[lastIndex]);
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LargestDivisibleSubset instance = new LargestDivisibleSubset();
        instance.largestDivisibleSubset(new int[] {1,2,3});
        
    }
    
}
