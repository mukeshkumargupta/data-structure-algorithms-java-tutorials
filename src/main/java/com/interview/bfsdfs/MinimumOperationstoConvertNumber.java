package com.interview.bfsdfs;
import java.util.*;
/*
 * 
 * Category: Medium, bfs, VVImp, 
 * 2059. Minimum Operations to Convert Number
 * Related: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/ Medium
Medium

350

19

Add to List

Share
You are given a 0-indexed integer array nums containing distinct numbers, an integer start, and an integer goal. There is an integer x that is initially set to start, and you want to perform operations on x such that it is converted to goal. You can perform the following operation repeatedly on the number x:

If 0 <= x <= 1000, then for any index i in the array (0 <= i < nums.length), you can set x to any of the following:

x + nums[i]
x - nums[i]
x ^ nums[i] (bitwise-XOR)
Note that you can use each nums[i] any number of times in any order. Operations that set x to be out of the range 0 <= x <= 1000 are valid, but no more operations can be done afterward.

Return the minimum number of operations needed to convert x = start into goal, and -1 if it is not possible.

 

Example 1:

Input: nums = [2,4,12], start = 2, goal = 12
Output: 2
Explanation: We can go from 2 → 14 → 12 with the following 2 operations.
- 2 + 12 = 14
- 14 - 2 = 12
Example 2:

Input: nums = [3,5,7], start = 0, goal = -4
Output: 2
Explanation: We can go from 0 → 3 → -4 with the following 2 operations. 
- 0 + 3 = 3
- 3 - 7 = -4
Note that the last operation sets x out of the range 0 <= x <= 1000, which is valid.
Example 3:

Input: nums = [2,8,16], start = 0, goal = 1
Output: -1
Explanation: There is no way to convert 0 into 1.
 

Constraints:

1 <= nums.length <= 1000
-109 <= nums[i], goal <= 109
0 <= start <= 1000
start != goal
All the integers in nums are distinct.
 */
public class MinimumOperationstoConvertNumber {
    public int minimumOperationsSlow(int[] nums, int start, int goal) {
        /*
         * Runtime: 354 ms, faster than 41.14% of Java online submissions for Minimum Operations to Convert Number.
Memory Usage: 175 MB, less than 37.72% of Java online submissions for Minimum Operations to Convert Number.
            It is quite slow but standard bfs, see below optimization
            Here also u are increasing unwanted queue
         */
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList();
        q.add(start);
        int op = 0;
        while(q.size() > 0)
        {
            int len = q.size();
            for(int i = 0; i < len; i++)
            {
                int curr = q.remove();
                if(curr == goal) return op;
                if(curr > 1000 || curr < 0 || visited.contains(curr)) continue;
                
                visited.add(curr);
                for(int elm: nums)
                {
                    q.add(curr + elm);
                    q.add(curr - elm);
                    q.add(curr ^ elm);
                }
                
            }
            op++;
        }
        return -1;
    }
    
    public int minimumOperationsOptimized(int[] nums, int start, int goal) {        
        if(start==goal) return 0;
        
        int ops=0;
        Queue<Integer> q = new LinkedList<>(); 
        q.add(start);
        Set<Integer> visited = new HashSet<>(); //Can use an array of length 1000 as well, as items will always be 0 <= x <= 1000
        
        while(q.size() > 0){
            int s = q.size();
            for(int i=0; i<s; i++){
                int current = q.remove();
                for(int elm:nums){
                    int res=current+elm;
                    if(res==goal) return ops+1;
                    checkAndAdd(res, q, visited);
                    res=current-elm;
                    if(res==goal) return ops+1;
                    checkAndAdd(res, q, visited);
                    res=current^elm;
                    if(res==goal) return ops+1;
                    checkAndAdd(res, q, visited);
                }
            }
            ops++;
        }
        
        return -1;
    }
    
    private void checkAndAdd(int res, Queue<Integer> q, Set<Integer> visited){
        if(res>=0&&res<=1000&&!visited.contains(res)){
            q.add(res);
            visited.add(res);
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
