package com.interview.dynamic;

/*
 * https://leetcode.com/problems/target-sum/ 
 * https://www.youtube.com/watch?v=hqGa65Rp5LQ
 * https://www.youtube.com/watch?v=MqYLmIzl8sQ
 * Same as SubsetSum dp problem inplace of ||, here + is used refer api subsetSum()
 * Category: Medium, Top100, Must Do
 * You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

 

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1
 

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
 */
public class TargetSum {
    int findTargetSumWays(int[] nums, int target) {
        /*
         * Runtime: 4 ms, faster than 94.56% of Java online submissions for Target Sum.
Memory Usage: 38.9 MB, less than 41.72% of Java online submissions for Target Sum.
         */
        int sum = 0;
        for(int i=0;i<nums.length;i++) {
            sum+=nums[i];
        }
           
        if((target+sum)%2 != 0 || sum < Math.abs(target)) 
            return 0;
        
        sum=(target+sum)/2;  
        int R = nums.length + 1;
        int C= sum+1;
        int[][] dp = new int[R][C];
        for (int i = 0 ;i < R; i++) {//first column 1
            dp[i][0] = 1;
        }
        for(int i=0;i<R;i++)
           for(int j=0;j<C;j++)
           {   
               if(j==0) dp[i][j]=1;
               else dp[i][j]=0;
           }
        
        for(int i=1;i<R;i++)
        
            for(int j=0;j<C;j++)
            {   if(j < nums[i-1])
                    dp[i][j]=dp[i-1][j];
                else
                    dp[i][j]=dp[i-1][j] + dp[i-1][j-nums[i-1]];
            }
        return dp[R-1][C-1];
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
