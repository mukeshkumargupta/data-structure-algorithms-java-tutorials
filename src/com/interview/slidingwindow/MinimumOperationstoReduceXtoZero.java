package com.interview.slidingwindow;

import java.util.*;
/*
 * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 * Category: Medium, Tricky, Try bfs as well
 * Best solution:
 * https://www.youtube.com/watch?v=7nzwrX4N_A0
 * DP solution but not efficient
 * https://www.youtube.com/watch?v=HddgLcq9Efs
 * Related: https://leetcode.com/problems/removing-minimum-number-of-magic-beans/ Medium VVImp
 * 
 * 1658. Minimum Operations to Reduce X to Zero
Medium

1561

30

Add to List

Share
You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

 

Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
Accepted
45,917
Submissions
136,879
 */
public class MinimumOperationstoReduceXtoZero {
    
    public int minOperations(int[] nums, int x) {
        /*
         * Runtime: 155 ms, faster than 18.75% of Java online submissions for Minimum Operations to Reduce X to Zero.
Memory Usage: 193.4 MB, less than 13.36% of Java online submissions for Minimum Operations to Reduce X to Zero.
This is optimized solution, variant of find total count of given target
         */
        int l = nums.length;
        int sum = 0;
        Map<Integer, Integer> mymap = new HashMap<>();   //<sum,pos>
        for(int i=0;i<l;++i)
        {
            sum+=nums[i];
            mymap.put(sum, i);
        }
        if(x>sum)   //Sum out of range
            return -1;
        mymap.put(0, 0);
        
        int longest = 0;
        //sum-=x;
        int k = sum - x;
        int val = 0;
        for(int i=0;i<l;++i)
        {
            val += nums[i];
            if(mymap.containsKey(val-k))
            {
                if(val-k==0)
                    longest = Math.max(longest,i-mymap.get(val-k) +1);
                else
                    longest = Math.max(longest,i-mymap.get(val-k));
            }
        }
        return longest==0?(k==0?l:-1):l-longest;

    }
    
    Map<String, Integer> map;
    public int minOperationsUtil(int[] nums, int x, int start, int end, int count) {
        if(x==0)    //Sum found
            return count;
        if(x<0 || start>end)   //Out of bounds
            return (int) 1e6;
        String key = "" + start + "*"+ end+ "*"+x; //Form Key, this is state like 2, 3, val 9
        if(map.containsKey(key))  //Check if already calculated
            return map.get(key);
        
        int l = minOperationsUtil(nums,x-nums[start],start+1,end,count+1);  //Include left element
        int r = minOperationsUtil(nums,x-nums[end],start,end-1,count+1); //Include right element
        int val = Math.min(l,r);
        map.put(key, val);
        return val;
        
    }
    public int minOperations(int[] nums, int x) {
        map = new HashMap<>();
        int ans = minOperationsUtil(nums, x, 0, nums.length-1, 0);
        return ans==1e6?-1:ans;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
