package com.interview.dynamic.PartIDPOnLISubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class B_LongestDivisibleSubset {
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
    public static class LongestDivisibleSubset {
        static List<Integer> divisibleSet(int arr[]){

            Arrays.sort(arr);
            int n = arr.length;
            int[] dp=new int[n];
            Arrays.fill(dp,1);
            int[] hash=new int[n];
            Arrays.fill(hash,1);

            for(int i=0; i<=n-1; i++){

                hash[i] = i; // initializing with current index
                for(int prev_index = 0; prev_index <=i-1; prev_index ++){

                    if(arr[i] %  arr[prev_index] == 0 && 1 + dp[prev_index] > dp[i]){
                        dp[i] = 1 + dp[prev_index];
                        hash[i] = prev_index;
                    }
                }
            }

            int ans = -1;
            int lastIndex =-1;

            for(int i=0; i<=n-1; i++){
                if(dp[i]> ans){
                    ans = dp[i];
                    lastIndex = i;
                }
            }

            List<Integer> temp=new ArrayList<>();
            temp.add(arr[lastIndex]);

            while(hash[lastIndex] != lastIndex){ // till not reach the initialization value
                lastIndex = hash[lastIndex];
                temp.add(arr[lastIndex]);
            }

            Collections.reverse(temp);
            return temp;

        }
    }
}
